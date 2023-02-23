package frc.robot.commands.autonomous.arm;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.arm.Intake.IntakeIn;
import frc.robot.commands.arm.Intake.IntakeNone;
import frc.robot.commands.arm.Intake.IntakeOut;
import frc.robot.commands.arm.LowLevel.MoveArmExtensionToPos;
import frc.robot.commands.arm.LowLevel.MoveArmRotationToPos;
import frc.robot.commands.arm.LowLevel.MoveArmWristToPos;
import frc.robot.subsystems.arm.ArmExtension;
import frc.robot.subsystems.arm.ArmPositions;
import frc.robot.subsystems.arm.CaliGirls;

public class AutoGroundIntakeCube extends SequentialCommandGroup{
    ArmPositions aPosition;
    CaliGirls caliGirls;
    ArmExtension armExtension;

    public AutoGroundIntakeCube() {
        this.aPosition = ArmPositions.CUBE_INTAKE_FLOOR;
        this.caliGirls = CaliGirls.getInstance();
        addCommands(new MoveArmWristToPos(aPosition.wrist), new MoveArmExtensionToPos(aPosition.extension), new MoveArmRotationToPos(aPosition.armAngle));
        new IntakeIn();
        new WaitCommand(0.1);
        new IntakeNone();
        this.aPosition = ArmPositions.DRIVE_WITH_PIECE;
        addCommands(new MoveArmRotationToPos(aPosition.armAngle), new MoveArmWristToPos(aPosition.wrist), new MoveArmExtensionToPos(aPosition.extension));
    }
}

