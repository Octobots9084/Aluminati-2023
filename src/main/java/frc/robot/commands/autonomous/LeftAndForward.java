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

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.autonomous.arm.AutoConeTop;
import frc.robot.subsystems.swerve.DriveTrain;

/**
 * Represents a swerve drive style drivetrain.
*/
public class LeftAndForward extends SequentialCommandGroup {
    public LeftAndForward() {
        addCommands(
            new AutoConeTop(),
            new DriveLeft(0.7).withTimeout(1.2),
            new DriveBackwards(0.8).withTimeout(1),
            new InstantCommand(() -> DriveTrain.getInstance().drive(0,0, 0, false))      
        
        );

    }
    

}