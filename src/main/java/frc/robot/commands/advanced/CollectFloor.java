package frc.robot.commands.advanced;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.arm.basic.instant.IntakeSpeedInstant;
import frc.robot.commands.arm.yeet.Arm2PosCollect;
import frc.robot.subsystems.arm.ArmPositions;
public class CollectFloor extends SequentialCommandGroup{

    public CollectFloor() {
        addCommands(new Arm2PosCollect(ArmPositions.INTAKE_GROUND), new IntakeSpeedInstant(-10));
    }
}
