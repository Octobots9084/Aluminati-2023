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

package frc.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.subsystems.swerve.DriveTrain;
import frc.subsystems.vision.PIVision;
import frc.util.MathUtil;

public class GoTowardsTargetWithID extends CommandBase {
    private final DriveTrain driveTrain;
    private final PIVision vision;

    public GoTowardsTargetWithID() {
        // Initialization
        this.driveTrain = DriveTrain.getInstance();
        this.vision = PIVision.getInstance();
    }

    @Override
    public void initialize() {
//      SmartDashboard.putNumber("Yaw", Math.toRadians(vision.getTargetWithID(0).getYaw()));
    }

    @Override
    public void execute() {
        try {
            if (vision.getHasTarget()) {
                driveTrain.drive(vision.getTargetWithID(0).getBestCameraToTarget().getX(), vision.getTargetWithID(0).getBestCameraToTarget().getY(), 0,true);
                driveTrain.drive(0, 0, Math.toRadians(vision.getTargetWithID(0).getYaw()) * 3, true);
            } else {
                isFinished();
            }
        } catch (Exception e) {
            //
        }
    }

    @Override
    public boolean isFinished() {
        try {
            return vision.getTargetWithID(0).getBestCameraToTarget().getX() < 1 && vision.getTargetWithID(0).getBestCameraToTarget().getY() < 1;
        } catch (Exception e) {
            //
        }

        return true;
    }

    @Override
    public void end(boolean interrupted) {
        driveTrain.drive(0, 0, 0, false);
    }

}

