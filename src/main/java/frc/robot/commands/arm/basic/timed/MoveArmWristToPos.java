package frc.robot.commands.arm.basic.timed;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.arm.CaliGirls;
import frc.robot.util.MathUtil;

@Deprecated
public class MoveArmWristToPos extends CommandBase{
    double target;

    CaliGirls caliGirls;
    double startTime;
    double currentTime;
    public MoveArmWristToPos(double pos) {
        this.caliGirls = CaliGirls.getInstance();
        this.target = pos;
    }

    @Override
    public void initialize() {
        startTime = Timer.getFPGATimestamp();
        caliGirls.setTopPos(target);
    }

    @Override
    public void execute() {
        currentTime = Timer.getFPGATimestamp();
    }

    @Override
    public boolean isFinished() {
        boolean timeout = !MathUtil.isWithinTolerance(startTime, currentTime, 0.2);
        return (timeout);
    }
}
