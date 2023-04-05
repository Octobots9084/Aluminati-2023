package frc.robot.commands.autonomous.arm;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.arm.basic.timed.ExtensionPosTimed;
import frc.robot.commands.arm.basic.instant.IntakeSpeedInstant;
import frc.robot.commands.arm.basic.timed.CaliTopPosTimed;
import frc.robot.commands.arm.basic.tolerance.CaliBottomPosTolerance;
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
        addCommands(new CaliBottomPosTolerance(aPosition.armAngle, aPosition.angleHold), new CaliTopPosTimed(aPosition.wrist), new ExtensionPosTimed(aPosition.extension),
        new IntakeSpeedInstant(3),
        new WaitCommand(.5),
        new IntakeSpeedInstant(0),
        new ExtensionPosTimed(drivePosition.extension), new CaliTopPosTimed(drivePosition.wrist), new CaliBottomPosTolerance(drivePosition.armAngle, drivePosition.angleHold)
        );
    
    }
}
