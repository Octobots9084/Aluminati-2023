package frc.robot.subsystems.vision;

import java.util.List;

import org.photonvision.PhotonCamera;
import org.photonvision.targeting.PhotonPipelineResult;
import org.photonvision.targeting.PhotonTrackedTarget;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Vision extends SubsystemBase {
	private static Vision INSTANCE;

	public static Vision getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new Vision();
		}
		return INSTANCE;
	}

	private final PhotonCamera tapeCamera = new PhotonCamera("TapeCam");
	private final PhotonCamera tagCamera = new PhotonCamera("TagCam");
	// public boolean hasTarget;
	private PhotonPipelineResult tapeResult;
	private PhotonPipelineResult tagResult;
	// private List<PhotonTrackedTarget> allTargets;

	@Override
	public void periodic() {
		PhotonPipelineResult tapeResult = tapeCamera.getLatestResult(); // Query the latest result from PhotonVision
		PhotonPipelineResult tagResult = tagCamera.getLatestResult(); // Query the latest result from PhotonVision

		if (tapeResult.hasTargets()) {
			this.tapeResult = tapeResult;
		}

		if (tagResult.hasTargets()) {
			this.tagResult = tagResult;
		}

	}

	public PhotonTrackedTarget getTargetWithID(int id) { // Returns the apriltag target with the specified ID (if it exists)
		if (tagResult.hasTargets()) {
			List<PhotonTrackedTarget> targets = tagResult.getTargets(); // Create a list of all currently tracked targets
			for (PhotonTrackedTarget i : targets) {
				if (i.getFiducialId() == id) { // Check the ID of each target in the list
					return i; // Found the target with the specified ID!
				}
			}
		}
		return null; // Failed to find the target with the specified ID
	}

	public PhotonTrackedTarget getBestTarget() {
		if (tapeResult.hasTargets()) {
			return tapeResult.getBestTarget(); // Returns the best (closest) target
		} else {
			return null; // Otherwise, returns null if no targets are currently found
		}
	}

	public PhotonTrackedTarget getBestTargetWithID() { // Returns the apriltag target with the specified ID (if it exists)
		if (tagResult.hasTargets()) {
			return tagResult.getBestTarget(); // Returns the best (closest) target
		} else {
			return null; // Otherwise, returns null if no targets are currently found
		}
	}

	public PhotonTrackedTarget getClosestTarget() {
		if (tagResult.hasTargets() && tapeResult.hasTargets()) {
			if (this.getBestTarget() != null && this.getBestTargetWithID() != null) {
				if (this.getBestTarget().getYaw() > this.getBestTargetWithID().getYaw()) {
					return tapeResult.getBestTarget(); // Returns the best (closest) reflective target
				} else {
					return tagResult.getBestTarget(); // Returns the best (closest) april tag target
				}
			} else {
				return null;
			}
		} else if (tapeResult.hasTargets() && !tagResult.hasTargets()) {
			if (this.getBestTarget() != null) {
				return tapeResult.getBestTarget(); // Returns the best (closest) reflective target
			} else {
				return null;
			}
		} else if (tagResult.hasTargets() && !tapeResult.hasTargets()) {
			if (this.getBestTargetWithID() != null) {
				return tagResult.getBestTarget(); // Returns the best (closest) april tag target
			} else {
				return null;
			}
		} else {
			return null; // Otherwise, returns null if no targets are currently found
		}
	}

	public boolean getTapeCamHasTarget() {
		return tapeResult.hasTargets(); // Returns whether a target was found
	}

	public boolean getTagCamHasTarget() {
		return tagResult.hasTargets(); // Returns whether a target was found
	}
}
