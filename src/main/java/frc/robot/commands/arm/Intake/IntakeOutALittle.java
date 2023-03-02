package frc.robot.commands.arm.Intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.arm.Roller;

public class IntakeOutALittle extends InstantCommand {


    @Override
    public void initialize() {
        Roller.getInstance().setRollerVoltage(1);
    }
    
}
