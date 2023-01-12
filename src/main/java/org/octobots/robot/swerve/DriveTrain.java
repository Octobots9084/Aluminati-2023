/*
 * This file is part of Placeholder-2023, licensed under the GNU General Public License (GPLv3).
 *
 * Copyright (c) Octobots <https://github.com/Octobots9084>
 * Copyright (c) contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.octobots.robot.swerve;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import org.octobots.robot.MotorIDs;
import org.octobots.robot.util.Gyro;
import org.octobots.robot.util.MathUtil;

/**
 * Represents a swerve drive style drivetrain.
 */
public class DriveTrain extends SubsystemBase {
    private static DriveTrain INSTANCE;

    public static DriveTrain getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DriveTrain();
        }
        return INSTANCE;
    }

    //Drive Speed Constants
    public static final double MAX_SPEED = 10; // m/s
    public static final double MAX_ACCELERATION = 0.5; // m/s
    public static final double MAX_ANGULAR_SPEED = Math.PI * 3 * 0.8; // rad/s
    public static final double MAX_ANGULAR_ACCELERATION = Math.PI * 3; // rad/s
    //Turn Constraints
    public static double MAX_TURN_SPEED = 10;
    public static double TOLERANCE = 1.5;
    //Module Mappings / Measurements
    private static final double WHEEL_DIST_TO_CENTER = 0.29; //m

    private final Gyro gyro;
    //Modules
    private final SwerveModule[] swerveModules = new SwerveModule[4];
    private final Translation2d[] swervePosition = new Translation2d[4];

    private final DutyCycleEncoder[] rioEncoders = new DutyCycleEncoder[4];
    //Drive Controllers
    private final SwerveDriveKinematics swerveDriveKinematics;
    //Logging
    //Flags
    private boolean isFieldCentric = true;
    private boolean useDriverAssist = false;
    private double targetRotationAngle = 0.0;
    private double turnSpeedP = 0.05;
    private double minTurnSpeed = 0.42;

    private DriveTrain() {
        rioEncoders[0] = new DutyCycleEncoder(1);
        rioEncoders[1] = new DutyCycleEncoder(2);
        rioEncoders[2] = new DutyCycleEncoder(3);
        rioEncoders[3] = new DutyCycleEncoder(4);

        //Position relative to center of robot -> (0,0) is the center (m)
        swervePosition[0] = new Translation2d(WHEEL_DIST_TO_CENTER, WHEEL_DIST_TO_CENTER); // FL
        swervePosition[1] = new Translation2d(WHEEL_DIST_TO_CENTER, -WHEEL_DIST_TO_CENTER); // FR
        swervePosition[2] = new Translation2d(-WHEEL_DIST_TO_CENTER, WHEEL_DIST_TO_CENTER); // BL
        swervePosition[3] = new Translation2d(-WHEEL_DIST_TO_CENTER, -WHEEL_DIST_TO_CENTER); // BR

        swerveModules[0] = new SwerveModule(MotorIDs.FRONT_LEFT_DRIVE, MotorIDs.FRONT_LEFT_STEER,0, rioEncoders[0]);
        swerveModules[1] = new SwerveModule(MotorIDs.FRONT_RIGHT_DRIVE, MotorIDs.FRONT_RIGHT_STEER, 0, rioEncoders[1]);
        swerveModules[2] = new SwerveModule(MotorIDs.BACK_LEFT_DRIVE, MotorIDs.BACK_LEFT_STEER, 0, rioEncoders[2]);
        swerveModules[3] = new SwerveModule(MotorIDs.BACK_RIGHT_DRIVE, MotorIDs.BACK_RIGHT_STEER, 0, rioEncoders[3]);



        // Setup gyro and pose estimator
        this.gyro = Gyro.getInstance();
        this.swerveDriveKinematics = new SwerveDriveKinematics(
                swervePosition[0], swervePosition[1], swervePosition[2], swervePosition[3]
        );

    }

    /**
     * Method to drive the robot using joystick info.
     *
     * @param xSpeed        Speed of the robot in the x direction (forward).
     * @param ySpeed        Speed of the robot in the y direction (sideways).
     * @param rot           Angular rate of the robot.
     * @param fieldRelative Whether the provided x and y speeds are relative to the field.
     */
    public void drive(double xSpeed, double ySpeed, double rot, boolean fieldRelative) {
        // Calculate swerve states
        var swerveModuleStates = swerveDriveKinematics.toSwerveModuleStates(
                fieldRelative ? ChassisSpeeds.fromFieldRelativeSpeeds(xSpeed, ySpeed, rot, gyro.getRotation2d())
                        : new ChassisSpeeds(xSpeed, ySpeed, rot)
        );
        SwerveDriveKinematics.desaturateWheelSpeeds(swerveModuleStates, MAX_SPEED);

        // Set states
        if (Math.abs(xSpeed) <= 0.05 && Math.abs(ySpeed) <= 0.05 && rot == 0) {
            for (int i = 0; i < swerveModuleStates.length; i++) {
                swerveModules[i].setDesiredState(new SwerveModuleState(0, new Rotation2d(0)));
            }
        } else {
            for (int i = 0; i < swerveModuleStates.length; i++) {
                swerveModules[i].setDesiredState(swerveModuleStates[i]);
            }
        }
    }

    public double getRotationSpeed() {
        double gyroAngle = MathUtil.wrapToCircle(gyro.getRotation2d().getDegrees());
        if (MathUtil.isWithinTolerance(gyroAngle, targetRotationAngle, TOLERANCE)) {
            return 0.0;
        }
        double targetAngle = MathUtil.wrapToCircle(targetRotationAngle);
        var diff = targetAngle - gyroAngle;
        if (Math.abs(diff) >= 180 && diff < 0) {
            diff += 360;
        }
        if (Math.abs(diff) >= 180 && diff > 0) {
            diff -= 360;
        }

        double vel = (turnSpeedP * (diff));
        return Math.signum(diff) * (Math.min(Math.abs(vel), MAX_ANGULAR_SPEED) + minTurnSpeed);
    }

    public void setSwerveModuleAngle(double angle) {
        // Set swerve states with angle
        for (var m : swerveModules) {
            m.setDesiredState(new SwerveModuleState(0, new Rotation2d(angle)));
        }
    }

    public void setSwerveModuleVelocity(double vel) {
        for (var m : swerveModules) {
            m.setDriveMotorVelocity(vel);
        }
    }

    public boolean useDriverAssist() {
        return useDriverAssist;
    }

    public void setUseDriverAssist(boolean useDriverAssist) {
        this.useDriverAssist = useDriverAssist;
    }

    public void setFieldCentric(boolean fieldCentric) {
        this.isFieldCentric = fieldCentric;
    }

    public boolean getFieldCentric() {
        return isFieldCentric;
    }

    public SwerveDriveKinematics getSwerveDriveKinematics() {
        return this.swerveDriveKinematics;
    }

    public ChassisSpeeds getChassisSpeeds() {
        return swerveDriveKinematics.toChassisSpeeds(
                swerveModules[0].getState(),
                swerveModules[1].getState(),
                swerveModules[2].getState(),
                swerveModules[3].getState()
        );
    }

    public double getTargetRotationAngle() {
        return targetRotationAngle;
    }

    public void setTargetRotationAngle(double targetRotationAngle) {
        this.targetRotationAngle = targetRotationAngle;
    }

//    public PoseEstimator getPoseEstimator() {
//        return swerveDrivePoseEstimator;
//    }

    public SwerveModule[] getSwerveModules() {
        return swerveModules;
    }

    public double getTurnSpeedP() {
        return turnSpeedP;
    }

    public void setTurnSpeedP(double turnSpeedP) {
        this.turnSpeedP = turnSpeedP;
    }

    public double getMinTurnSpeed() {
        return minTurnSpeed;
    }

    public void setMinTurnSpeed(double minTurnSpeed) {
        this.minTurnSpeed = minTurnSpeed;
    }



    public void updateSwerveStates() {
        for (var sm : swerveModules) {
            sm.updateSwerveInformation();
        }
    }
}
