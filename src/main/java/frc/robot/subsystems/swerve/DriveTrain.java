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

package frc.robot.subsystems.swerve;

import java.util.HashMap;

import com.pathplanner.lib.PathConstraints;
import com.pathplanner.lib.PathPlanner;
import com.pathplanner.lib.PathPlannerTrajectory;
import com.pathplanner.lib.commands.FollowPathWithEvents;
import com.pathplanner.lib.commands.PPSwerveControllerCommand;

import edu.wpi.first.math.controller.HolonomicDriveController;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.swerve.SwerveControl;
import frc.robot.robot.MotorIDs;
import frc.robot.robot.Tuning;
import frc.robot.util.Gyro;
import frc.robot.util.MathUtil;
import frc.robot.util.PoseEstimator;

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
    public static final double MAX_SPEED = 5; // m/s
    public static final double MAX_ACCELERATION = 0.5; // m/s
    public static final double MAX_ANGULAR_SPEED = Math.PI * 3 * 0.8; // rad/s
    public static final double MAX_ANGULAR_ACCELERATION = Math.PI * 3; // rad/s
    //Turn Constraints
    public static double MAX_TURN_SPEED = 10;
    public static double TOLERANCE = 1.5;
    //Module Mappings / Measurements
    private static final double WHEEL_DIST_TO_CENTER = 0.3; //m

    private final Gyro gyro;
    //Modules
    private final SwerveModule[] swerveModules = new SwerveModule[4];
    private final Translation2d[] swervePosition = new Translation2d[4];
    //Drive Controllers
    private final SwerveDriveKinematics swerveDriveKinematics;
    //Flags
    private boolean isFieldCentric = true;
    private boolean useDriverAssist = false;
    private double targetRotationAngle = 0.0;
    private double turnSpeedP = 0.05;
    private double minTurnSpeed = 0.42;
    private PIDController daController;
    private double previousRot = 0;
    public double previousXSpeed = 0;

    private final HolonomicDriveController holonomicDriveController;

    // Pose Estimator
    private final PoseEstimator swerveDrivePoseEstimator;

    private DriveTrain() {
        this.daController = new PIDController(0.1, 0.00, 0.0);
        //Position relative to center of robot -> (0,0) is the center (m)
        // swervePosition[0] = new Translation2d(0.32, 0.32);
        // swervePosition[1] = new Translation2d(0.32, -0.32);
        // swervePosition[2] = new Translation2d(-0.32, 0.32);
        // swervePosition[3] = new Translation2d(-0.32, 0.32);

        swervePosition[0] = new Translation2d(WHEEL_DIST_TO_CENTER, WHEEL_DIST_TO_CENTER);
        swervePosition[1] = new Translation2d(WHEEL_DIST_TO_CENTER, -WHEEL_DIST_TO_CENTER);
        swervePosition[2] = new Translation2d(-WHEEL_DIST_TO_CENTER, WHEEL_DIST_TO_CENTER);
        swervePosition[3] = new Translation2d(-WHEEL_DIST_TO_CENTER, -WHEEL_DIST_TO_CENTER);

        swerveModules[0] = new SwerveModule(MotorIDs.FRONT_LEFT_DRIVE, MotorIDs.FRONT_LEFT_STEER, false,
                Tuning.FL_TURN_PID, Tuning.FL_DRIVE_PID, Tuning.FL_DRIVE_PID2);
        swerveModules[1] = new SwerveModule(MotorIDs.FRONT_RIGHT_DRIVE, MotorIDs.FRONT_RIGHT_STEER, false,
                Tuning.FR_TURN_PID, Tuning.FR_DRIVE_PID, Tuning.FR_DRIVE_PID2);
        swerveModules[2] = new SwerveModule(MotorIDs.BACK_LEFT_DRIVE, MotorIDs.BACK_LEFT_STEER, false,
                Tuning.BL_TURN_PID, Tuning.BL_DRIVE_PID, Tuning.BL_DRIVE_PID2);
        swerveModules[3] = new SwerveModule(MotorIDs.BACK_RIGHT_DRIVE, MotorIDs.BACK_RIGHT_STEER, false,
                Tuning.BR_TURN_PID, Tuning.BR_DRIVE_PID, Tuning.BR_DRIVE_PID2);

        // Setup gyro
        this.gyro = Gyro.getInstance();
        this.swerveDriveKinematics = new SwerveDriveKinematics(
                swervePosition[0], swervePosition[1], swervePosition[2], swervePosition[3]);

        swerveDrivePoseEstimator = new PoseEstimator(this.gyro, swerveDriveKinematics, swerveModules);

        this.holonomicDriveController = new HolonomicDriveController(
                //PID FOR X DISTANCE (kp of 1 = 1m/s extra velocity / m of error)
                new PIDController(1.2, 0.001, 0),
                //PID FOR Y DISTANCE (kp of 1.2 = 1.2m/s extra velocity / m of error)
                new PIDController(1.2, 0.001, 0),
                //PID FOR ROTATION (kp of 1 = 1rad/s extra velocity / rad of error)
                new ProfiledPIDController(0.1, 0.0, 0.00,
                        new TrapezoidProfile.Constraints(MAX_ANGULAR_SPEED * 5, MAX_ANGULAR_ACCELERATION * 5)));

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

        // SmartDashboard.putNumber("xSpeed", xSpeed);
        //driver assist """implementation"""

        if (useDriverAssist) {

            //SmartDashboard.putNumber("controt", rot);
            if (!MathUtil.isWithinTolerance(rot, 0, 0.01) && SwerveControl.hasTurnControl) {
                this.setTargetRotationAngle(gyro.getUnwrappedAngle());
                previousRot = rot;

            } else {
                if (MathUtil.isWithinTolerance(rot, 0, 0.01) && !MathUtil.isWithinTolerance(previousRot, 0, 0.01)) {
                    this.setTargetRotationAngle(gyro.getUnwrappedAngle());
                    previousRot = rot;
                }
                rot = getRotationSpeed();
            }
            //SmartDashboard.putNumber("rot", rot);

            //SmartDashboard.putNumber("darot", rot);
        }
        // Calculate swerve states
        var swerveModuleStates = swerveDriveKinematics.toSwerveModuleStates(
                fieldRelative ? ChassisSpeeds.fromFieldRelativeSpeeds(xSpeed, ySpeed, rot, gyro.getRotation2d())
                        : new ChassisSpeeds(xSpeed, ySpeed, rot));

        SwerveDriveKinematics.desaturateWheelSpeeds(swerveModuleStates, MAX_SPEED);

        // Set states
        if (Math.abs(xSpeed) <= 0.01 && Math.abs(ySpeed) <= 0.01 && Math.abs(rot) <= 0.2) {
            for (int i = 0; i < swerveModuleStates.length; i++) {
                swerveModules[i].setDesiredState(
                        new SwerveModuleState(0, new Rotation2d(0/*swerveModules[i].getAngle()*/)), true);
            }
        } else {
            for (int i = 0; i < swerveModuleStates.length; i++) {
                swerveModules[i].setDesiredState(swerveModuleStates[i]);
            }
        }

        previousXSpeed = xSpeed;

    }

    public void driveAutos(ChassisSpeeds chassisSpeeds) {
        // SmartDashboard.putNumber("xSppedpath", chassisSpeeds.vxMetersPerSecond);
        // SmartDashboard.putNumber("ySppedpath", chassisSpeeds.vyMetersPerSecond);
        // SmartDashboard.putNumber("rotpoath", chassisSpeeds.omegaRadiansPerSecond);
        // driveDashboard.setEntry("X-Speed Path", chassisSpeeds.vxMetersPerSecond);
        // driveDashboard.setEntry("Y-Speed Path", chassisSpeeds.vyMetersPerSecond);
        // driveDashboard.setEntry("Rot Path", chassisSpeeds.omegaRadiansPerSecond);

        // if (Math.abs(chassisSpeeds.omegaRadiansPerSecond)>DriveTrain.MAX_ANGULAR_SPEED) {
        //     chassisSpeeds.omegaRadiansPerSecond = DriveTrain.MAX_ANGULAR_SPEED * Math.signum(chassisSpeeds.omegaRadiansPerSecond);
        // }
        // targetRotationAngle = targetRotationAngle + chassisSpeeds.omegaRadiansPerSecond*0.02;
        double rot = getRotationSpeed();
        var swerveModuleStates = swerveDriveKinematics.toSwerveModuleStates(new ChassisSpeeds(
                chassisSpeeds.vxMetersPerSecond, chassisSpeeds.vyMetersPerSecond, rot));

        for (int i = 0; i < swerveModuleStates.length; i++) {
            swerveModules[i].setDesiredState(swerveModuleStates[i]);
        }

    }

    public void driveWithStates(SwerveModuleState[] states) {
        // SmartDashboard.putNumber("xSppedpath", chassisSpeeds.vxMetersPerSecond);
        // SmartDashboard.putNumber("ySppedpath", chassisSpeeds.vyMetersPerSecond);
        // SmartDashboard.putNumber("rotpoath", chassisSpeeds.omegaRadiansPerSecond);
        // driveDashboard.setEntry("X-Speed Path", chassisSpeeds.vxMetersPerSecond);
        // driveDashboard.setEntry("Y-Speed Path", chassisSpeeds.vyMetersPerSecond);

        for (int i = 0; i < states.length; i++) {
            swerveModules[i].setDesiredState(states[i]);
        }
    }

    public SwerveModuleState[] getModuleStates() {
        SwerveModuleState[] swerveModuleStates = new SwerveModuleState[4];
        for (int i = 0; i < 4; i++) {
            swerveModuleStates[i] = swerveModules[i].getState();
        }

        return swerveModuleStates;
    }

    public Pose2d getPose2d() {
        return this.getPoseEstimator().getRobotPose();
    }

    public Pose2d getPose2dPathplanner() {
        return new Pose2d(this.getPoseEstimator().getRobotPose().getX(), this.getPoseEstimator().getRobotPose().getY(),
                new Rotation2d((Math.abs(this.getPoseEstimator().getRobotPose().getRotation().getRadians()) % 180)
                        * Math.signum(this.getPoseEstimator().getRobotPose().getRotation().getRadians())));
    }

    public void resetPosePathplanner(Pose2d pose2d) {
    }

    public double getRotationSpeed() {
        double gyroAngle = gyro.getUnwrappedAngle();
        // if (MathUtil.isWithinTolerance(gyroAngle, targetRotationAngle, TOLERANCE)) {
        //     return 0.0;
        // }
        double targetAngle = targetRotationAngle;

        double otherDiff = gyroAngle - (targetAngle + 360);
        double diff = gyroAngle - targetAngle;
        if (Math.abs(diff) > Math.abs(otherDiff)) {
            diff = otherDiff;
        }

        double vel = daController.calculate(diff);
        if (MathUtil.isWithinTolerance(diff, 0, 0.03))
            ;
        if (vel > 2.5) {
            vel = 2.5;
        }
        if (vel < -2.5) {
            vel = -2.5;
        }

        return vel;
    }

    public double getShortestRotationDiff() {
        double gyroAngle = gyro.getUnwrappedAngle();
        // if (MathUtil.isWithinTolerance(gyroAngle, targetRotationAngle, TOLERANCE)) {
        //     return 0.0;
        // }
        double targetAngle = targetRotationAngle;

        double otherDiff = gyroAngle - (targetAngle + 360);
        double diff = gyroAngle - targetAngle;
        if (Math.abs(diff) > Math.abs(otherDiff)) {
            diff = otherDiff;
        }
        return diff;
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

    public PoseEstimator getPoseEstimator() {
        return swerveDrivePoseEstimator;
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

    public HolonomicDriveController getHolonomicDriveController() {
        return holonomicDriveController;
    }

    public ChassisSpeeds getChassisSpeeds() {
        return swerveDriveKinematics.toChassisSpeeds(
                swerveModules[0].getState(),
                swerveModules[1].getState(),
                swerveModules[2].getState(),
                swerveModules[3].getState());
    }

    public double getTargetRotationAngle() {
        return targetRotationAngle;
    }

    public void setTargetRotationAngle(double targetRotationAngle) {
        this.targetRotationAngle = targetRotationAngle;
    }

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
