package frc.robot.commands.arm.basic.instant;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.arm.Roller;

public class SetIntakeSpeed extends InstantCommand {

    private double voltage;

    public SetIntakeSpeed(double voltage) {
        this.voltage = voltage;
    }

    @Override
    public void initialize() {
        Roller.getInstance().setRollerVoltage(voltage);
    }
}
