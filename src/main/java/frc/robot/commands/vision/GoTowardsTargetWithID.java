package frc.robot.commands.vision;

import org.photonvision.targeting.PhotonTrackedTarget;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.swerve.DriveTrain;
import frc.robot.subsystems.vision.Vision;

public class GoTowardsTargetWithID extends CommandBase {
    private final DriveTrain driveTrain;
    private final Vision vision;
    private PhotonTrackedTarget cameraToTarget;
    private double ySpeed = 0;

    public GoTowardsTargetWithID() {
        // Initialization
        this.driveTrain = DriveTrain.getInstance();
        this.vision = Vision.getInstance();
        this.cameraToTarget = null;
    }

    @Override
    public void initialize() {
        try {
            if (vision.getTagCamHasTarget()) {
                cameraToTarget = vision.getBestTargetWithID();
            }
        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    @Override
    public void execute() {
        try {
            if (vision.getTagCamHasTarget()) {
                cameraToTarget = vision.getBestTargetWithID();

                ySpeed = cameraToTarget.getYaw() * 0.1;
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
