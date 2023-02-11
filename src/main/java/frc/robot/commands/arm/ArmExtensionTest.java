package frc.robot.commands.arm;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.arm.ArmExtension;

public class ArmExtensionTest extends InstantCommand{
    ArmExtension armExtension;
    @Override
    public void initialize() {
        this.armExtension = ArmExtension.getInstance();
        armExtension.SetPosition(3500);
    }
}
