package frc.robot.commands.autonomous.arm;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.advanced.CollectCone;
import frc.robot.commands.arm.basic.MoveArmExtensionToPos;
import frc.robot.commands.arm.basic.MoveArmRotationToPos;
import frc.robot.commands.arm.basic.MoveArmWristToPos;
import frc.robot.subsystems.arm.ArmExtension;
import frc.robot.subsystems.arm.ArmPositions;
import frc.robot.subsystems.arm.CaliGirls;

public class AutoGroundIntakeCone extends SequentialCommandGroup{
    ArmPositions aPosition;
    CaliGirls caliGirls;
    ArmExtension armExtension;

    public AutoGroundIntakeCone() {
        this.aPosition = ArmPositions.STOW;
        addCommands(new CollectCone(), new MoveArmRotationToPos(aPosition.armAngle, aPosition.angleHold), new MoveArmExtensionToPos(aPosition.extension), new MoveArmWristToPos(aPosition.wrist));
        
    }
}

