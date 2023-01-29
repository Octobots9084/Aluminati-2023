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

package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.swerve.DriveTrain;
import frc.robot.util.Gyro;
import frc.robot.util.MathUtil;
import frc.robot.util.PIDConfig;
import frc.robot.vision.PhotonCameraWrapper;

/**
 * Represents a swerve drive style drivetrain.
*/
public class driveToPos extends CommandBase {
    private final DriveTrain driveTrain;
    private final Pose2d target;

    private double xSpeed;
    private double ySpeed;
    private double rotSpeed;
    private Pose2d currentPose;
    private PIDController drivePids;
    private PIDController turnPids;
    public driveToPos(Pose2d target) {
        this.driveTrain = DriveTrain.getInstance();
        this.target = target;
        this.xSpeed = 0;
        this.ySpeed = 0;
        this.rotSpeed = 0;
        this.currentPose = new Pose2d();
        // P is a little high
        this.drivePids = new PIDController(1, 0, 0.0000);
        this.turnPids = new PIDController(0.5, 0, 0.00);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        currentPose = driveTrain.getPoseEstimator().getRobotPose();
        SmartDashboard.putNumber("XPo2s: ", currentPose.getX());
        SmartDashboard.putNumber("YPos2e1: ", currentPose.getY());
        SmartDashboard.putNumber("Ro1t2: ", currentPose.getRotation().getDegrees());
        ySpeed = drivePids.calculate(target.getY(),currentPose.getY());
        xSpeed = drivePids.calculate(currentPose.getX(),target.getX());
        rotSpeed = turnPids.calculate(target.getRotation().getRadians(),currentPose.getRotation().getRadians());

        if (xSpeed>0.3) {
            xSpeed = 0.3;
        }
        if (xSpeed<-0.3) {
            xSpeed = -0.3;
        }
        if (MathUtil.isWithinTolerance(currentPose.getX(), target.getX(), 0.05)) {
            ySpeed = 0;
        }

        if (ySpeed>0.3) {
            ySpeed = 0.3;
        }
        if (ySpeed<-0.3) {
            ySpeed = -0.3;
        }
        if (MathUtil.isWithinTolerance(currentPose.getY(), target.getY(), 0.05)) {
            ySpeed = 0;
        }

        if (rotSpeed>0.3) {
            rotSpeed = 0.3;
        }
        if (rotSpeed<-0.3) {
            rotSpeed = -0.3;
        }
        if (MathUtil.isWithinTolerance(MathUtil.wrapToCircle(currentPose.getRotation().getRadians()), MathUtil.wrapToCircle(target.getRotation().getRadians()), 0.03)) {
            ySpeed = 0;
        }

        // Check driver assist and drive
        if (rotSpeed == 0) {
            driveTrain.drive(xSpeed, ySpeed, driveTrain.getRotationSpeed(), true);
        } else {
            driveTrain.drive(xSpeed, ySpeed, rotSpeed, true);
            Gyro.getInstance().updateRotation2D();
            driveTrain.setTargetRotationAngle(Gyro.getInstance().getRotation2d().getDegrees());
        }
    }

    @Override
    public boolean isFinished() {
        // tolerances are a bit low
        if (MathUtil.isWithinTolerance(currentPose.getY(), target.getY(), 0.05) && MathUtil.isWithinTolerance(currentPose.getX(), target.getX(), 0.05) && MathUtil.isWithinTolerance(MathUtil.wrapToCircle(currentPose.getRotation().getRadians()), MathUtil.wrapToCircle(target.getRotation().getRadians()), 0.03)) {
            return true;
        }
        
        return false;
    }

    @Override
    public void end(boolean interrupted) {
    }

}