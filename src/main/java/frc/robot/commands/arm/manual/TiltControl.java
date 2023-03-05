package frc.robot.commands.arm.manual;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.robot.ControlMap;
import frc.robot.robot.Logging;
import frc.robot.subsystems.arm.CaliGirls;
import frc.robot.util.MathUtil;

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
        var topPos = 0.025 * Math.signum(ControlMap.CO_DRIVER_LEFT.getY())
                * MathUtil.fitDeadband((-Math.pow(ControlMap.CO_DRIVER_LEFT.getY(), 2)), 0.01);
        var bottomPos = -0.025 * .75 * Math.signum(ControlMap.CO_DRIVER_RIGHT.getY())
                * MathUtil.fitDeadband((Math.pow(ControlMap.CO_DRIVER_RIGHT.getY(), 2)), 0.01);

        caliGirls.setTopPos(caliGirls.lastPosTop - topPos);
        caliGirls.setBottomPos(caliGirls.lastPosBottom + bottomPos);

        
    }

}
