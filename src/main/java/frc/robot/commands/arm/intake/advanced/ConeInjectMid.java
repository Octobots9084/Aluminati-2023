package frc.robot.commands.arm.intake.advanced;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.arm.CaliGirlsBottomMoveDownALittle;
import frc.robot.commands.arm.basic.MoveArmRotationToPos;
import frc.robot.commands.arm.basic.SetArmAngle;
import frc.robot.commands.arm.basic.SetArmExtension;
import frc.robot.commands.arm.intake.basic.IntakeNone;
import frc.robot.commands.arm.intake.basic.IntakeOut;
import frc.robot.commands.arm.intake.basic.IntakeOutALittle;
import frc.robot.commands.arm.slow.MoveArmToPositionGoingDown;
import frc.robot.subsystems.arm.ArmPositions;
import frc.robot.subsystems.arm.CaliGirls;

public class ConeInjectMid extends SequentialCommandGroup {
    public ConeInjectMid() {
        addCommands(
                // new SetArmAngle(pos - 0.1, caliGirls.getBottomKf()),
                // new MoveArmRotationToPos(pos, caliGirls.getBottomKf()),
                new CaliGirlsBottomMoveDownALittle(),
                new CaliGirlsBottomMoveDownALittle(),
                new WaitCommand(0.25),
                new IntakeOutALittle(),
                // new IntakeOut(),
                new WaitCommand(0.15),
                new SetArmExtension(0),
                new MoveArmToPositionGoingDown(ArmPositions.STOW));
                new IntakeNone();
    }

}
