package frc.robot.commands.autonomous.arm;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.arm.basic.instant.IntakeSpeedInstant;
import frc.robot.commands.arm.yeet.Arm2PosCollect;
import frc.robot.subsystems.arm.ArmExtension;
import frc.robot.subsystems.arm.ArmPositions;
import frc.robot.subsystems.arm.CaliGirls;

public class AutoGroundIntakeCone extends SequentialCommandGroup{
    ArmPositions aPosition;
    CaliGirls caliGirls;
    ArmExtension armExtension;

    public AutoGroundIntakeCone() {
        this.aPosition = ArmPositions.STOW;
        addCommands(new Arm2PosCollect(ArmPositions.INTAKE_GROUND), new IntakeSpeedInstant(-10));
        
    }
}

