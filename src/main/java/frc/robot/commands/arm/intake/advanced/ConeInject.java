package frc.robot.commands.arm.intake.advanced;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.arm.CaliGirlsBottomMoveDownALittle;
import frc.robot.commands.arm.intake.basic.IntakeNone;
import frc.robot.commands.arm.intake.basic.IntakeOutALittle;
import frc.robot.commands.arm.yeet.Arm2PosCooldown;
import frc.robot.subsystems.arm.ArmPositions;

public class ConeInject extends SequentialCommandGroup {
    public ConeInject() {
        addCommands(
                // new SetArmAngle(pos - 0.1, caliGirls.getBottomKf()),
                // new MoveArmRotationToPos(pos, caliGirls.getBottomKf()),
                new CaliGirlsBottomMoveDownALittle(),
                new WaitCommand(0.25),
                new IntakeOutALittle(),
                new WaitCommand(1),
                new Arm2PosCooldown(ArmPositions.DRIVE_WITHOUT_PIECE));
                new IntakeNone();
    }

}
