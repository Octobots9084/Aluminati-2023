package frc.robot.commands.arm.intake.advanced;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.arm.basic.instant.SetWristAngle;
import frc.robot.commands.arm.basic.tolerance.ArmExtension2PosTolerance;
import frc.robot.commands.arm.basic.tolerance.MoveArmRotationToPos;
import frc.robot.commands.arm.intake.basic.IntakeNone;
import frc.robot.commands.arm.intake.basic.IntakeOut;
import frc.robot.commands.arm.yeet.Arm2PosCooldown;
import frc.robot.subsystems.arm.ArmPositions;

public class CubeInjectMid extends SequentialCommandGroup {
    public CubeInjectMid() {
        addCommands(
                // new SetArmAngle(pos - 0.1, caliGirls.getBottomKf()),
                // new MoveArmRotationToPos(pos, caliGirls.getBottomKf()),
                new ArmExtension2PosTolerance(0).withTimeout(1),
                new MoveArmRotationToPos(ArmPositions.CUBE_PLACE_MID.armAngle, 0),
                new WaitCommand(0.2),
                new SetWristAngle(ArmPositions.CUBE_PLACE_MID.wrist),
                new IntakeOut(),
                new WaitCommand(0.5),
                new Arm2PosCooldown(ArmPositions.STOW),
                new IntakeNone());
    }

}
