package frc.robot.commands.autonomous.arm;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.arm.CaliGirlsBottomMoveDownALittle;
import frc.robot.commands.arm.basic.timed.ExtensionPosTimed;
import frc.robot.commands.arm.basic.timed.CaliTopPosTimed;
import frc.robot.commands.arm.basic.tolerance.CaliBottomPosTolerance;
import frc.robot.commands.arm.intake.basic.IntakeIn;
import frc.robot.commands.arm.intake.basic.IntakeNone;
import frc.robot.commands.arm.intake.basic.IntakeOutALittle;
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
            new IntakeIn(),
            new CaliBottomPosTolerance(aPosition.armAngle, aPosition.angleHold), new CaliTopPosTimed(aPosition.wrist), 
            new WaitCommand(.2),
            new ExtensionPosTimed(aPosition.extension),
            new WaitCommand(0.4),
            new CaliGirlsBottomMoveDownALittle(),
            new WaitCommand(.25),
            new IntakeOutALittle(),
            new WaitCommand(0.05),
            new IntakeNone(),
            new ExtensionPosTimed(ArmExtension.getInstance().lastpos-10),
            new WaitCommand(.25),
            new CaliBottomPosTolerance(aPosition.armAngle, aPosition.angleHold),
            new WaitCommand(.25),
            new CaliTopPosTimed(drivePosition.wrist), new ExtensionPosTimed(drivePosition.extension),new CaliBottomPosTolerance(drivePosition.armAngle, drivePosition.angleHold)
        );
    
    }
}
