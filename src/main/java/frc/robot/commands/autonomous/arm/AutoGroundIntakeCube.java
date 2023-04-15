package frc.robot.commands.autonomous.arm;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.arm.basic.instant.IntakeSpeedInstant;
import frc.robot.commands.arm.basic.tolerance.CaliBottomPosTolerance;
import frc.robot.commands.arm.basic.tolerance.CaliTopPosTolerance;
import frc.robot.commands.arm.basic.tolerance.ExtensionPosTolerance;
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
        addCommands(new CaliTopPosTolerance(aPosition.wrist), new ExtensionPosTolerance(aPosition.extension), new CaliBottomPosTolerance(aPosition.armAngle, aPosition.angleHold),
        new IntakeSpeedInstant(-10),
        new WaitCommand(0.1),
        new IntakeSpeedInstant(0),
        new CaliBottomPosTolerance(drivePosition.armAngle, drivePosition.angleHold), new CaliTopPosTolerance(drivePosition.wrist), new ExtensionPosTolerance(drivePosition.extension)
        );
            }
}

