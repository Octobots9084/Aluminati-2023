package frc.robot.commands.autonomous.arm;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.arm.basic.timed.ExtensionPosTimed;
import frc.robot.commands.arm.basic.timed.CaliTopPosTimed;
import frc.robot.commands.arm.basic.tolerance.CaliBottomPosTolerance;
import frc.robot.commands.arm.intake.basic.IntakeIn;
import frc.robot.commands.arm.intake.basic.IntakeNone;
import frc.robot.subsystems.arm.ArmExtension;
import frc.robot.subsystems.arm.ArmPositions;
import frc.robot.subsystems.arm.CaliGirls;

public class AutoGroundIntakeCube extends SequentialCommandGroup{
    ArmPositions aPosition;
    ArmPositions drivePosition;
    CaliGirls caliGirls;
    ArmExtension armExtension;

    public AutoGroundIntakeCube() {
        this.aPosition = ArmPositions.DEPRECIATED_CUBE_INTAKE_FLOOR;
        this.drivePosition = ArmPositions.STOW;
        this.caliGirls = CaliGirls.getInstance();
        addCommands(new CaliTopPosTimed(aPosition.wrist), new ExtensionPosTimed(aPosition.extension), new CaliBottomPosTolerance(aPosition.armAngle, aPosition.angleHold),
        new IntakeIn(),
        new WaitCommand(0.1),
        new IntakeNone(),
        new CaliBottomPosTolerance(drivePosition.armAngle, drivePosition.angleHold), new CaliTopPosTimed(drivePosition.wrist), new ExtensionPosTimed(drivePosition.extension)
        );
            }
}

