package frc.robot.commands.arm.basic;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.arm.CaliGirls;
import frc.robot.util.MathUtil;

public class MoveArmRotationToPos extends CommandBase {
    double target;

    CaliGirls caliGirls;
    double startTime;
    double currentTime;

    private double kf;
    public MoveArmRotationToPos(double pos, double kf) {
        this.caliGirls = CaliGirls.getInstance();
        this.target = pos;
        this.kf = kf;
    }

    @Override
    public void initialize() {
        startTime = target;
        caliGirls.setBottomKf();
        caliGirls.setBottomPos(target);
    }

    @Override
    public void execute() {
        currentTime = caliGirls.getBottomPos();
    }

    @Override
    public boolean isFinished() {
        return MathUtil.isWithinTolerance(startTime, currentTime, 0.05);
    }
}
