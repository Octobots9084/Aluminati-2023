package frc.robot.commands.arm.basic.tolerance;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.arm.CaliGirls;
import frc.robot.util.MathUtil;

public class CaliBottomPosTolerance extends CommandBase {
    double target;

    CaliGirls caliGirls;

    double tolerance = 0.05;
    public CaliBottomPosTolerance(double pos, double kf) {
        this.caliGirls = CaliGirls.getInstance();
        this.target = pos;
    }

    public CaliBottomPosTolerance(double pos, double kf, double tolerance) {
        this.caliGirls = CaliGirls.getInstance();
        this.target = pos;
        this.tolerance = tolerance;
    }

    @Override
    public void initialize() {
        caliGirls.setBottomPos(target);
    }

    @Override
    public boolean isFinished() {
        return MathUtil.isWithinTolerance(target, caliGirls.getBottomPos(), tolerance);
    }
}
