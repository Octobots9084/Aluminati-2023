package frc.robot.commands.arm;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.arm.LowLevel.MoveArmExtensionToPos;
import frc.robot.commands.arm.LowLevel.MoveArmRotationToPos;
import frc.robot.commands.arm.LowLevel.MoveArmWristToPos;
import frc.robot.subsystems.arm.ArmExtension;
import frc.robot.subsystems.arm.ArmPositions;
import frc.robot.subsystems.arm.CaliGirls;

public class MoveArmToPositionGoingDown extends SequentialCommandGroup{
    ArmPositions aPosition;
    CaliGirls caliGirls;
    ArmExtension armExtension;

    public MoveArmToPositionGoingDown(ArmPositions aPosition) {
        this.aPosition = aPosition;
        this.caliGirls = CaliGirls.getInstance();
        this.armExtension = ArmExtension.getInstance();
        addCommands(new MoveArmExtensionToPos(0),new MoveArmWristToPos(aPosition.wrist),new MoveArmRotationToPos(aPosition.armAngle, aPosition.angleHold), new MoveArmExtensionToPos(aPosition.extension));
    }
}
