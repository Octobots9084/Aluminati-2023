package frc.robot.commands.arm.basic.tolerance;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.arm.CaliGirls;
import frc.robot.util.MathUtil;

public class CaliBottomPosTolerance extends CommandBase {
    double target;

    CaliGirls caliGirls;

    public CaliBottomPosTolerance(double pos, double kf) {
        this.caliGirls = CaliGirls.getInstance();
        this.target = pos;
    }

    @Override
    public void initialize() {
        caliGirls.setBottomKf();
        caliGirls.setBottomPos(target);
    }

    @Override
    public boolean isFinished() {
        return MathUtil.isWithinTolerance(target, caliGirls.getBottomPos(), 0.05);
    }
}
