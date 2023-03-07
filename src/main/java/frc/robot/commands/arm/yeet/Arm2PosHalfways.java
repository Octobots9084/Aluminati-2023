package frc.robot.commands.arm.yeet;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.arm.basic.SetArmAngle;
import frc.robot.commands.arm.basic.SetArmExtension;
import frc.robot.commands.arm.basic.SetWristAngle;
import frc.robot.subsystems.arm.ArmExtension;
import frc.robot.subsystems.arm.ArmPositions;
import frc.robot.subsystems.arm.CaliGirls;

public class Arm2PosHalfways extends SequentialCommandGroup {
    ArmPositions aPosition;
    CaliGirls caliGirls;
    ArmExtension armExtension;

    public Arm2PosHalfways(ArmPositions aPosition) {
        this.aPosition = aPosition;
        this.caliGirls = CaliGirls.getInstance();
        this.armExtension = ArmExtension.getInstance();
        addCommands(
                new SetArmAngle(aPosition.armAngle, aPosition.armAngle),
                new WaitCommand(0),
                new SetWristAngle(aPosition.wrist),
                new WaitCommand(1.2),
                new SetArmExtension(aPosition.extension));

    }
}
