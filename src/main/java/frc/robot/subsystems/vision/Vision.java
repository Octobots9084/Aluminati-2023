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

	private final PhotonCamera tapeCamera = new PhotonCamera("Microsoft_LifeCam_HD-3000");
	// public boolean hasTarget;
	private PhotonPipelineResult tapeResult;
	// private List<PhotonTrackedTarget> allTargets;

	@Override
	public void periodic() {
		PhotonPipelineResult tapeResult = tapeCamera.getLatestResult(); // Query the latest result from PhotonVision

		if (tapeResult.hasTargets()) {
			this.tapeResult = tapeResult;
		}

	}

	

	public PhotonTrackedTarget getBestTarget() {
		if (tapeResult.hasTargets()) {
			return tapeResult.getBestTarget(); // Returns the best (closest) target
		} else {
			return null; // Otherwise, returns null if no targets are currently found
		}
	}

	public boolean getTapeCamHasTarget() {
		if (tapeResult==null) {
			return false;
		}
		return tapeResult.hasTargets(); 
		
		// Returns whether a target was found
	}

}
