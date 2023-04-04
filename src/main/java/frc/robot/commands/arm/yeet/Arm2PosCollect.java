package frc.robot.commands.arm.yeet;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.arm.basic.instant.SetArmAngle;
import frc.robot.commands.arm.basic.instant.SetArmExtension;
import frc.robot.commands.arm.basic.instant.SetWristAngle;
import frc.robot.subsystems.arm.ArmExtension;
import frc.robot.subsystems.arm.ArmPositions;
import frc.robot.subsystems.arm.CaliGirls;

public class Arm2PosCollect extends SequentialCommandGroup {
    ArmPositions aPosition;
    CaliGirls caliGirls;
    ArmExtension armExtension;

    public Arm2PosCollect(ArmPositions aPosition) {
        this.aPosition = aPosition;
        this.caliGirls = CaliGirls.getInstance();
        this.armExtension = ArmExtension.getInstance();
        addCommands(
                new SetArmAngle(aPosition.armAngle, aPosition.angleHold),
                new WaitCommand(0.1),
                new SetWristAngle(aPosition.wrist),
                new WaitCommand(0.5),
                new SetArmExtension(aPosition.extension));
    }
}
