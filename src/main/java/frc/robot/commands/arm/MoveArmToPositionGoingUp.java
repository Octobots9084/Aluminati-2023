package frc.robot.commands.arm;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.arm.LowLevel.MoveArmExtensionToPos;
import frc.robot.commands.arm.LowLevel.MoveArmRotationToPos;
import frc.robot.commands.arm.LowLevel.MoveArmWristToPos;
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
                new WaitCommand(0.5),
                new MoveArmExtensionToPos(aPosition.extension), 
                new MoveArmWristToPos(aPosition.wrist));
    }
}
