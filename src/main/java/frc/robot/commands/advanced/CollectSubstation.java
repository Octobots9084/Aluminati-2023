package frc.robot.commands.advanced;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.arm.basic.instant.IntakeSpeedInstant;
import frc.robot.commands.arm.basic.tolerance.CaliBottomPosTolerance;
import frc.robot.commands.arm.basic.tolerance.ExtensionPosTolerance;
import frc.robot.commands.arm.basic.instant.ArmPosInstant;
import frc.robot.commands.arm.basic.instant.CaliTopPosInstant;
import frc.robot.subsystems.arm.ArmPositions;
public class CollectSubstation extends SequentialCommandGroup{

    public CollectSubstation() {
        addCommands(new ExtensionPosTolerance(0).withTimeout(0.1), new CaliTopPosInstant(ArmPositions.AUTO_ALIGN.wrist), new CaliBottomPosTolerance(ArmPositions.AUTO_ALIGN.armAngle, 0, 0.1), new ArmPosInstant(ArmPositions.INTAKE_SUBSTATION), new IntakeSpeedInstant(-10));
    }
}
