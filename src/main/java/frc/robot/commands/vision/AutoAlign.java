package frc.robot.commands.vision;

import org.photonvision.targeting.PhotonTrackedTarget;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.swerve.SetDriveAngle;
import frc.robot.subsystems.Light;
import frc.robot.subsystems.arm.ArmPositions;
import frc.robot.subsystems.arm.CaliGirls;
import frc.robot.subsystems.swerve.DriveTrain;
import frc.robot.subsystems.vision.Vision;

public class AutoAlign extends CommandBase {
    private final DriveTrain driveTrain;
    private final Vision vision;
    private final CaliGirls caliGirls;
    private PhotonTrackedTarget cameraToTarget;
    private double ySpeed = 0;
    private Light light;

    public AutoAlign() {
        // Initialization
        this.driveTrain = DriveTrain.getInstance();
        this.vision = Vision.getInstance();
        this.caliGirls = CaliGirls.getInstance();
        this.cameraToTarget = null;
        this.light = Light.getInstance();
    }

    @Override
    public void initialize() {
        // try {
            if (vision.getTapeCamHasTarget()) {
                if (vision.getBestTarget() != null) {
                    cameraToTarget = vision.getBestTarget();
                    caliGirls.setBottomPos(ArmPositions.AUTO_ALIGN.armAngle);
                    caliGirls.setBottomKf();
                } else {
                    end(true);
                }
            }
        // } catch (Exception e) {
        //     // TO DO: handle exception
        // }
    }

    private double degreesToTarget;
    private boolean isCloseEnough; // to reliably place high cone
    private boolean isAligned;
    private double ALIGNMENT_TOLERANCE; // THIS NEEDS TO BE TUNED

    @Override
    public void execute() {
        try {
            if (vision.getTapeCamHasTarget()) {
                if (vision.getBestTarget() != null) {
                    cameraToTarget = vision.getBestTarget();
                    degreesToTarget = cameraToTarget.getYaw()-3;
                    ySpeed = degreesToTarget * 0.1;
                    SmartDashboard.putNumber("areaFilled", cameraToTarget.getArea());
                    if(Math.abs(degreesToTarget)<ALIGNMENT_TOLERANCE){
                        isAligned = true;
                    } else {
                        isAligned = false;
                    }

                    // To Do:
                    // if no target, red flash
                    // if target unaligned, red solid
                    // if aligned & far away, blue
                    // if ready, green
                    
                            
                } else {
                    ySpeed = 0;
                    end(true);
                    // driveTrain.drive(0, 0, 0, true);
                }

                
                


                //SmartDashboard.putNumber("Y_SPED", ySpeed);
                CommandScheduler.getInstance().schedule(new SetDriveAngle(180));
                driveTrain.drive(driveTrain.previousXSpeed, ySpeed, 0, true);
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
