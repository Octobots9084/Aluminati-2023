package frc.robot.commands.advanced;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.arm.MoveArmToPos;
import frc.robot.commands.arm.MoveArmToPositionGoingDown;
import frc.robot.subsystems.arm.ArmCommand;
import frc.robot.subsystems.arm.ArmMovement;
import frc.robot.subsystems.arm.ArmPositions;
public class SafeMoveArm extends SequentialCommandGroup{

    public SafeMoveArm(ArmPositions armPositions) {
        ArmPositions drivePosition = ArmPositions.DRIVE_WITH_PIECE;
        ArmMovement[] moveToDrivePosMovements = {ArmMovement.wrist, ArmMovement.extension, ArmMovement.angle};
        ArmCommand moveToDrivePosCommand = new ArmCommand(moveToDrivePosMovements, drivePosition);
        addCommands(new MoveArmToPos(moveToDrivePosCommand.commands), new MoveArmToPositionGoingDown(armPositions));
    }
}
