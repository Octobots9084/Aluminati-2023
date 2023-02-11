package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.arm.ArmExtension;

public class ArmZero extends InstantCommand{
    
    public ArmExtension armExtension;
    public ArmZero() {
        armExtension = ArmExtension.getInstance();
    }

    @Override
    public void initialize() {
        armExtension.setOffset();
    }
}
