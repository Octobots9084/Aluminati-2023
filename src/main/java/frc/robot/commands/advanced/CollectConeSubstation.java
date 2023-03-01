package frc.robot.commands.advanced;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.arm.MoveArmToPositionGoingDown;
import frc.robot.commands.arm.Intake.IntakeIn;
import frc.robot.subsystems.arm.ArmExtension;
import frc.robot.subsystems.arm.ArmPositions;
import frc.robot.subsystems.arm.CaliGirls;
public class CollectConeSubstation extends SequentialCommandGroup{

    public CollectConeSubstation() {
        addCommands(new MoveArmToPositionGoingDown(ArmPositions.INTAKE_SUBSTATION), new IntakeIn());
    }
}
