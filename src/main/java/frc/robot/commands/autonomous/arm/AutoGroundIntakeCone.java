package frc.robot.commands.autonomous.arm;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.arm.intake.basic.IntakeIn;
import frc.robot.commands.arm.intake.basic.IntakeNone;
import frc.robot.commands.arm.intake.basic.IntakeOut;
import frc.robot.commands.arm.lowLevel.MoveArmExtensionToPos;
import frc.robot.commands.arm.lowLevel.MoveArmRotationToPos;
import frc.robot.commands.arm.lowLevel.MoveArmWristToPos;
import frc.robot.subsystems.arm.ArmExtension;
import frc.robot.subsystems.arm.ArmPositions;
import frc.robot.subsystems.arm.CaliGirls;

public class AutoGroundIntakeCone extends SequentialCommandGroup{
    ArmPositions aPosition;
    CaliGirls caliGirls;
    ArmExtension armExtension;

    public AutoGroundIntakeCone() {
        this.aPosition = ArmPositions.CUBE_INTAKE_FLOOR;
        this.caliGirls = CaliGirls.getInstance();
        addCommands(new MoveArmWristToPos(aPosition.wrist), new MoveArmExtensionToPos(aPosition.extension), new MoveArmRotationToPos(aPosition.armAngle, aPosition.angleHold));
        new IntakeIn();
        new WaitCommand(0.1);
        new IntakeNone();
        this.aPosition = ArmPositions.DRIVE_WITH_PIECE;
        addCommands(new MoveArmRotationToPos(aPosition.armAngle, aPosition.angleHold), new MoveArmWristToPos(aPosition.wrist), new MoveArmExtensionToPos(aPosition.extension));
    }
}

