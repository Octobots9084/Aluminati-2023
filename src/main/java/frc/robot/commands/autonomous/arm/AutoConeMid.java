package frc.robot.commands.autonomous.arm;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.arm.CaliGirlsBottomMoveDownALittle;
import frc.robot.commands.arm.basic.instant.IntakeSpeedInstant;
import frc.robot.commands.arm.basic.tolerance.CaliBottomPosTolerance;
import frc.robot.commands.arm.basic.tolerance.CaliTopPosTolerance;
import frc.robot.commands.arm.basic.tolerance.ExtensionPosTolerance;
import frc.robot.subsystems.arm.ArmExtension;
import frc.robot.subsystems.arm.ArmPositions;
import frc.robot.subsystems.arm.CaliGirls;

public class AutoConeMid extends SequentialCommandGroup{
    ArmPositions aPosition;
    ArmPositions drivePosition;
    CaliGirls caliGirls;
    ArmExtension armExtension;

    public AutoConeMid() {
        this.aPosition = ArmPositions.PRE_CONE_PLACE_MID;
        this.drivePosition = ArmPositions.STOW;
        this.caliGirls = CaliGirls.getInstance();
        this.armExtension = ArmExtension.getInstance();
        addCommands(
            new IntakeSpeedInstant(-10),
            new CaliBottomPosTolerance(aPosition.armAngle, aPosition.angleHold), new CaliTopPosTolerance(aPosition.wrist), 
            new WaitCommand(.2),
            new ExtensionPosTolerance(aPosition.extension),
            new WaitCommand(0.4),
            new CaliGirlsBottomMoveDownALittle(),
            new WaitCommand(.25),
            new IntakeSpeedInstant(1),
            new WaitCommand(0.05),
            new IntakeSpeedInstant(0),
            new ExtensionPosTolerance(ArmExtension.getInstance().lastpos-10),
            new CaliBottomPosTolerance(aPosition.armAngle, aPosition.angleHold),
            new CaliTopPosTolerance(drivePosition.wrist), new ExtensionPosTolerance(drivePosition.extension),new CaliBottomPosTolerance(drivePosition.armAngle, drivePosition.angleHold)
        );
    
    }
}
