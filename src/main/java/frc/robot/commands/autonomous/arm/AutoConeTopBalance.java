package frc.robot.commands.autonomous.arm;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.arm.CaliGirlsBottomMoveDownALittle;
import frc.robot.commands.arm.basic.instant.IntakeSpeedInstant;
import frc.robot.commands.arm.basic.tolerance.CaliBottomPosTolerance;
import frc.robot.commands.arm.basic.tolerance.CaliTopPosTolerance;
import frc.robot.commands.arm.basic.tolerance.ExtensionPosTolerance;
import frc.robot.subsystems.arm.ArmExtension;
import frc.robot.subsystems.arm.ArmPositions;
import frc.robot.subsystems.arm.CaliGirls;

public class AutoConeTopBalance extends SequentialCommandGroup{
    ArmPositions aPosition;
    ArmPositions drivePosition;
    ArmPositions driveWithPiece;
    CaliGirls caliGirls;
    ArmExtension armExtension;

    public AutoConeTopBalance() {
        this.aPosition = ArmPositions.PRE_CONE_PLACE_HIGH;
        this.drivePosition = ArmPositions.STOW;
        this.caliGirls = CaliGirls.getInstance();
        this.armExtension = ArmExtension.getInstance();
        addCommands(
            new IntakeSpeedInstant(-10),
            new WaitCommand(2),
            new CaliBottomPosTolerance(aPosition.armAngle, aPosition.angleHold), 
            new ExtensionPosTolerance(aPosition.extension),
            new CaliTopPosTolerance(aPosition.wrist), 
            new CaliGirlsBottomMoveDownALittle(),
            new WaitCommand(.25),
            new IntakeSpeedInstant(1),
            new WaitCommand(0.05),
            new IntakeSpeedInstant(0),
            new ExtensionPosTolerance(ArmExtension.getInstance().lastpos-10),
            new CaliBottomPosTolerance(aPosition.armAngle, aPosition.angleHold)
            // new MoveArmWristToPos(drivePosition.wrist), new MoveArmExtensionToPos(drivePosition.extension),new MoveArmRotationToPos(drivePosition.armAngle, drivePosition.angleHold)
        );
    
    }
}
