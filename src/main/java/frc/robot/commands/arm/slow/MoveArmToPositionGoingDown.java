package frc.robot.commands.arm.slow;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.arm.basic.timed.ExtensionPosTimed;
import frc.robot.commands.arm.basic.instant.ExtensionPosInstant;
import frc.robot.commands.arm.basic.timed.CaliTopPosTimed;
import frc.robot.commands.arm.basic.tolerance.CaliBottomPosTolerance;
import frc.robot.commands.arm.basic.tolerance.ExtensionPosTolerance;
import frc.robot.subsystems.arm.ArmExtension;
import frc.robot.subsystems.arm.ArmPositions;
import frc.robot.subsystems.arm.CaliGirls;

public class MoveArmToPositionGoingDown extends SequentialCommandGroup {
    ArmPositions aPosition;
    CaliGirls caliGirls;
    ArmExtension armExtension;
    @Deprecated
    public MoveArmToPositionGoingDown(ArmPositions aPosition) {
        this.aPosition = aPosition;
        this.caliGirls = CaliGirls.getInstance();
        this.armExtension = ArmExtension.getInstance();
        addCommands(
                new ExtensionPosInstant(0),
                new CaliTopPosTimed(aPosition.wrist),
                new CaliBottomPosTolerance(aPosition.armAngle, aPosition.angleHold),
                new ExtensionPosTolerance(aPosition.extension));
    }
}
