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

import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.commands.TurnToTrackedTarget;
import frc.commands.TurnToTrackedTargetWithID;

public class ButtonConfiguration {
    public void initTeleop() {
        // DRIVER LEFT

        new JoystickButton(ControlMap.DRIVER_LEFT, 1).whileTrue(new TurnToTrackedTarget());

        new JoystickButton(ControlMap.DRIVER_LEFT, 2).whileTrue(new TurnToTrackedTargetWithID());

        // DRIVER RIGHT

        // DRIVER BUTTONS

        // CO-DRIVER LEFT

        // CO-DRIVER RIGHT

        // CO-DRIVER BUTTONS
    }
}
