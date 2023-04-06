package frc.robot.commands.advanced;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.arm.basic.instant.IntakeSpeedInstant;
import frc.robot.commands.arm.basic.instant.ArmPosInstant;
import frc.robot.subsystems.arm.ArmPositions;
public class CollectSubstation extends SequentialCommandGroup{

    public CollectSubstation() {
        addCommands(new ArmPosInstant(ArmPositions.INTAKE_SUBSTATION), new IntakeSpeedInstant(-10));
    }
}
