package frc.robot.commands.advanced;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.arm.CaliGirlsBottomMoveDownALittle;
import frc.robot.commands.arm.basic.instant.ArmPosInstant;
import frc.robot.commands.arm.basic.instant.IntakeSpeedInstant;
import frc.robot.commands.arm.basic.tolerance.ExtensionPosTolerance;
import frc.robot.commands.arm.basic.tolerance.CaliBottomPosTolerance;
import frc.robot.commands.arm.basic.tolerance.CaliTopPosTolerance;
import frc.robot.subsystems.arm.ArmPositions;

public class ConeInjectHigh extends SequentialCommandGroup {

    private ArmPositions aPosition;

    public ConeInjectHigh() {
        this.aPosition = ArmPositions.PRE_CONE_PLACE_HIGH;

        addCommands(
                new ExtensionPosTolerance(0).withTimeout(0.25),
                new CaliBottomPosTolerance(aPosition.armAngle, aPosition.angleHold).withTimeout(2),
                new CaliTopPosTolerance(aPosition.wrist).withTimeout(3),
                new ExtensionPosTolerance(aPosition.extension).withTimeout(5),
                new CaliGirlsBottomMoveDownALittle(),
                
                new WaitCommand(0.25),
                new IntakeSpeedInstant(2),
                
                new WaitCommand(0.1),
                new ExtensionPosTolerance(5),
                new ExtensionPosTolerance(0),
                new WaitCommand(0.15),
                new ArmPosInstant(ArmPositions.STOW),
                new IntakeSpeedInstant(0));
    }

}
