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

package frc.robot;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.pathplanner.lib.server.PathPlannerServer;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.arm.manual.ExtensionControl;
import frc.robot.commands.arm.basic.instant.ArmPosInstant;
import frc.robot.commands.arm.manual.CGControl;
import frc.robot.commands.spatula.SetSpatulaVoltageAndPos;
import frc.robot.commands.swerve.SwerveControl;
import frc.robot.robot.ButtonConfig;
import frc.robot.robot.ControlMap;
import frc.robot.robot.DriverButtonConfig;
import frc.robot.robot.Logging;
import frc.robot.subsystems.arm.ArmExtension;
import frc.robot.subsystems.arm.ArmPositions;
import frc.robot.subsystems.arm.CaliGirls;
import frc.robot.subsystems.arm.Roller;
import frc.robot.subsystems.spatula.RollingPins;
import frc.robot.subsystems.spatula.SpatulaFlip;
import frc.robot.subsystems.swerve.DriveTrain;
import frc.robot.util.Gyro;

public class Robot extends TimedRobot {
    public static double autoStartTime = 0.0;
    private static boolean autoFlag = false;

    public static boolean isAutoFlag() {
        return autoFlag;
    }

    @Override
    public void disabledPeriodic() {
        CommandScheduler.getInstance().cancelAll();

        Logging.updateLogging();

        // SmartDashboard.putNumber("Extens2ion", ArmExtension.getInstance().getPosition());
        // SmartDashboard.putNumber("Arm Rotat2ion", CaliGirls.getInstance().getTopPos());
        // SmartDashboard.putNumber("Claw Rot2ation", CaliGirls.getInstance().getBottomPos());

    }

    @Override
    public void robotInit() {

        initializeAllSubsystems();

        // var drive = Shuffleboard.getTab("Drive");
        // drive.add(field2d)
        //         .withSize(6, 4)
        //         .withPosition(0, 0)
        //         .withWidget("Field");

        // Initialize custom loops

        resetRobotPoseAndGyro();
        var threader = Executors.newSingleThreadScheduledExecutor();
        threader.scheduleWithFixedDelay(new Thread(() -> Gyro.getInstance().updateRotation2D()), 0, 5,
                TimeUnit.MILLISECONDS);
        LiveWindow.disableAllTelemetry();
        LiveWindow.setEnabled(false);
        Logging.addAutoChooser();
        // PathPlannerServer.startServer(5811);

        if (!ControlMap.DRIVER_BUTTONS.getRawButton(14)) {
            // SmartDashboard.putBoolean("14", true);
            new DriverButtonConfig().initTeleop();
        } else {
            // SmartDashboard.putBoolean("14", false);
            new ButtonConfig().initTeleop();
        }

        initializeAllSubsystems();
    }

    @Override
    public void robotPeriodic() {
        // DriveTrain.getInstance().getPoseEstimator().updateOdometry();
        // SmartDashboard.putNumber("X-Pos", DriveTrain.getInstance().getPoseEstimator().getRobotPose().getX());
        // SmartDashboard.putNumber("Y-Pos", DriveTrain.getInstance().getPoseEstimator().getRobotPose().getY());
        // SmartDashboard.putNumber("Rot Deg",
        // DriveTrain.getInstance().getPoseEstimator().getRobotPose().getRotation().getDegrees());
    }

    @Override
    public void teleopPeriodic() {
        DriveTrain.getInstance().updateSwerveStates();
        CommandScheduler.getInstance().run();

        Logging.updateLogging();
    }

    @Override
    public void teleopInit() {
        // SmartDashboard.putNumber("Initialized", 1);
        // SmartDashboard.putBoolean("14actual", ControlMap.DRIVER_BUTTONS.getRawButton(14));
        CommandScheduler.getInstance().cancelAll();
        DriveTrain.getInstance().setUseDriverAssist(false);
        initializeDefaultCommands();
        ArmExtension.getInstance().setOffset();
        if (!autoFlag) {
            resetRobotPoseAndGyro();
        }
        Robot.autoFlag = false;
        DriveTrain.getInstance().setUseDriverAssist(true);
        Gyro.getInstance().setAngleAdjustment(180);

        //DriveTrain.getInstance().getPoseEstimator().useAprilTags.set(true);


    }

    @Override
    public void autonomousInit() {
        Robot.autoFlag = true;
        
        
        //new driveToPos(new Pose2d(14.16, 1.4, new Rotation2d()));
        //CommandScheduler.getInstance().schedule(PathPlannerAutos.BalanceChargeStation());
        // CommandScheduler.getInstance().schedule(PathPlannerAutos.TestAutoOne());
        //CommandScheduler.getInstance().schedule(new driveToPos(new Pose2d(0.0, FieldConstants.length/2, new Rotation2d(0,0))));

        Robot.autoStartTime = Timer.getFPGATimestamp();
        Gyro.getInstance().setAngleAdjustment(0);
        resetRobotPoseAndGyro();
        // DriveTrain.getInstance().setUseDriverAssist(true);
        try {
            var command = Logging.getAutoChooser().getSelected();
            if (command != null) {
                CommandScheduler.getInstance().schedule(command);
            }
        } catch (Exception e) {
            // I fuxed it
        }

    }

    @Override
    public void autonomousPeriodic() {
        DriveTrain.getInstance().updateSwerveStates();
        DriveTrain.getInstance().getPoseEstimator().updateOdometry();
        DriveTrain.getInstance().updateSwerveStates();
        CommandScheduler.getInstance().run();
        Logging.updateLogging();
    }

    @Override
    public void disabledInit() {
        CommandScheduler.getInstance().cancelAll();
    }

    private void initializeAllSubsystems() {

        // CaliGirls.getInstance();
        // IntakeClaws.getInstance();
        DriveTrain.getInstance();
        CaliGirls.getInstance();
        ArmExtension.getInstance();
        Roller.getInstance();
        Logging.getInstance();
        SpatulaFlip.getInstance();
        RollingPins.getInstance();
        new ArmPosInstant(ArmPositions.STOW);
    }

    private void resetRobotPoseAndGyro() {
        Gyro.getInstance().resetGyro();
        DriveTrain.getInstance().setTargetRotationAngle(0);
        // DriveTrain.getInstance().drive(0, 0, 0, true);
        DriveTrain.getInstance().getPoseEstimator().resetPose(new Pose2d(0,0, new Rotation2d()));
    }

    private void initializeDefaultCommands() {
        CaliGirls.getInstance().lastPosBottom = CaliGirls.getInstance().getBottomPos();
        CaliGirls.getInstance().lastPosTop = CaliGirls.getInstance().getTopPos();
        ArmExtension.getInstance().lastpos = ArmExtension.getInstance().getPosition();
        CommandScheduler.getInstance().setDefaultCommand(DriveTrain.getInstance(), new SwerveControl());
        CommandScheduler.getInstance().setDefaultCommand(CaliGirls.getInstance(), new CGControl());
        CommandScheduler.getInstance().setDefaultCommand(ArmExtension.getInstance(), new ExtensionControl());
        CommandScheduler.getInstance().schedule(new SetSpatulaVoltageAndPos(0, 0.45));
    }
}