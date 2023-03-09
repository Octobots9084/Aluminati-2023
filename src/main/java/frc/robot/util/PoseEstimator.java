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

package frc.robot.util;

import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantLock;

import org.photonvision.EstimatedRobotPose;

import edu.wpi.first.math.MatBuilder;
import edu.wpi.first.math.Nat;
import edu.wpi.first.math.estimator.SwerveDrivePoseEstimator;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.swerve.SwerveModule;
import frc.robot.subsystems.vision.LeftCameraWrapper;
import frc.robot.subsystems.vision.RightCameraWrapper;

public class PoseEstimator {
    public final SwerveDrivePoseEstimator swerveDrivePoseEstimator;
    private final AtomicReference<Pose2d> robotPose = new AtomicReference<>();
    private final ReentrantLock resetLock = new ReentrantLock();
    private final Gyro gyro;
    private final SwerveModule[] swerveModules;
    public LeftCameraWrapper leftCameraWrapper;
    public RightCameraWrapper rightCameraWrapper;

    public PoseEstimator(Gyro gyro, SwerveDriveKinematics swerveDriveKinematics, SwerveModule[] swerveModules) {
        this.leftCameraWrapper = new LeftCameraWrapper();
        this.rightCameraWrapper = new RightCameraWrapper();
        this.gyro = gyro;
        this.swerveModules = swerveModules;
        SwerveModulePosition[] swerveModulePositions = {
                swerveModules[2].getModulePosition(),
                swerveModules[3].getModulePosition(),
                swerveModules[0].getModulePosition(),
                swerveModules[1].getModulePosition()
        };
        this.swerveDrivePoseEstimator = new SwerveDrivePoseEstimator(
                swerveDriveKinematics,
                gyro.getRotation2d(),
                swerveModulePositions,
                new Pose2d(0, 0, gyro.getRotation2d()),
                // //Standard deviations of model states. Increase these numbers to trust your model's state estimates less.
                // //This matrix is in the form [x, y, theta]^T, with units in meters and radians.
                new MatBuilder<>(Nat.N3(), Nat.N1()).fill(0.2, 0.2, 0.1),
                // // Standard deviations of the encoder and gyro measurements. Increase these numbers to trust sensor
                // // readings from encoders and gyros less. This matrix is in the form [theta], with units in radians.
                // new MatBuilder<>(Nat.N1(), Nat.N1()).fill(0.01),
                // //Standard deviations of the vision measurements. Increase these numbers to trust global measurements
                // //from vision less. This matrix is in the form [x, y, theta]^T, with units in meters and radians.
                new MatBuilder<>(Nat.N3(), Nat.N1()).fill(0.01, 0.01, 0.01) //Vision Measurement stdev
        );

        var e = Executors.newScheduledThreadPool(2);
        e.scheduleWithFixedDelay(this::updateOdometry, 5, 15, TimeUnit.MILLISECONDS);
        robotPose.set(new Pose2d());
        this.swerveDrivePoseEstimator.getEstimatedPosition();
    }

    public Pose2d getRobotPose() {
        SmartDashboard.putNumber("RotationPoseEstimation", robotPose.get().getRotation().getDegrees());
        return robotPose.get();
    }

    public void updateOdometry() {
        resetLock.lock();
        SwerveModulePosition[] swerveModulePositions = {
                swerveModules[2].getModulePosition(),
                swerveModules[3].getModulePosition(),
                swerveModules[0].getModulePosition(),
                swerveModules[1].getModulePosition()
        };
        Rotation2d rotation = new Rotation2d();
        if (DriverStation.getAlliance()==DriverStation.Alliance.Blue) {
            rotation = new Rotation2d(gyro.getRotation2d().getRadians());
        } else {
            rotation = new Rotation2d(gyro.getRotation2d().getRadians());
        }
        var pose2d = swerveDrivePoseEstimator.updateWithTime(
                Timer.getFPGATimestamp(),
                rotation,
                swerveModulePositions);
        //pose2d = new Pose2d(pose2d.getX(), pose2d.getY(), pose2d.getRotation())
        robotPose.set(pose2d);
        // SmartDashboard.putNumber("XPo31s: ", robotPose.get().getX());
        // SmartDashboard.putNumber("YP3ose1: ", robotPose.get().getY());
        // SmartDashboard.putNumber("Ro31t: ", robotPose.get().getRotation().getDegrees());

        // driveDashboard.setEntry("X-Pos", robotPose.get().getX());
        // driveDashboard.setEntry("Y-Pos", robotPose.get().getY());
        // driveDashboard.setEntry("Rot Deg", robotPose.get().getRotation().getDegrees());

        try {
            Optional<EstimatedRobotPose> result = leftCameraWrapper.getEstimatedGlobalPose(getRobotPose());
            if (result.isPresent()) {
                swerveDrivePoseEstimator.addVisionMeasurement(result.get().estimatedPose.toPose2d(),
                        Timer.getFPGATimestamp());
            }

        } catch (Exception e) {
            //deez
        } 

        try {
            Optional<EstimatedRobotPose> result = rightCameraWrapper.getEstimatedGlobalPose(getRobotPose());
            if (result.isPresent()) {
                swerveDrivePoseEstimator.addVisionMeasurement(result.get().estimatedPose.toPose2d(),
                        Timer.getFPGATimestamp());
            }

        } catch (Exception e) {
            //deez
        } 
        resetLock.unlock();

    }

    public void resetPose() {
        resetLock.lock();
        try {
            SwerveModulePosition[] swerveModulePositions = {
                    swerveModules[2].getModulePosition(),
                    swerveModules[3].getModulePosition(),
                    swerveModules[0].getModulePosition(),
                    swerveModules[1].getModulePosition()
            };
            Rotation2d rotation = new Rotation2d();
            if (DriverStation.getAlliance()==DriverStation.Alliance.Blue) {
                rotation = new Rotation2d(gyro.getRotation2d().getRadians());
            } else {
                rotation = new Rotation2d(gyro.getRotation2d().getRadians());
            }
            swerveDrivePoseEstimator.resetPosition(rotation, swerveModulePositions, this.getRobotPose());
        } finally {
            resetLock.unlock();
        }
    }

    public void resetPose(Pose2d pose2d) {
        resetLock.lock();
        try {
            SwerveModulePosition[] swerveModulePositions = {
                    swerveModules[2].getModulePosition(),
                    swerveModules[3].getModulePosition(),
                    swerveModules[0].getModulePosition(),
                    swerveModules[1].getModulePosition()
            };
            Rotation2d rotation = new Rotation2d();
            if (DriverStation.getAlliance()==DriverStation.Alliance.Blue) {
                rotation = new Rotation2d(gyro.getRotation2d().getRadians());
            } else {
                rotation = new Rotation2d(gyro.getRotation2d().getRadians());
            }
            
            swerveDrivePoseEstimator.resetPosition(rotation, swerveModulePositions, pose2d);
        } finally {
            resetLock.unlock();
        }
    }

    public void updateRobotPose(Pose2d pose2d) {
        resetLock.lock();
        try {
            swerveDrivePoseEstimator.addVisionMeasurement(pose2d, Timer.getFPGATimestamp());
        } finally {
            resetLock.unlock();
        }
    }

}
