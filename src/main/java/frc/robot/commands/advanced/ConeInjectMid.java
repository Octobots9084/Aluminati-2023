package frc.robot.commands.advanced;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.arm.CaliGirlsBottomMoveDownALittle;
import frc.robot.commands.arm.basic.instant.ArmPosInstant;
import frc.robot.commands.arm.basic.instant.ExtensionPosInstant;
import frc.robot.commands.arm.basic.instant.IntakeSpeedInstant;
import frc.robot.commands.arm.basic.tolerance.ExtensionPosTolerance;
import frc.robot.commands.arm.basic.tolerance.CaliBottomPosTolerance;
import frc.robot.commands.arm.basic.tolerance.CaliTopPosTolerance;
import frc.robot.subsystems.arm.ArmPositions;

public class ConeInjectMid extends SequentialCommandGroup {
    
    public ConeInjectMid() {
        ArmPositions aPosition = ArmPositions.PRE_CONE_PLACE_MID;
        addCommands(
                new ExtensionPosTolerance(0).withTimeout(2),
                new CaliBottomPosTolerance(aPosition.armAngle, aPosition.angleHold).withTimeout(2),
                new CaliTopPosTolerance(aPosition.wrist),
                new ExtensionPosTolerance(aPosition.extension).withTimeout(5),
                // new SetArmAngle(pos - 0.1, caliGirls.getBottomKf()),
                // new MoveArmRotationToPos(pos, caliGirls.getBottomKf()),
                new CaliGirlsBottomMoveDownALittle(),
                new WaitCommand(0.25),
                new IntakeSpeedInstant(1),
                // new IntakeSpeedInstant(3),
                new WaitCommand(0.15),
                new ExtensionPosInstant(0),
                new ArmPosInstant(ArmPositions.STOW));
                new IntakeSpeedInstant(0);
    }

}
