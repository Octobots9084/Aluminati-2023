package frc.robot.commands.autonomous.arm;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.arm.basic.timed.MoveArmExtensionToPos;
import frc.robot.commands.arm.basic.timed.MoveArmWristToPos;
import frc.robot.commands.arm.basic.tolerance.MoveArmRotationToPos;
import frc.robot.commands.arm.intake.basic.IntakeNone;
import frc.robot.commands.arm.intake.basic.IntakeOut;
import frc.robot.subsystems.arm.ArmExtension;
import frc.robot.subsystems.arm.ArmPositions;
import frc.robot.subsystems.arm.CaliGirls;

public class AutoConeLow extends SequentialCommandGroup{
    ArmPositions aPosition;
    ArmPositions drivePosition;
    CaliGirls caliGirls;
    ArmExtension armExtension;

    public AutoConeLow() {
        this.aPosition = ArmPositions.INTAKE_GROUND;
        this.drivePosition = ArmPositions.STOW;
        this.caliGirls = CaliGirls.getInstance();
        this.armExtension = ArmExtension.getInstance();
        addCommands(new MoveArmRotationToPos(aPosition.armAngle, aPosition.angleHold), new MoveArmWristToPos(aPosition.wrist), new MoveArmExtensionToPos(aPosition.extension),
        new IntakeOut(),
        new WaitCommand(.2),
        new IntakeNone(),
        new MoveArmExtensionToPos(drivePosition.extension), new MoveArmRotationToPos(drivePosition.armAngle, drivePosition.angleHold), new MoveArmWristToPos(drivePosition.wrist)
        );
    
    }
}
