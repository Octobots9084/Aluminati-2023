package frc.robot.commands.arm.LowLevel;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.arm.ArmExtension;

public class SetArmExtension extends InstantCommand {
    ArmExtension armExtension;
    double target;

    public SetArmExtension(double pos) {
        this.armExtension = ArmExtension.getInstance();
        this.target = pos;
        this.addRequirements(armExtension);
    }

    @Override
    public void initialize() {
        armExtension.SetPosition(target, false);
    }
}
