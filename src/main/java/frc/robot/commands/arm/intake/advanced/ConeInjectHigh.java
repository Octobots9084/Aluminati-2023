package frc.robot.commands.arm.intake.advanced;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.arm.CaliGirlsBottomMoveDownALittle;
import frc.robot.commands.arm.basic.ArmExtension2PosTolerance;
import frc.robot.commands.arm.basic.MoveArmRotationToPos;
import frc.robot.commands.arm.basic.MoveArmWristToPos;
import frc.robot.commands.arm.basic.SetArmAngle;
import frc.robot.commands.arm.basic.SetArmExtension;
import frc.robot.commands.arm.intake.basic.IntakeNone;
import frc.robot.commands.arm.intake.basic.IntakeOut;
import frc.robot.commands.arm.intake.basic.IntakeOutALittle;
import frc.robot.commands.arm.slow.MoveArmToPositionGoingDown;
import frc.robot.subsystems.arm.ArmPositions;
import frc.robot.subsystems.arm.CaliGirls;

public class ConeInjectHigh extends SequentialCommandGroup {
    
    private ArmPositions aPosition;

    public ConeInjectHigh() {
        this.aPosition = ArmPositions.PRE_CONE_PLACE_HIGH;

        addCommands(
            new ArmExtension2PosTolerance(0).withTimeout(2),
            new MoveArmRotationToPos(aPosition.armAngle, aPosition.angleHold).withTimeout(2),
            new MoveArmWristToPos(aPosition.wrist),
            new ArmExtension2PosTolerance(aPosition.extension).withTimeout(5),
            new CaliGirlsBottomMoveDownALittle(),
            new WaitCommand(0.25),
            new IntakeOut(),
            new WaitCommand(0.05),
            new SetArmExtension(0),
            new WaitCommand(0.05),
            new SetArmAngle(ArmPositions.PRE_CONE_PLACE_HIGH.armAngle, CaliGirls.getInstance().getBottomKf()),
            new WaitCommand(0.75),
            new MoveArmToPositionGoingDown(ArmPositions.STOW),
            new IntakeNone());
    }

}
