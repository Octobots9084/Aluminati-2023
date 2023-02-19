package frc.robot.commands.arm;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.arm.CaliGirls;
import frc.robot.util.MathUtil;

public class MoveArmWristToPos extends CommandBase{
    double target;

    CaliGirls caliGirls;
    double startTime = Timer.getFPGATimestamp();
    double currentTime;
    public MoveArmWristToPos(double pos) {
        
        this.target = pos;
    }

    @Override
    public void initialize() {
        this.caliGirls = CaliGirls.getInstance();
        caliGirls.setTopPos(target);
    }

    @Override
    public void execute() {
        currentTime = Timer.getFPGATimestamp();
    }

    @Override
    public boolean isFinished() {
        boolean timeout = !MathUtil.isWithinTolerance(startTime, currentTime, 3);
        return (timeout || MathUtil.isWithinTolerance(caliGirls.getTopPos(),target,0.01));
    }
}
