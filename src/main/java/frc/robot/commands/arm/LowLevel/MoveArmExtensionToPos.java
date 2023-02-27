package frc.robot.commands.arm.LowLevel;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.arm.ArmExtension;
import frc.robot.util.MathUtil;

public class MoveArmExtensionToPos extends CommandBase {
    double target;

    ArmExtension armExtension;
    double startTime = Timer.getFPGATimestamp();
    double currentTime;

    public MoveArmExtensionToPos(double pos) {

        this.target = pos;
        this.armExtension = ArmExtension.getInstance();
    }

    @Override
    public void initialize() {

        armExtension.setPosition(target, false);
    }

    @Override
    public void execute() {
        currentTime = Timer.getFPGATimestamp();
    }

    @Override
    public boolean isFinished() {
        boolean timeout = !MathUtil.isWithinTolerance(startTime, currentTime, 3);
        return (timeout || MathUtil.isWithinTolerance(armExtension.getPosition(), target, 5));
    }
}
