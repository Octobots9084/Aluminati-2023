package frc.robot.commands.arm.yeet;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.arm.basic.instant.SetArmAngle;
import frc.robot.commands.arm.basic.instant.SetArmExtension;
import frc.robot.commands.arm.basic.instant.SetWristAngle;
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
                    new WaitCommand(.55),
                    new SetWristAngle(aPosition.wrist),
                    new SetArmAngle(aPosition.armAngle, aPosition.angleHold));
    }
}
