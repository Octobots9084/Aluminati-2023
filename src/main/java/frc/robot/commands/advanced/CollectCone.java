package frc.robot.commands.advanced;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.arm.intake.basic.IntakeIn;
import frc.robot.commands.arm.yeet.Arm2PosCollect;
import frc.robot.subsystems.arm.ArmPositions;
public class CollectCone extends SequentialCommandGroup{

    public CollectCone() {
        addCommands(new Arm2PosCollect(ArmPositions.CONE_INTAKE_GROUND), new IntakeIn());
    }
}
