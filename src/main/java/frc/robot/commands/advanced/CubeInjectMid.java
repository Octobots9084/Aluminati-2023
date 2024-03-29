package frc.robot.commands.advanced;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.arm.basic.instant.CaliTopPosInstant;
import frc.robot.commands.arm.basic.instant.IntakeSpeedInstant;
import frc.robot.commands.arm.basic.tolerance.ExtensionPosTolerance;
import frc.robot.commands.arm.basic.tolerance.CaliBottomPosTolerance;
import frc.robot.commands.arm.yeet.Arm2PosCooldown;
import frc.robot.subsystems.arm.ArmPositions;

public class CubeInjectMid extends SequentialCommandGroup {
    public CubeInjectMid() {
        addCommands(
                // new SetArmAngle(pos - 0.1, caliGirls.getBottomKf()),
                // new MoveArmRotationToPos(pos, caliGirls.getBottomKf()),
                new ExtensionPosTolerance(0).withTimeout(1),
                new CaliBottomPosTolerance(ArmPositions.CUBE_PLACE_MID.armAngle, 0),
                new CaliTopPosInstant(ArmPositions.CUBE_PLACE_MID.wrist),
                new IntakeSpeedInstant(3),
                new WaitCommand(0.5),
                new Arm2PosCooldown(ArmPositions.STOW),
                new IntakeSpeedInstant(0));
    }

}
