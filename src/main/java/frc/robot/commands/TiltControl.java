package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.robot.ControlMap;
import frc.robot.subsystems.arm.CaliGirls;

public class TiltControl extends CommandBase{
    private CaliGirls caliGirls;
    public TiltControl() {
        this.caliGirls = CaliGirls.getInstance();
        addRequirements(caliGirls);
    }
    @Override
    public void execute() {
        // caliGirls.setBottomPos(caliGirls.refrence + ControlMap.XBOX.getRightY() * 0.2 );
        SmartDashboard.putNumber("pivot", caliGirls.caliEncoderBottom.getPosition());
    }
    
}
