package frc.robot.commands.arm;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.arm.ArmExtension;

public class ArmZero extends CommandBase {

    ArmExtension armExtension;

    public ArmZero() {
        this.armExtension = ArmExtension.getInstance();
    }

    @Override
    public void initialize() {
        armExtension.getInstance().zeroArm();
    }

    public boolean isFinished() {
        return armExtension.getInstance().zeroDone();
    }
}
