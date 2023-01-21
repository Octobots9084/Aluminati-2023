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

package frc.Autonomous;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.InstantCommand;


/**
 * Represents a swerve drive style drivetrain.
*/
public class TestCommand extends InstantCommand {
    @Override
    public void initialize() {
        SmartDashboard.putNumber("testcommand", 2);
    }

}