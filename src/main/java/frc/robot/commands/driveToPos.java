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
import frc.robot.util.MathUtil;
import frc.robot.vision.PhotonCameraWrapper;

/**
 * Represents a swerve drive style drivetrain.
*/
public class driveToPos extends CommandBase {
    private final DriveTrain driveTrain;
    private final Pose2d target;
    private final PhotonCameraWrapper photonCameraWrapper;
    private final PIDController drivePid;
    private final PIDController turnPid;

    public driveToPos(Pose2d target) {
        this.driveTrain = DriveTrain.getInstance();
        this.photonCameraWrapper = this.driveTrain.getPoseEstimator().photonCameraWrapper;
        this.target = target;
        this.drivePid = new PIDController(0.08, 0, 0);
        this.turnPid = new PIDController(0.08, 0, 0);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        Pose2d currentPose = driveTrain.getPoseEstimator().getRobotPose();
        double ySpeed = (target.getY()-currentPose.getY());
        double xSpeed = (target.getX()-currentPose.getX());
        // if (MathUtil.isWithinTolerance(xSpeed, 0, 0.05)) {
        //     xSpeed = 0;
        // }
        // if (xSpeed>0.5) {
        //     xSpeed = 0.5;
        // }
        // if (xSpeed<-0.5) {
        //     xSpeed = -0.5;
        // }
        // if (MathUtil.isWithinTolerance(ySpeed, 0, 0.05)) {
        //     ySpeed = 0;
        // }

        // if (ySpeed>0.5) {
        //     ySpeed = 0.5;
        // }

        // if (ySpeed<-0.5) {
        //     ySpeed = -0.5;
        // }

        SmartDashboard.putNumber("CurrentX", currentPose.getX());
        SmartDashboard.putNumber("CurrentY", currentPose.getY());
        SmartDashboard.putNumber("yspeed", xSpeed);
        SmartDashboard.putNumber("xspeed", ySpeed);
        double rotSpeed = 0;//turnPid.calculate(currentPose.getRotation().getRadians(), target.getRotation().getRadians());
        //driveTrain.drive(xSpeed, ySpeed, rotSpeed, true);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
    }

}