package frc.robot.commands.arm.intake.advanced;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.arm.CaliGirlsBottomMoveDownALittle;
import frc.robot.commands.arm.basic.MoveArmRotationToPos;
import frc.robot.commands.arm.basic.SetArmAngle;
import frc.robot.commands.arm.intake.basic.IntakeNone;
import frc.robot.commands.arm.slow.MoveArmToPositionGoingDown;
import frc.robot.commands.arm.slow.MoveArmToPositionGoingUp;
import frc.robot.commands.arm.yeet.Arm2PosCooldown;
import frc.robot.subsystems.arm.ArmPositions;
import frc.robot.subsystems.arm.CaliGirls;

public class CubeInject extends SequentialCommandGroup {
    public CubeInject() {
        addCommands(
                // new SetArmAngle(pos - 0.1, caliGirls.getBottomKf()),
                // new MoveArmRotationToPos(pos, caliGirls.getBottomKf()),
                new IntakeOutWithTimeout(),
                new WaitCommand(0.3),
                new Arm2PosCooldown(ArmPositions.DRIVE_WITHOUT_PIECE));
    }

}
