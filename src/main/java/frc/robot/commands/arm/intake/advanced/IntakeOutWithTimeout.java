package frc.robot.commands.arm.intake.advanced;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.arm.intake.basic.IntakeNone;
import frc.robot.commands.arm.intake.basic.IntakeOut;

public class IntakeOutWithTimeout extends SequentialCommandGroup{

    public IntakeOutWithTimeout() {
        addCommands(new IntakeOut(), new WaitCommand(2), new IntakeNone());
    }
}