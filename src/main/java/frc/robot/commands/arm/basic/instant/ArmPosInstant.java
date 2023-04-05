package frc.robot.commands.arm.basic.instant;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.subsystems.arm.ArmPositions;

public class ArmPosInstant extends ParallelCommandGroup{
    public ArmPosInstant(ArmPositions armPositions) {
        addCommands(
            new ExtensionPosInstant(armPositions.extension), 
            new CaliBottomPosInstant(armPositions.armAngle, armPositions.angleHold),
            new CaliTopPosInstant(armPositions.wrist)
        );
    }
    
}
