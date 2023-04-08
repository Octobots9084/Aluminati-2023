package frc.robot.commands.arm.yeet;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.arm.basic.tolerance.CaliBottomPosTolerance;
import frc.robot.commands.arm.basic.tolerance.CaliTopPosTolerance;
import frc.robot.commands.arm.basic.tolerance.ExtensionPosTolerance;
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
                new CaliTopPosTolerance(aPosition.wrist),
                new ExtensionPosTolerance(aPosition.extension),
                new CaliBottomPosTolerance(ArmPositions.STOW.armAngle, ArmPositions.STOW.armAngle),
                new Arm2PosStow(aPosition));

    }
}
