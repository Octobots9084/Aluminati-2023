package frc.robot.commands.arm.slow;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.arm.basic.timed.MoveArmExtensionToPos;
import frc.robot.commands.arm.basic.timed.MoveArmWristToPos;
import frc.robot.commands.arm.basic.tolerance.MoveArmRotationToPos;
import frc.robot.subsystems.arm.ArmExtension;
import frc.robot.subsystems.arm.ArmPositions;
import frc.robot.subsystems.arm.CaliGirls;

public class MoveArmToPositionGoingUp extends SequentialCommandGroup {
    ArmPositions aPosition;
    CaliGirls caliGirls;
    ArmExtension armExtension;

    public MoveArmToPositionGoingUp(ArmPositions aPosition) {
        this.aPosition = aPosition;
        this.caliGirls = CaliGirls.getInstance();
        this.armExtension = ArmExtension.getInstance();
        addCommands(
                new MoveArmExtensionToPos(0),
                new MoveArmRotationToPos(aPosition.armAngle, aPosition.angleHold),
                new MoveArmExtensionToPos(aPosition.extension), 
                new MoveArmWristToPos(aPosition.wrist));
    }
}
