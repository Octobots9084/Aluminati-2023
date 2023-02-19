package frc.robot.commands.advanced;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.arm.IntakeIn;
import frc.robot.commands.arm.MoveArmToPositionGoingDown;
import frc.robot.subsystems.arm.ArmExtension;
import frc.robot.subsystems.arm.ArmPositions;
import frc.robot.subsystems.arm.CaliGirls;
public class CollectCone extends SequentialCommandGroup{

    public CollectCone() {
        addCommands(new MoveArmToPositionGoingDown(ArmPositions.FLOOR_INTAKE_CONE), new IntakeIn());
    }
}
