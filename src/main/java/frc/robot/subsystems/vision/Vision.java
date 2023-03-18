package frc.robot.subsystems.vision;

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

	private final PhotonCamera camera = new PhotonCamera("OctoLight");
	public boolean hasTarget;
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

	public PhotonCamera getCamera() {
		return camera;
	}
}
