package frc.robot.commands.arm;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.commands.arm.lowLevel.MoveArmExtensionToPos;
import frc.robot.commands.arm.lowLevel.MoveArmRotationToPos;
import frc.robot.commands.arm.lowLevel.MoveArmWristToPos;
import frc.robot.subsystems.arm.ArmPositions;

public class ParallalMoveArm extends ParallelCommandGroup{
    public ParallalMoveArm(ArmPositions armPositions) {
        addCommands(
            new MoveArmExtensionToPos(armPositions.extension), 
            new MoveArmRotationToPos(armPositions.armAngle, armPositions.angleHold),
            new MoveArmWristToPos(armPositions.wrist)
        );
    }
    
}
