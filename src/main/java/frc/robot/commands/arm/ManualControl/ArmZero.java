package frc.robot.commands.arm.ManualControl;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.arm.ArmExtension;

public class ArmZero extends CommandBase {

    ArmExtension armExtension;

    public ArmZero() {
        this.armExtension = ArmExtension.getInstance();
    }

    @Override
    public void execute() {
        armExtension.zeroArm();
    }

    @Override
    public  boolean isFinished() {
        return  armExtension.zeroDone();
    }
    @Override
    public void end(boolean interupted) {
        armExtension.resetCurrent();
        armExtension.setOffset();
    }
}
