package frc.robot.commands.arm;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.subsystems.arm.ArmPositions;

public class ParallalMoveArm extends ParallelCommandGroup{
    public ParallalMoveArm(ArmPositions armPositions) {
        addCommands(
            new MoveExtensionToPos(armPositions.extension), 
            new MoveArmRotationToPos(armPositions.angle),
            new MoveArmWristToPos(armPositions.wrist)
        );
    }
    
}
