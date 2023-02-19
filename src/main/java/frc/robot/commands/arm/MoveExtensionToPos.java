package frc.robot.commands.arm;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.arm.ArmExtension;
import frc.robot.util.MathUtil;

public class MoveExtensionToPos extends CommandBase {
    double target;

    ArmExtension armExtension;
    double startTime = Timer.getFPGATimestamp();
    double currentTime;
    public MoveExtensionToPos(double pos) {

        this.target = pos;
    }

    @Override
    public void initialize() {
        this.armExtension = ArmExtension.getInstance();
        armExtension.SetPosition(target, false);
    }

    @Override
    public void execute() {
        currentTime = Timer.getFPGATimestamp();
    }

    @Override
    public boolean isFinished() {
        boolean timeout = !MathUtil.isWithinTolerance(startTime, currentTime, 180);
        return (timeout || MathUtil.isWithinTolerance(armExtension.GetPosition(),target,5));
    }
}
