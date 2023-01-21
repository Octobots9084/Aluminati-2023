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

public class TurnToTrackedTargetWithID extends CommandBase {
	private final DriveTrain driveTrain;
	private final PIVision vision;

	public TurnToTrackedTargetWithID() {
		// Initialization
		this.driveTrain = DriveTrain.getInstance();
		this.vision = PIVision.getInstance();
		// addRequirements(this.driveTrain);
	}

	@Override
	public void initialize() {
		// driveTrain.setTargetRotationAngle(vision.getBestTarget().getYaw());
		SmartDashboard.putNumber("Yaw", vision.getTargetWithID(0).getYaw());

	}

	@Override
	public void execute() {
		try {
			if (!MathUtil.isWithinTolerance(Math.toRadians(vision.getTargetWithID(0).getYaw()) * 2, 0, 0.002)) {
				driveTrain.drive(0, 0, Math.toRadians(vision.getTargetWithID(0).getYaw()) * 2, true);
			} else {
				isFinished();
			}
		} catch (Exception e) {
			//
		}
	}

	@Override
	public boolean isFinished() {
		SmartDashboard.putNumber("Yaw", vision.getTargetWithID(0).getYaw());
		return MathUtil.isWithinTolerance(vision.getTargetWithID(0).getYaw(), 0, 0.002);
	}

	@Override
	public void end(boolean interrupted) {
		SmartDashboard.putNumber("Yaw", vision.getTargetWithID(0).getYaw());
		driveTrain.drive(0, 0, 0, false);
	}
}
