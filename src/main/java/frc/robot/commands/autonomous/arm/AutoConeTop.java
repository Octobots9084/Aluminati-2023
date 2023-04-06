package frc.robot.commands.autonomous.arm;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.arm.CaliGirlsBottomMoveDownALittle;
import frc.robot.commands.arm.basic.instant.IntakeSpeedInstant;
import frc.robot.commands.arm.basic.instant.CaliTopPosInstant;
import frc.robot.commands.arm.basic.tolerance.ExtensionPosTolerance;
import frc.robot.commands.arm.basic.tolerance.CaliBottomPosTolerance;
import frc.robot.commands.arm.basic.tolerance.CaliTopPosTolerance;
import frc.robot.commands.arm.yeet.Arm2PosStow;
import frc.robot.subsystems.arm.ArmExtension;
import frc.robot.subsystems.arm.ArmPositions;
import frc.robot.subsystems.arm.CaliGirls;

public class AutoConeTop extends SequentialCommandGroup{
    ArmPositions aPosition;
    ArmPositions drivePosition;
    ArmPositions driveWithPiece;
    CaliGirls caliGirls;
    ArmExtension armExtension;

    public AutoConeTop() {
        this.aPosition = ArmPositions.PRE_CONE_PLACE_HIGH;
        this.drivePosition = ArmPositions.STOW;
        this.caliGirls = CaliGirls.getInstance();
        this.armExtension = ArmExtension.getInstance();
        addCommands(
            // new SetWristAngle(ArmPositions.STOW.wrist+0.1),
            new IntakeSpeedInstant(-10),
            // new WaitCommand(0.6),
            // new SetItemMode(true),
            new CaliTopPosInstant(caliGirls.getTopPos()),
            new ExtensionPosTolerance(0).withTimeout(2),
            new CaliBottomPosTolerance(aPosition.armAngle, aPosition.angleHold).withTimeout(2),
            new CaliTopPosTolerance(aPosition.wrist),
            new ExtensionPosTolerance(aPosition.extension).withTimeout(5),
            new CaliGirlsBottomMoveDownALittle(),
            new WaitCommand(0.25),
            new IntakeSpeedInstant(3),
            new WaitCommand(0.05),
            new ExtensionPosTolerance(0),
            new CaliBottomPosTolerance(ArmPositions.PRE_CONE_PLACE_HIGH.armAngle, CaliGirls.getInstance().getBottomKf()),
            new Arm2PosStow(ArmPositions.STOW),
            new IntakeSpeedInstant(0)

            // new SetArmAngle(aPosition.armAngle, aPosition.angleHold), 
            // new WaitCommand(.5),
            // new SetArmExtension(aPosition.extension),
            // new WaitCommand(0.8),
            // new SetWristAngle(aPosition.wrist), 
            // new WaitCommand(.35),
            // new CaliGirlsBottomMoveDownALittle(),
            // new WaitCommand(.25),
            // new IntakeSpeedInstant(1),
            // new WaitCommand(0.05),
            // new IntakeSpeedInstant(0),
            // new SetArmExtension(ArmExtension.getInstance().lastpos-10),
            // new WaitCommand(.25),
            // new SetArmAngle(aPosition.armAngle, aPosition.angleHold),
            // new WaitCommand(.5),
            // new Arm2PosStow(drivePosition)

            // new MoveArmWristToPos(drivePosition.wrist), new MoveArmExtensionToPos(drivePosition.extension),new MoveArmRotationToPos(drivePosition.armAngle, drivePosition.angleHold)
        );
    
    }
}
