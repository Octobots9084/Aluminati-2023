package frc.robot.commands.arm.intake.advanced;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
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
                new IntakeOut(),
                new WaitCommand(0.5),
                new Arm2PosCooldown(ArmPositions.STOW),
                new IntakeNone()
                );
    }

}
