package frc.robot.commands.arm.yeet;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.arm.basic.tolerance.CaliBottomPosTolerance;
import frc.robot.commands.arm.basic.tolerance.CaliTopPosTolerance;
import frc.robot.commands.arm.basic.tolerance.ExtensionPosTolerance;
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
                    new ExtensionPosTolerance(aPosition.extension),
                    new CaliTopPosTolerance(aPosition.wrist),
                    new CaliBottomPosTolerance(aPosition.armAngle, aPosition.angleHold));
    }
}
