/*
 * This file is part of GradleRIO-Redux-example, licensed under the GNU General Public License (GPLv3).
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

package org.octobots.robot;

import edu.wpi.first.math.MatBuilder;
import edu.wpi.first.math.Nat;
import edu.wpi.first.math.StateSpaceUtil;
import edu.wpi.first.math.estimator.KalmanFilterLatencyCompensator;
import edu.wpi.first.math.estimator.SwerveDrivePoseEstimator;
import edu.wpi.first.math.estimator.UnscentedKalmanFilter;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.math.numbers.N1;
import edu.wpi.first.math.numbers.N3;
import edu.wpi.first.wpilibj.Timer;
import org.octobots.robot.Gyro;
import org.octobots.robot.SwerveModule;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantLock;

public class PoseEstimator {
    private final SwerveDrivePoseEstimator swerveDrivePoseEstimator;
    private final AtomicReference<Pose2d> robotPose = new AtomicReference<>();
    private final ReentrantLock resetLock = new ReentrantLock();
    private final Gyro gyro;
    private final SwerveModule[] swerveModules;

    public PoseEstimator(Gyro gyro, SwerveDriveKinematics swerveDriveKinematics, SwerveModule[] swerveModules) {
        this.gyro = gyro;
        this.swerveModules = swerveModules;
        this.swerveDrivePoseEstimator = new SwerveDrivePoseEstimator(
                gyro.getRotation2d(),
                new Pose2d(0, 0, gyro.getRotation2d()),
                swerveDriveKinematics,
                //Standard deviations of model states. Increase these numbers to trust your model's state estimates less.
                //This matrix is in the form [x, y, theta]^T, with units in meters and radians.
                new MatBuilder<>(Nat.N3(), Nat.N1()).fill(0.2, 0.2, 0.1),
                // Standard deviations of the encoder and gyro measurements. Increase these numbers to trust sensor
                // readings from encoders and gyros less. This matrix is in the form [theta], with units in radians.
                new MatBuilder<>(Nat.N1(), Nat.N1()).fill(0.01),
                //Standard deviations of the vision measurements. Increase these numbers to trust global measurements
                //from vision less. This matrix is in the form [x, y, theta]^T, with units in meters and radians.
                new MatBuilder<>(Nat.N3(), Nat.N1()).fill(0.01, 0.01, 0.01) //Vision Measurement stdev
        );

        var e = Executors.newScheduledThreadPool(2);
        e.scheduleWithFixedDelay(this::updateOdometry, 5, 15, TimeUnit.MILLISECONDS);
        robotPose.set(new Pose2d());
    }

    public Pose2d getRobotPose() {
        return robotPose.get();
    }

    public void updateOdometry() {
        resetLock.lock();
        SwerveModuleState[] swerveModuleStates = Arrays.stream(swerveModules).map(SwerveModule::getState).toArray(a -> new SwerveModuleState[swerveModules.length]);
        try {
            var pose2d = swerveDrivePoseEstimator.updateWithTime(
                    Timer.getFPGATimestamp(),
                    gyro.getRotation2d(),
                    swerveModuleStates
            );
            robotPose.set(pose2d);
        } finally {
            resetLock.unlock();
        }
    }

    public void resetPose() {
        resetLock.lock();
        try {
            swerveDrivePoseEstimator.resetPosition(new Pose2d(0, 0, new Rotation2d(0)), new Rotation2d(0));
        } finally {
            resetLock.unlock();
        }
    }

    public void resetPose(Pose2d pose2d) {
        resetLock.lock();
        try {
            Field observerField = SwerveDrivePoseEstimator.class.getDeclaredField("m_observer");
            Field latencyCompensatorField = SwerveDrivePoseEstimator.class.getDeclaredField("m_latencyCompensator");
            observerField.setAccessible(true);
            latencyCompensatorField.setAccessible(true);
            @SuppressWarnings("unchecked")
            var observer = (UnscentedKalmanFilter<N3, N3, N1>) observerField.get(swerveDrivePoseEstimator);
            @SuppressWarnings("unchecked")
            var latencyCompensator = (KalmanFilterLatencyCompensator<N3, N3, N1>) latencyCompensatorField.get(swerveDrivePoseEstimator);
            observer.reset();
            latencyCompensator.reset();

            observer.setXhat(StateSpaceUtil.poseTo3dVector(pose2d));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
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
