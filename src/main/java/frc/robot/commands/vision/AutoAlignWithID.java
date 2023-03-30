package frc.robot.commands.vision;

import org.photonvision.targeting.PhotonTrackedTarget;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.arm.ArmPositions;
import frc.robot.subsystems.arm.CaliGirls;
import frc.robot.subsystems.swerve.DriveTrain;
import frc.robot.subsystems.vision.Vision;

public class AutoAlignWithID extends CommandBase {
    private final DriveTrain driveTrain;
    private final Vision vision;
    private final CaliGirls caliGirls;
    private PhotonTrackedTarget cameraToTarget;
    private double ySpeed = 0;

    public AutoAlignWithID() {
        // Initialization
        this.driveTrain = DriveTrain.getInstance();
        this.vision = Vision.getInstance();
        this.caliGirls = CaliGirls.getInstance();
        this.cameraToTarget = null;
    }

    @Override
    public void initialize() {
        try {
            if (vision.getTagCamHasTarget()) {
                if (vision.getBestTargetWithID() != null) {
                    cameraToTarget = vision.getBestTargetWithID();
                    caliGirls.setBottomPos(ArmPositions.AUTO_ALIGN.armAngle);
                    caliGirls.setBottomKf();
                } else {
                    end(true);
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    @Override
    public void execute() {
        try {
            if (vision.getTagCamHasTarget()) {
                if (vision.getBestTargetWithID() != null) {
                    cameraToTarget = vision.getBestTargetWithID();
                } else {
                    end(true);
                }
                ySpeed = (cameraToTarget.getYaw()+ 0.5) * 0.1;
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
