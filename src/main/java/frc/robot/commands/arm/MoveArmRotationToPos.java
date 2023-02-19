package frc.robot.commands.arm;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.arm.CaliGirls;
import frc.robot.util.MathUtil;

public class MoveArmRotationToPos extends CommandBase {
    double target;

    CaliGirls caliGirls;
    double startTime = Timer.getFPGATimestamp();
    double currentTime;
    public MoveArmRotationToPos(double pos) {

        this.target = pos;
    }

    @Override
    public void initialize() {
        this.caliGirls = CaliGirls.getInstance();
        caliGirls.setBottomPos(target);
    }

    @Override
    public void execute() {
        currentTime = Timer.getFPGATimestamp();
    }

    @Override
    public boolean isFinished() {
        boolean timeout = !MathUtil.isWithinTolerance(startTime, currentTime, 3);
        return (timeout || MathUtil.isWithinTolerance(caliGirls.getBottomPos(),target,0.01));
    }
}
