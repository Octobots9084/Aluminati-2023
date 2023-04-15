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

public class AutoCubeTop extends SequentialCommandGroup{
    ArmPositions aPosition;
    ArmPositions drivePosition;
    CaliGirls caliGirls;
    ArmExtension armExtension;

    public AutoCubeTop() {
        this.aPosition = ArmPositions.CUBE_PLACE_HIGH;
        this.drivePosition = ArmPositions.STOW;
        this.caliGirls = CaliGirls.getInstance();
        this.armExtension = ArmExtension.getInstance();
        addCommands(new CaliBottomPosTolerance(aPosition.armAngle, aPosition.angleHold), new CaliTopPosTolerance(aPosition.wrist), new ExtensionPosTolerance(aPosition.extension),
        new IntakeSpeedInstant(3),
        new WaitCommand(.5),
        new IntakeSpeedInstant(0),
        new ExtensionPosTolerance(drivePosition.extension), new CaliTopPosTolerance(drivePosition.wrist), new CaliBottomPosTolerance(drivePosition.armAngle, drivePosition.angleHold)
        );
    
    }
}
