package frc.robot.commands.arm;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.arm.LowLevel.MoveArmExtensionToPos;
import frc.robot.commands.arm.LowLevel.MoveArmRotationToPos;
import frc.robot.commands.arm.LowLevel.MoveArmWristToPos;
import frc.robot.commands.arm.LowLevel.SetArmAngle;
import frc.robot.commands.arm.LowLevel.SetArmExtension;
import frc.robot.commands.arm.LowLevel.SetWristAngle;
import frc.robot.subsystems.arm.ArmExtension;
import frc.robot.subsystems.arm.ArmPositions;
import frc.robot.subsystems.arm.CaliGirls;

public class Arm2PosStow extends SequentialCommandGroup {
    ArmPositions aPosition;
    CaliGirls caliGirls;
    ArmExtension armExtension;

    public Arm2PosStow(ArmPositions aPosition) {
        this.aPosition = aPosition;
        this.caliGirls = CaliGirls.getInstance();
        this.armExtension = ArmExtension.getInstance();
        addCommands(
                    new SetArmExtension(aPosition.extension),
                    new SetWristAngle(aPosition.wrist),
                    new WaitCommand(.3),
                    new SetArmAngle(aPosition.armAngle, aPosition.angleHold));
    }
}
