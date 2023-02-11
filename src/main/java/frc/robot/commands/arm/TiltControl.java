package frc.robot.commands.arm;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.robot.ControlMap;
import frc.robot.subsystems.arm.CaliGirls;

//CURRENTLY BROKEN REWRITE BEFORE USE!
public class TiltControl extends CommandBase{
    private CaliGirls caliGirls;
    public TiltControl() {
        this.caliGirls = CaliGirls.getInstance();
        addRequirements(caliGirls);
    }
    @Override
    public void execute() {
        SmartDashboard.putNumber("arm angle", caliGirls.lastPosBottom);
        caliGirls.setBottomPos(caliGirls.lastPosBottom + 0.01 * ControlMap.XBOX.getRightY());
    }
    
}
