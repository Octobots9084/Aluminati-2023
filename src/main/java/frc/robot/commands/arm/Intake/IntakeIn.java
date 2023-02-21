package frc.robot.commands.arm.Intake;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.arm.Roller;

public class IntakeIn extends InstantCommand {

    @Override
    public void initialize() {
        Roller.getInstance().setRollerVoltage(-8);
    }
}
