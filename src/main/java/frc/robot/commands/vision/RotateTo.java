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

package frc.robot.commands.vision;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.swerve.DriveTrain;


/**
 * Represents a swerve drive style drivetrain.
*/
public class RotateTo extends CommandBase {
    private final DriveTrain dt;
    private final double angle;

    public RotateTo(double angle) {
        this.dt = DriveTrain.getInstance();
        this.angle = angle;
        addRequirements(this.dt);
    }

    @Override
    public void initialize() {
        dt.setTargetRotationAngle(angle);
    }

    @Override
    public boolean isFinished() {
        return true;
        //return dt.getRotationSpeed() == 0;
    }

    @Override
    public void execute() {
       // dt.drive(0, 0, dt.getRotationSpeed(), true);
    }

    @Override
    public void end(boolean interrupted) {
        dt.drive(0, 0, 0, false);
    }

}