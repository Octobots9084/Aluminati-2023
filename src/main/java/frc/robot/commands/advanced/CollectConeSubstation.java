package frc.robot.commands.advanced;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.arm.intake.basic.IntakeIn;
import frc.robot.commands.arm.slow.MoveArmToPositionGoingUp;
import frc.robot.subsystems.arm.ArmPositions;
public class CollectConeSubstation extends SequentialCommandGroup{

    public CollectConeSubstation() {
        addCommands(new MoveArmToPositionGoingUp(ArmPositions.INTAKE_SUBSTATION), new IntakeIn());
    }
}
