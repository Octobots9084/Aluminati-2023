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

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import org.octobots.robot.commands.SwerveControl;
import org.octobots.robot.swerve.DriveTrain;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Robot extends TimedRobot {
    public static double autoStartTime = 0.0;
    private final Field2d field2d = new Field2d();
    private SendableChooser<Command> chooser;
    private boolean autoFlag = false;
    private boolean ran = false;

    @Override
    public void disabledPeriodic() {
        DriveTrain.getInstance().drive(0, 0, 0, true);
        CommandScheduler.getInstance().cancelAll();
    }

    @Override
    public void robotInit() {
        initializeAllSubsystems();
        initializeDefaultCommands();
        Gyro.getInstance().resetGyro();
        resetRobotPoseAndGyro();
        var threader = Executors.newSingleThreadScheduledExecutor();
        threader.scheduleWithFixedDelay(new Thread(() -> Gyro.getInstance().updateRotation2D()), 0, 5, TimeUnit.MILLISECONDS);
        LiveWindow.disableAllTelemetry();
        LiveWindow.setEnabled(false);
        //custom slower loop to run swerve optimized angle
        addPeriodic(this::swervePeriodic, 0.05, 0.01);
        SmartDashboard.putNumber("period", getPeriod());

    }

    @Override
    public void robotPeriodic() {
        CommandScheduler.getInstance().run();
    }

    public void swervePeriodic() {
        DriveTrain.getInstance().updateSwerveStates();
    }

    @Override
    public void teleopInit() {
        CommandScheduler.getInstance().cancelAll();
//        new ButtonConfiguration().initTeleop();
        initializeAllSubsystems();
        initializeDefaultCommands();

        if (!autoFlag) {
            resetRobotPoseAndGyro();
        }
        this.autoFlag = false;
    }

    @Override
    public void disabledInit() {
        CommandScheduler.getInstance().cancelAll();
    }

    private void initializeAllSubsystems() {
        DriveTrain.getInstance();
    }

    private void resetRobotPoseAndGyro() {
        Gyro.getInstance().resetGyro();
        DriveTrain.getInstance().drive(0, 0, 0, true);
    }

    private void initializeDefaultCommands() {
        CommandScheduler.getInstance().setDefaultCommand(DriveTrain.getInstance(), new SwerveControl());
    }
}

