package frc.robot.util;

import org.photonvision.PhotonCamera;
import org.photonvision.PhotonUtils;

public class RetrotapeUtil {
    private final PhotonCamera camera;
    public final double LL_ANGLE = 0;
    public final double GOAL_HEIGHT = 0;
    public final double ROBOT_HEIGHT = 0;

    // public RetrotapeUtil() {
    //     this.camera = new PhotonCamera("gloworm");
    // }
    
    // public double getTy() {
    //     var result = camera.getLatestResult();
    //     return result.hasTargets() ? result.targets.get(0).getPitch() : 0;
    // }

    // public double getTx() {
    //     var result = camera.getLatestResult();
    //     return result.hasTargets() ? result.targets.get(0).getYaw() : 0;
    // }

    // public boolean isDetected() {
    //     return camera.getLatestResult().hasTargets();
    // }

    // public double getDistance() {
        
    //     return PhotonUtils.calculateDistanceToTargetMeters(ROBOT_HEIGHT, GOAL_HEIGHT, Math.toRadians(LL_ANGLE), Math.toRadians(getTy()));
    // }

}
