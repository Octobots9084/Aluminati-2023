package frc.robot.commands.vision;

import org.photonvision.targeting.PhotonTrackedTarget;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Light;
import frc.robot.subsystems.arm.ArmPositions;
import frc.robot.subsystems.arm.CaliGirls;
import frc.robot.subsystems.swerve.DriveTrain;
import frc.robot.subsystems.vision.Vision;
import frc.robot.util.MathUtil;

public class AutoAutoAlign extends CommandBase {
    private final DriveTrain driveTrain;
    private final Vision vision;
    private final CaliGirls caliGirls;
    private PhotonTrackedTarget cameraToTarget;
    private double ySpeed = 0;
    private Light light;

    public AutoAutoAlign() {
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
                    //caliGirls.setBottomKf();
                } else {
                    end(true);
                }
            }
        // } catch (Exception e) {
        //     // TO DO: handle exception
        // }
    }

    public int detectioncycles = 0;

    @Override
    public void execute() {
        try {
            if (vision.getTapeCamHasTarget()) {
                if (vision.getBestTarget() != null) {
                    cameraToTarget = vision.getBestTarget();
                            // SmartDashboard.putNumber("Degrees", cameraToTarget.getYaw()-3);
                            //light.setDegrees(cameraToTarget.getYaw()-3);
                            //light.setHasTarget(true);
                            //light.lightUpdateControl(-1);
                            ySpeed = (cameraToTarget.getYaw()-3) * 0.1;
                            // SmartDashboard.putNumber("Degrees to target", cameraToTarget.getYaw()-3);
                            
                            if(MathUtil.isWithinTolerance(cameraToTarget.getYaw()-3,0,1)){
                                light.AdrUpdateStrobe(0, 255, 0, 1);
                            } else {
                                light.AdrUpdateStrobe(0, 0, 255, 1);
                            }
                            
                } else {
                            ySpeed = 0;
                            //light.setHasTarget(false);
                            //light.lightUpdateControl(-1);
                            light.AdrUpdateStrobe(255, 0, 0, 1);
                    end(true);
                    // driveTrain.drive(0, 0, 0, true);
                }

                
                


                // SmartDashboard.putNumber("Y_SPED", ySpeed);
                driveTrain.drive(0, -ySpeed, 0, true);
            }
        } catch (Exception e) {
            //
        }
    }

    @Override
    public void end(boolean interrupted) {
        driveTrain.drive(0, 0, 0, true);
    }

    @Override
    public boolean isFinished() {
        if (vision.getTapeCamHasTarget()) {
            if (vision.getBestTarget() != null) {
                return MathUtil.isWithinTolerance(cameraToTarget.getYaw()-3,0,0.7);
            }
        } 
        return false;
    }

}
