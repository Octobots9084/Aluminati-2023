package frc.robot.commands.arm.yeet;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.arm.basic.instant.SetArmExtension;
import frc.robot.commands.arm.basic.instant.SetWristAngle;
import frc.robot.commands.arm.basic.tolerance.MoveArmRotationToPos;
import frc.robot.subsystems.arm.ArmExtension;
import frc.robot.subsystems.arm.ArmPositions;
import frc.robot.subsystems.arm.CaliGirls;

public class Arm2PosFull extends SequentialCommandGroup {
    ArmPositions aPosition;
    CaliGirls caliGirls;
    ArmExtension armExtension;

    public Arm2PosFull(ArmPositions aPosition) {
        this.aPosition = aPosition;
        this.caliGirls = CaliGirls.getInstance();
        this.armExtension = ArmExtension.getInstance();
        addCommands(
                new SetWristAngle(aPosition.wrist),
                new MoveArmRotationToPos(aPosition.armAngle, aPosition.armAngle),
                new WaitCommand(0),
                new SetArmExtension(aPosition.extension));

    }
}
