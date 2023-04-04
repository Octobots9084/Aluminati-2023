package frc.robot.commands.arm.slow;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.arm.basic.timed.MoveArmExtensionToPos;
import frc.robot.commands.arm.basic.timed.MoveArmWristToPos;
import frc.robot.subsystems.arm.ArmPositions;

public class SafeMoveArmGoingUp extends SequentialCommandGroup {

    public SafeMoveArmGoingUp(ArmPositions armPositions) {
        addCommands(new MoveArmWristToPos(ArmPositions.STOW.wrist), new MoveArmExtensionToPos(0),
                new MoveArmToPositionGoingUp(armPositions));
    }
}
