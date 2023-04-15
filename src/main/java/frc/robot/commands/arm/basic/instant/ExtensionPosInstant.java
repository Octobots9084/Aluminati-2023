package frc.robot.commands.arm.basic.instant;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.arm.ArmExtension;

public class ExtensionPosInstant extends InstantCommand {
    ArmExtension armExtension;
    double target;

    public ExtensionPosInstant(double pos) {
        this.armExtension = ArmExtension.getInstance();
        this.target = pos;
    }

    @Override
    public void initialize() {
        armExtension.setPosition(target, false);
    }
}
