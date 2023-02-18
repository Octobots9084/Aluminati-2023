package frc.robot.commands.arm;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.arm.CaliGirls;
import frc.robot.util.MathUtil;

public class MoveArmWristToPos extends CommandBase{
    double target;

    CaliGirls caliGirls;
    public MoveArmWristToPos(double pos) {
        
        this.target = pos;
    }

    @Override
    public void initialize() {
        this.caliGirls = CaliGirls.getInstance();
        caliGirls.setTopPos(target);
    }

    @Override
    public boolean isFinished() {
        return (MathUtil.isWithinTolerance(caliGirls.getTopPos(),target,0.01));
    }
}
