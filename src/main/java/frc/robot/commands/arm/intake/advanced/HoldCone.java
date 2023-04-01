package frc.robot.commands.arm.intake.advanced;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.arm.intake.basic.IntakeIn;
import frc.robot.commands.arm.slow.MoveArmToPositionGoingDown;
import frc.robot.subsystems.arm.ArmPositions;
public class HoldCone extends SequentialCommandGroup{

    public HoldCone() {
        addCommands(new MoveArmToPositionGoingDown(ArmPositions.STOW), new IntakeIn());
    }
}
