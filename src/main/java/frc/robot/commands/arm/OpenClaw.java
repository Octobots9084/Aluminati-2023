package frc.robot.commands.arm;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.arm.IntakeClaws;

public class OpenClaw extends InstantCommand {
    IntakeClaws claws;

    @Override
    public void initialize() {
        this.claws = IntakeClaws.getInstance();
        claws.setClawVoltage(1);
    }
}
