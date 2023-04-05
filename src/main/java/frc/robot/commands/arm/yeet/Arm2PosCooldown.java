package frc.robot.commands.arm.yeet;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.arm.basic.instant.CaliBottomPosInstant;
import frc.robot.commands.arm.basic.instant.ExtensionPosInstant;
import frc.robot.commands.arm.basic.instant.CaliTopPosInstant;
import frc.robot.subsystems.arm.ArmExtension;
import frc.robot.subsystems.arm.ArmPositions;
import frc.robot.subsystems.arm.CaliGirls;

public class Arm2PosCooldown extends SequentialCommandGroup {
    ArmPositions aPosition;
    CaliGirls caliGirls;
    ArmExtension armExtension;

    public Arm2PosCooldown(ArmPositions aPosition) {
        this.aPosition = aPosition;
        this.caliGirls = CaliGirls.getInstance();
        this.armExtension = ArmExtension.getInstance();
        addCommands(
                new CaliTopPosInstant(aPosition.wrist),
                new ExtensionPosInstant(aPosition.extension),
                new WaitCommand(0.4),
                new CaliBottomPosInstant(ArmPositions.STOW.armAngle, ArmPositions.STOW.armAngle),
                new WaitCommand(0.1),
                new Arm2PosStow(aPosition));

    }
}
