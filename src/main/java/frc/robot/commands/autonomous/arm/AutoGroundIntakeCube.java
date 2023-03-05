package frc.robot.commands.autonomous.arm;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.arm.basic.MoveArmExtensionToPos;
import frc.robot.commands.arm.basic.MoveArmRotationToPos;
import frc.robot.commands.arm.basic.MoveArmWristToPos;
import frc.robot.commands.arm.intake.basic.IntakeIn;
import frc.robot.commands.arm.intake.basic.IntakeNone;
import frc.robot.commands.arm.intake.basic.IntakeOut;
import frc.robot.subsystems.arm.ArmExtension;
import frc.robot.subsystems.arm.ArmPositions;
import frc.robot.subsystems.arm.CaliGirls;

public class AutoGroundIntakeCube extends SequentialCommandGroup{
    ArmPositions aPosition;
    ArmPositions drivePosition;
    CaliGirls caliGirls;
    ArmExtension armExtension;

    public AutoGroundIntakeCube() {
        this.aPosition = ArmPositions.CUBE_INTAKE_FLOOR;
        this.drivePosition = ArmPositions.DRIVE_WITH_PIECE;
        this.caliGirls = CaliGirls.getInstance();
        addCommands(new MoveArmWristToPos(aPosition.wrist), new MoveArmExtensionToPos(aPosition.extension), new MoveArmRotationToPos(aPosition.armAngle, aPosition.angleHold),
        new IntakeIn(),
        new WaitCommand(0.1),
        new IntakeNone(),
        new MoveArmRotationToPos(drivePosition.armAngle, drivePosition.angleHold), new MoveArmWristToPos(drivePosition.wrist), new MoveArmExtensionToPos(drivePosition.extension)
        );
            }
}

