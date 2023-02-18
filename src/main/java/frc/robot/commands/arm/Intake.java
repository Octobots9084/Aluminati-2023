package frc.robot.commands.arm;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.arm.IntakeClaws;

public class Intake extends InstantCommand {
    private IntakeClaws intake;
    private double voltage;

    public Intake(double voltage)
    {
        this.intake = IntakeClaws.getInstance();
        this.voltage = voltage;
    }

    @Override
    public void initialize() {
        intake.setClawVoltage(voltage);
    }
}

