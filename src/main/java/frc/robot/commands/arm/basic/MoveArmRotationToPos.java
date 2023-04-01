package frc.robot.commands.arm.basic;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.arm.CaliGirls;
import frc.robot.util.MathUtil;

public class MoveArmRotationToPos extends CommandBase {
    double target;

    CaliGirls caliGirls;

    public MoveArmRotationToPos(double pos, double kf) {
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
