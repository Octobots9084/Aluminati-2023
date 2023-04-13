package frc.robot.commands.arm.basic.tolerance;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.arm.CaliGirls;
import frc.robot.util.MathUtil;

public class CaliTopPosTolerance extends CommandBase {
    double target;

    CaliGirls caliGirls;

    public CaliTopPosTolerance(double pos) {
        this.caliGirls = CaliGirls.getInstance();
        this.target = pos;
    }

    @Override
    public void initialize() {
        caliGirls.setTopPos(target);
    }

    @Override
    public boolean isFinished() {
        return MathUtil.isWithinTolerance(target, caliGirls.getTopPos(), 0.1);
    }
}
