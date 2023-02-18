package frc.robot.commands.arm;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.arm.ArmExtension;

public class ArmExtensionPos extends InstantCommand {
    ArmExtension armExtension;
    double target;

    public ArmExtensionPos(double pos) {
        this.target = pos;
    }

    @Override
    public void initialize() {
        this.armExtension = ArmExtension.getInstance();
        //Try True
        armExtension.SetPosition(target, false);
    }
}
