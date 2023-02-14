package frc.robot.commands.arm;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.robot.ControlMap;
import frc.robot.subsystems.arm.CaliGirls;

//CURRENTLY BROKEN REWRITE BEFORE USE!
public class TiltControl extends CommandBase {
    private CaliGirls caliGirls;

    public TiltControl() {
        this.caliGirls = CaliGirls.getInstance();
        addRequirements(caliGirls);
    }

    @Override
    public void execute() {
        //TOO MUCH DELAY
        caliGirls.setBottomPos(caliGirls.lastPosBottom + 0.00025 * (-Math.pow(ControlMap.XBOX.getRightY(), 3)));
        caliGirls.setTopPos(caliGirls.lastPosTop + 0.00025 * (-Math.pow(ControlMap.XBOX.getRightX(), 3)));
    }

}
