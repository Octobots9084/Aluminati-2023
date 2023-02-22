package frc.robot.commands.autonomous.arm;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.arm.LowLevel.MoveArmExtensionToPos;
import frc.robot.commands.arm.LowLevel.MoveArmRotationToPos;
import frc.robot.commands.arm.LowLevel.MoveArmWristToPos;
import frc.robot.subsystems.arm.ArmExtension;
import frc.robot.subsystems.arm.ArmPositions;
import frc.robot.subsystems.arm.CaliGirls;

public class AutoCubeMid extends SequentialCommandGroup{
    ArmPositions aPosition;
    CaliGirls caliGirls;
    ArmExtension armExtension;

    public AutoCubeMid() {
       //Placeholder until cube positions formalized (2/21)    
    }
}