package frc.robot.commands.advanced;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.arm.basic.instant.ArmPosInstant;
import frc.robot.commands.arm.basic.instant.IntakeSpeedInstant;
import frc.robot.subsystems.arm.ArmPositions;
public class HoldCone extends SequentialCommandGroup{

    public HoldCone() {
        addCommands(new ArmPosInstant(ArmPositions.STOW), new IntakeSpeedInstant(-10));
    }
}
