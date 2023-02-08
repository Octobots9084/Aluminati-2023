package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.arm.ArmExtension;
import frc.robot.subsystems.arm.IntakeClaws;

public class ClawsTest extends InstantCommand{
    IntakeClaws claws;
    @Override
    public void initialize() {
        this.claws = IntakeClaws.getInstance();
        claws.setClawVoltage(5);
    }
}
