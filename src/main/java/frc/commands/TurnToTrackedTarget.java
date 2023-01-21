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

public class TurnToTrackedTarget extends CommandBase {
	private final DriveTrain driveTrain;
	private final PIVision vision;

	public TurnToTrackedTarget() {
		// Initialization
		this.driveTrain = DriveTrain.getInstance();
		this.vision = PIVision.getInstance();
		// addRequirements(this.driveTrain);
	}

	@Override
	public void initialize() {
		SmartDashboard.putNumber("madeit", 0);
		SmartDashboard.putNumber("Times executed", SmartDashboard.getNumber("Times executed", 0) + 1);
	}

	@Override
	public void execute() {
		try {
			driveTrain.setTargetRotationAngle(vision.getBestTarget().getYaw());
			driveTrain.drive(0, 0, driveTrain.getRotationSpeed(), true);
		} catch (Exception e) {
			//
		}

		SmartDashboard.putNumber("madeit", 1000);
	}

	@Override
	public boolean isFinished() {
		return MathUtil.isWithinTolerance(vision.getBestTarget().getYaw(), 0, 0.2);
	}

	@Override
	public void end(boolean interrupted) {
		driveTrain.drive(0, 0, 0, false);
	}
}
