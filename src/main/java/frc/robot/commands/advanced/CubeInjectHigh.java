package frc.robot.commands.advanced;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.arm.basic.instant.ArmPosInstant;
import frc.robot.commands.arm.basic.instant.CaliTopPosInstant;
import frc.robot.commands.arm.basic.instant.ExtensionPosInstant;
import frc.robot.commands.arm.basic.instant.IntakeSpeedInstant;
import frc.robot.commands.arm.basic.tolerance.CaliBottomPosTolerance;
import frc.robot.commands.arm.basic.tolerance.CaliTopPosTolerance;
import frc.robot.commands.arm.basic.tolerance.ExtensionPosTolerance;
import frc.robot.commands.arm.yeet.Arm2PosCooldown;
import frc.robot.subsystems.arm.ArmPositions;

public class CubeInjectHigh extends SequentialCommandGroup {
    public CubeInjectHigh() {
        addCommands(
                // new SetArmAngle(pos - 0.1, caliGirls.getBottomKf()),
                // new MoveArmRotationToPos(pos, caliGirls.getBottomKf()),
                new CaliBottomPosTolerance(ArmPositions.CUBE_PLACE_HIGH.armAngle, 0),
                new ExtensionPosInstant(ArmPositions.CUBE_PLACE_HIGH.extension),
                new CaliTopPosTolerance(ArmPositions.CUBE_PLACE_HIGH.wrist),
                new IntakeSpeedInstant(3),
                new WaitCommand(.25),
                new ExtensionPosTolerance(5),
                new Arm2PosCooldown(ArmPositions.STOW),
                new IntakeSpeedInstant(0));

    }

}
