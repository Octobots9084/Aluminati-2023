package frc.robot.commands.arm.intake.advanced;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.arm.basic.ArmExtension2PosTolerance;
import frc.robot.commands.arm.basic.MoveArmRotationToPos;
import frc.robot.commands.arm.basic.SetWristAngle;
import frc.robot.commands.arm.intake.basic.IntakeNone;
import frc.robot.commands.arm.intake.basic.IntakeOut;
import frc.robot.commands.arm.slow.MoveArmToPositionGoingUp;
import frc.robot.commands.arm.yeet.Arm2PosCooldown;
import frc.robot.subsystems.arm.ArmPositions;

public class CubeInjectHigh extends SequentialCommandGroup {
    public CubeInjectHigh() {
        addCommands(
                // new SetArmAngle(pos - 0.1, caliGirls.getBottomKf()),
                // new MoveArmRotationToPos(pos, caliGirls.getBottomKf()),
                new MoveArmToPositionGoingUp(ArmPositions.CUBE_PLACE_HIGH),
                new ArmExtension2PosTolerance(0),
                new MoveArmRotationToPos(ArmPositions.CUBE_PLACE_HIGH.armAngle, 0),
                new SetWristAngle(ArmPositions.CUBE_PLACE_MID.wrist),
                new ArmExtension2PosTolerance(ArmPositions.CUBE_PLACE_HIGH.extension),
                new IntakeOut(),
                new WaitCommand(0.15),
                new Arm2PosCooldown(ArmPositions.STOW),
                new WaitCommand(0.1),
                new IntakeNone()
                );
    }

}
