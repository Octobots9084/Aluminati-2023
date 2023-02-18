package frc.robot.commands.arm;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.arm.Roller;

public class Grab extends InstantCommand {
    double voltage;

    public Grab(double voltage) {
        this.voltage = voltage;
    }

    @Override
    public void initialize() {
        Roller.getInstance().setRollerVoltage(voltage);
    }
}
