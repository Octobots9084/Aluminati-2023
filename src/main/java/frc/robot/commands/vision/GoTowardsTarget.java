package frc.robot.commands.vision;

import org.photonvision.targeting.PhotonTrackedTarget;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.swerve.DriveTrain;
import frc.robot.subsystems.vision.Vision;

public class GoTowardsTarget extends CommandBase {
    private final DriveTrain driveTrain;
    private final Vision vision;
    private PhotonTrackedTarget cameraToTarget;
    private double ySpeed = 0;

    public GoTowardsTarget() {
        // Initialization
        this.driveTrain = DriveTrain.getInstance();
        this.vision = Vision.getInstance();
        this.cameraToTarget = null;
    }

    @Override
    public void initialize() {
        try {
            if (vision.getTagCamHasTarget() || vision.getTapeCamHasTarget()) {
                cameraToTarget = vision.getClosestTarget();
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    @Override
    public void execute() {
        try {
            if (vision.getTagCamHasTarget() || vision.getTapeCamHasTarget()) {
                cameraToTarget = vision.getClosestTarget();

                ySpeed = cameraToTarget.getYaw() * 0.05;
                SmartDashboard.putNumber("Y_SPED", ySpeed);

                driveTrain.drive(0, ySpeed, 0, true);
            }
        } catch (Exception e) {
            //
        }
    }

    @Override
    public void end(boolean interrupted) {
        driveTrain.drive(0, 0, 0, true);
    }

}
