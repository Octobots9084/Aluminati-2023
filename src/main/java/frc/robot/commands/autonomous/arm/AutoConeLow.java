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

public class AutoConeLow extends SequentialCommandGroup{
    ArmPositions aPosition;
    CaliGirls caliGirls;
    ArmExtension armExtension;

    public AutoConeLow() {
        this.aPosition = ArmPositions.CONE_PLACE_LOW;
        this.caliGirls = CaliGirls.getInstance();
        this.armExtension = ArmExtension.getInstance();
        new IntakeIn();
        new WaitCommand(.1);       
        new IntakeNone();
        this.aPosition = ArmPositions.DRIVE_WITHOUT_PIECE;
        addCommands(new MoveArmRotationToPos(aPosition.armAngle, aPosition.angleHold), new MoveArmWristToPos(aPosition.wrist), new MoveArmExtensionToPos(aPosition.extension));
    }
}
