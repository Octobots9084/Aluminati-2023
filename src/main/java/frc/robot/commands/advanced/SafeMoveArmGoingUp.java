package frc.robot.commands.advanced;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.arm.MoveArmToPos;
import frc.robot.commands.arm.MoveArmToPositionGoingDown;
import frc.robot.commands.arm.MoveArmToPositionGoingUp;
import frc.robot.commands.arm.LowLevel.MoveArmWristToPos;
import frc.robot.commands.arm.LowLevel.MoveArmExtensionToPos;
import frc.robot.subsystems.arm.ArmCommand;
import frc.robot.subsystems.arm.ArmMovement;
import frc.robot.subsystems.arm.ArmPositions;
public class SafeMoveArmGoingUp extends SequentialCommandGroup{

    public SafeMoveArmGoingUp(ArmPositions armPositions) {
        addCommands(new MoveArmWristToPos(ArmPositions.DRIVE_WITH_PIECE.wrist),new MoveArmExtensionToPos(0), new MoveArmToPositionGoingUp(armPositions));
    }
}
