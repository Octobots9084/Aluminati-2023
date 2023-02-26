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

package frc.robot.commands.autonomous;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.robot.Logging;
import frc.robot.subsystems.swerve.DriveTrain;
import frc.robot.util.Gyro;
import frc.robot.util.MathUtil;
import frc.robot.util.shuffleboard.RSTab;

/**
 * Represents a swerve drive style drivetrain.
*/
public class DriveToPosBB extends CommandBase {
    private final DriveTrain driveTrain;
    private final Pose2d target;

    private Pose2d currentPose;
    private double xSpeed, ySpeed;
    private boolean xDone, yDone;

    private final RSTab autoDashboard;

    public DriveToPosBB(Pose2d target, double speed) {
        this.driveTrain = DriveTrain.getInstance();
        this.target = target;
        this.xSpeed = speed;
        this.ySpeed = speed;
        this.currentPose = new Pose2d();

        this.autoDashboard = Logging.autoDashboard;

        // SmartDashboard.putBoolean("X Good?", false);
        // SmartDashboard.putBoolean("Y Good?", false);
        autoDashboard.setEntry("X Good?", false);
        autoDashboard.setEntry("Y Good?", false);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        currentPose = driveTrain.getPoseEstimator().getRobotPose();
        if (xDone || MathUtil.isWithinTolerance(currentPose.getX(), target.getX(), 0.05)) {
            xSpeed = 0;
            xDone = true;
            // SmartDashboard.putBoolean("X Good?", xDone);
            autoDashboard.setEntry("X Good?", xDone);
        }

        if (yDone || MathUtil.isWithinTolerance(currentPose.getY(), target.getY(), 0.05)) {
            ySpeed = 0;
            yDone = true;
            // SmartDashboard.putBoolean("Y Good?", true);
            autoDashboard.setEntry("Y Good?", true);
        }

        if (target.getX() - currentPose.getX() < 0) {
            xSpeed = -xSpeed;
        }

        if (target.getY() - currentPose.getY() < 0) {
            ySpeed = -ySpeed;
        }
        driveTrain.drive(xSpeed, ySpeed, 0, true);//rotSpeed, true);
        Gyro.getInstance().updateRotation2D();
    }

    @Override
    public boolean isFinished() {
        return false;// return yDone && xDone;
    }

    @Override
    public void end(boolean interrupted) {
    }

}