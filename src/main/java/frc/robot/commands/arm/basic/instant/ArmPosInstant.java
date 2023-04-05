package frc.robot.commands.arm.basic.instant;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.commands.arm.basic.timed.ExtensionPosTimed;
import frc.robot.commands.arm.basic.timed.CaliTopPosTimed;
import frc.robot.commands.arm.basic.tolerance.CaliBottomPosTolerance;
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
