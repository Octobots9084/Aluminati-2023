/*
 * This file is part of OctoSwerve-Revamp, licensed under the GNU General Public License (GPLv3).
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

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import org.octobots.robot.commands.SwerveControl;
import org.octobots.robot.swerve.DriveTrain;
import org.octobots.robot.util.Gyro;

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

        var drive = Shuffleboard.getTab("Drive");
        drive.add(field2d)
                .withSize(6, 4)
                .withPosition(0, 0)
                .withWidget("Field");

        // Initialize custom loops

        resetRobotPoseAndGyro();
        var threader = Executors.newSingleThreadScheduledExecutor();
        threader.scheduleWithFixedDelay(new Thread(() -> Gyro.getInstance().updateRotation2D()), 0, 5, TimeUnit.MILLISECONDS);
        LiveWindow.disableAllTelemetry();
        LiveWindow.setEnabled(false);
        this.chooser = new SendableChooser<>();
    }

    @Override
    public void robotPeriodic() {
        DriveTrain.getInstance().updateSwerveStates();
        CommandScheduler.getInstance().run();
    }

    @Override
    public void teleopInit() {
        CommandScheduler.getInstance().cancelAll();
        initializeAllSubsystems();
        initializeDefaultCommands();

        if (!autoFlag) {
            resetRobotPoseAndGyro();
        }
        this.autoFlag = false;
    }

    @Override
    public void autonomousInit() {
        this.autoFlag = true;
        initializeAllSubsystems();
        initializeDefaultCommands();

        resetRobotPoseAndGyro();
        Robot.autoStartTime = Timer.getFPGATimestamp();
        try {
        } catch (Exception ignored) {
            // If this fails we need robot code to still try to work in teleop,
            // so unless debugging there is no case where we want this to throw anything.
        }

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