package frc.robot.commands.arm;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.arm.ArmExtension;
import frc.robot.subsystems.arm.CaliGirls;
import frc.robot.util.MathUtil;
import frc.robot.util.armPosition;

public class MoveToPosition extends SequentialCommandGroup{
    armPosition aPosition;
    CaliGirls caliGirls;
    ArmExtension armExtension;

    public MoveToPosition(armPosition aPosition) {
        this.aPosition = aPosition;
        this.caliGirls = CaliGirls.getInstance();
        this.armExtension = ArmExtension.getInstance();
        addCommands(new MoveExtensionToPos(0),new MoveArmRotationToPos(aPosition.rotation), new MoveExtensionToPos(aPosition.extension), new MoveArmWristToPos(aPosition.wrist));
    }
}
