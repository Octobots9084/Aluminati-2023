package frc.robot.commands.arm;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.subsystems.arm.ArmPositions;

public class GoToFull extends ParallelCommandGroup{
    public GoToFull(ArmPositions armPositions) {
        addCommands(
            new MoveExtensionToPos(armPositions.extension), 
            new MoveArmRotationToPos(armPositions.angle),
            new MoveArmWristToPos(armPositions.wrist)
        );
    }
    
}
