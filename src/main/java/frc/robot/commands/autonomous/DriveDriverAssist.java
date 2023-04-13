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

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.robot.ControlMap;
import frc.robot.subsystems.swerve.DriveTrain;
import frc.robot.util.Gyro;
import frc.robot.util.MathUtil;

/**
 * Represents a swerve drive style drivetrain.
*/
public class DriveDriverAssist extends CommandBase {
    double startTime;
    double currentTime;
    @Override
    public void initialize() {
        startTime = Timer.getFPGATimestamp();
    }

    @Override
    public void execute() {
        currentTime = Timer.getFPGATimestamp();
        DriveTrain.getInstance().drive(0, 0, DriveTrain.getInstance().getRotationSpeed(), true);
    }

    @Override
    public boolean isFinished() {
        // tolerances are a bit low
        

        return !(MathUtil.isWithinTolerance(currentTime, startTime, 5));
    }

    @Override
    public void end(boolean interrupted) {
    }

}