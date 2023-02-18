package frc.robot.commands.arm;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.arm.ArmExtension;
import frc.robot.subsystems.arm.ArmPositions;
import frc.robot.subsystems.arm.CaliGirls;

public class MoveArmToPositionGoingUp extends SequentialCommandGroup{
    ArmPositions aPosition;
    CaliGirls caliGirls;
    ArmExtension armExtension;

    public MoveArmToPositionGoingUp(ArmPositions aPosition) {
        this.aPosition = aPosition;
        this.caliGirls = CaliGirls.getInstance();
        this.armExtension = ArmExtension.getInstance();
        addCommands(new MoveExtensionToPos(0),new MoveArmRotationToPos(aPosition.angle), new MoveExtensionToPos(aPosition.extension), new MoveArmWristToPos(aPosition.wrist));
    }
}
