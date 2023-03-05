package frc.robot.commands.arm.LowLevel;

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
        startTime = Timer.getFPGATimestamp();
        caliGirls.setBottomKf(kf);
        caliGirls.setBottomPos(target);
    }

    @Override
    public void execute() {
        currentTime = Timer.getFPGATimestamp();
    }

    @Override
    public boolean isFinished() {
        boolean timeout = !MathUtil.isWithinTolerance(startTime, currentTime, 0.5);
        return (timeout);
    }
}
