package frc.robot.commands.arm.basic.instant;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.commands.arm.basic.timed.MoveArmExtensionToPos;
import frc.robot.commands.arm.basic.timed.MoveArmWristToPos;
import frc.robot.commands.arm.basic.tolerance.MoveArmRotationToPos;
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
