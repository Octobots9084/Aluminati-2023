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

package frc.robot.commands.swerve;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.robot.Logging;
import frc.robot.subsystems.swerve.DriveTrain;

public class SetDriverAssist extends InstantCommand {
    private boolean useDriverAssist;

    public SetDriverAssist(boolean useDriverAssist) {
        this.useDriverAssist = useDriverAssist;

    }

    @Override
    public void initialize() {
        // SmartDashboard.putBoolean("Driver Assist: ", this.useDriverAssist);
        Logging.driveDashboard.setEntry("Driver Assist: ", this.useDriverAssist);
        DriveTrain.getInstance().setUseDriverAssist(this.useDriverAssist);
    }

}
