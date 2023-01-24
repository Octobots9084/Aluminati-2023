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

package frc.subsystems.vision;

import java.util.List;

import org.photonvision.PhotonCamera;
import org.photonvision.targeting.PhotonPipelineResult;
import org.photonvision.targeting.PhotonTrackedTarget;

import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class PIVision extends SubsystemBase {
	private static PIVision INSTANCE;

	public static PIVision getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new PIVision();
		}
		return INSTANCE;
	}

	public static final double CAMERA_HEIGHT_METERS = Units.inchesToMeters(0);
	public static final double CAMERA_PITCH_RADIANS = Units.degreesToRadians(0);
	public static final double TARGET_HEIGHT_METERS = Units.inchesToMeters(18.5);
	private final PhotonCamera camera = new PhotonCamera("Microsoft_LifeCam_HD-3000");
	public static boolean hasTarget;
	private PhotonPipelineResult result;

	@Override
	public void periodic() {
		PhotonPipelineResult result = camera.getLatestResult(); // Query the latest result from PhotonVision
		this.hasTarget = result.hasTargets(); // If the camera has detected an apriltag target, the hasTarget boolean
												// will
												// be true
		if (hasTarget) {
			this.result = result;
		}
	}

	public PhotonTrackedTarget getTargetWithID(int id) { // Returns the apriltag target with the specified ID (if it
															// exists)
		if (this.getHasTarget()) {
			List<PhotonTrackedTarget> targets = result.getTargets(); // Create a list of all currently tracked targets
			for (PhotonTrackedTarget i : targets) {
				if (i.getFiducialId() == id) { // Check the ID of each target in the list
					return i; // Found the target with the specified ID!
				}
			}
		}
		return null; // Failed to find the target with the specified ID
	}

	public PhotonTrackedTarget getBestTarget() {
		if (hasTarget) {
			return result.getBestTarget(); // Returns the best (closest) target
		} else {
			return null; // Otherwise, returns null if no targets are currently found
		}
	}

	public boolean getHasTarget() {
		return hasTarget; // Returns whether a target was found
	}
}
