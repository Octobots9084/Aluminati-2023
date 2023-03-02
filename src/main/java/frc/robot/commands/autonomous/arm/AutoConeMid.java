package frc.robot.commands.autonomous.arm;

import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.arm.CaliGirlsBottomMoveDownALittle;
import frc.robot.commands.arm.Intake.IntakeIn;
import frc.robot.commands.arm.Intake.IntakeNone;
import frc.robot.commands.arm.Intake.IntakeOut;
import frc.robot.commands.arm.Intake.IntakeOutALittle;
import frc.robot.commands.arm.LowLevel.MoveArmExtensionToPos;
import frc.robot.commands.arm.LowLevel.MoveArmRotationToPos;
import frc.robot.commands.arm.LowLevel.MoveArmWristToPos;
import frc.robot.subsystems.arm.ArmExtension;
import frc.robot.subsystems.arm.ArmPositions;
import frc.robot.subsystems.arm.CaliGirls;

public class AutoConeMid extends SequentialCommandGroup{
    ArmPositions aPosition;
    ArmPositions drivePosition;
    CaliGirls caliGirls;
    ArmExtension armExtension;

    public AutoConeMid() {
        this.aPosition = ArmPositions.PRE_CONE_PLACE_MID;
        this.drivePosition = ArmPositions.DRIVE_WITH_PIECE;
        this.caliGirls = CaliGirls.getInstance();
        this.armExtension = ArmExtension.getInstance();
        addCommands(
            new IntakeIn(),
            new MoveArmRotationToPos(aPosition.armAngle, aPosition.angleHold), new MoveArmWristToPos(aPosition.wrist), 
            new WaitCommand(.2),
            new MoveArmExtensionToPos(aPosition.extension),
            new WaitCommand(0.4),
            new CaliGirlsBottomMoveDownALittle(),
            new WaitCommand(.25),
            new IntakeOutALittle(),
            new WaitCommand(0.05),
            new IntakeNone(),
            new MoveArmExtensionToPos(ArmExtension.getInstance().lastpos-10),
            new WaitCommand(.25),
            new MoveArmRotationToPos(aPosition.armAngle, aPosition.angleHold),
            new WaitCommand(.25),
            new MoveArmWristToPos(drivePosition.wrist), new MoveArmExtensionToPos(drivePosition.extension),new MoveArmRotationToPos(drivePosition.armAngle, drivePosition.angleHold)
        );
    
    }
}
