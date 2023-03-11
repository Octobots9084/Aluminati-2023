package frc.robot.commands.autonomous.arm;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.arm.CaliGirlsBottomMoveDownALittle;
import frc.robot.commands.arm.basic.SetArmAngle;
import frc.robot.commands.arm.basic.SetArmExtension;
import frc.robot.commands.arm.basic.SetWristAngle;
import frc.robot.commands.arm.intake.basic.IntakeIn;
import frc.robot.commands.arm.intake.basic.IntakeNone;
import frc.robot.commands.arm.intake.basic.IntakeOutALittle;
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
        this.drivePosition = ArmPositions.DRIVE_WITHOUT_PIECE;
        this.caliGirls = CaliGirls.getInstance();
        this.armExtension = ArmExtension.getInstance();
        addCommands(
            new IntakeIn(),
            new WaitCommand(0.6),
            new SetArmAngle(aPosition.armAngle, aPosition.angleHold), 
            new WaitCommand(.5),
            new SetArmExtension(aPosition.extension),
            new WaitCommand(0.8),
            new SetWristAngle(aPosition.wrist), 
            new WaitCommand(.35),
            new CaliGirlsBottomMoveDownALittle(),
            new WaitCommand(.25),
            new IntakeOutALittle(),
            new WaitCommand(0.05),
            new IntakeNone(),
            new SetArmExtension(ArmExtension.getInstance().lastpos-10),
            new WaitCommand(.25),
            new SetArmAngle(aPosition.armAngle, aPosition.angleHold),
            new WaitCommand(.5),
            new Arm2PosStow(drivePosition)
            // new MoveArmWristToPos(drivePosition.wrist), new MoveArmExtensionToPos(drivePosition.extension),new MoveArmRotationToPos(drivePosition.armAngle, drivePosition.angleHold)
        );
    
    }
}
