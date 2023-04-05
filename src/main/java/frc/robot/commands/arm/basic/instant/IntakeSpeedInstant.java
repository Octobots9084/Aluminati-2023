package frc.robot.commands.arm.basic.instant;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.arm.Roller;

public class IntakeSpeedInstant extends InstantCommand {

    private double voltage;

    public IntakeSpeedInstant(double voltage) {
        this.voltage = voltage;
    }

    @Override
    public void initialize() {
        Roller.getInstance().setRollerVoltage(voltage);
    }
}
