package frc.robot.commands.advanced;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.arm.MoveArmToPositionGoingDown;
import frc.robot.commands.arm.LowLevel.MoveArmExtensionToPos;
import frc.robot.commands.arm.LowLevel.MoveArmWristToPos;
import frc.robot.subsystems.arm.ArmPositions;

public class SafeMoveArmGoingDown extends SequentialCommandGroup {

    public SafeMoveArmGoingDown(ArmPositions armPositions) {

        addCommands(new MoveArmWristToPos(ArmPositions.DRIVE_WITH_PIECE.wrist), new MoveArmExtensionToPos(0),
                new MoveArmToPositionGoingDown(armPositions));
    }
}
