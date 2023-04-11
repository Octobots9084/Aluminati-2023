package frc.robot.commands.advanced;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.arm.CaliGirlsBottomMoveDownALittle;
import frc.robot.commands.arm.basic.instant.ArmPosInstant;
import frc.robot.commands.arm.basic.instant.IntakeSpeedInstant;
import frc.robot.commands.arm.basic.tolerance.ExtensionPosTolerance;
import frc.robot.commands.arm.basic.tolerance.CaliBottomPosTolerance;
import frc.robot.commands.arm.basic.tolerance.CaliTopPosTolerance;
import frc.robot.subsystems.arm.ArmPositions;
import frc.robot.subsystems.arm.CaliGirls;

public class ConeInjectHigh extends SequentialCommandGroup {
    
    private ArmPositions aPosition;

    public ConeInjectHigh() {
        this.aPosition = ArmPositions.PRE_CONE_PLACE_HIGH;

        addCommands(
            new ExtensionPosTolerance(0).withTimeout(2),
            new CaliBottomPosTolerance(aPosition.armAngle, aPosition.angleHold).withTimeout(2),
            new CaliTopPosTolerance(aPosition.wrist),
            new ExtensionPosTolerance(aPosition.extension).withTimeout(5),
            new CaliGirlsBottomMoveDownALittle(),
            new WaitCommand(0.25),
            new IntakeSpeedInstant(3),
            new WaitCommand(0.15),
            new ExtensionPosTolerance(0),
            new WaitCommand(0.25),
            new CaliBottomPosTolerance(ArmPositions.PRE_CONE_PLACE_HIGH.armAngle, CaliGirls.getInstance().getBottomKf()),
            new ArmPosInstant(ArmPositions.STOW),
            new IntakeSpeedInstant(0));
    }

}
