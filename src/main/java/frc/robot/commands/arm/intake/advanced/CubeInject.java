package frc.robot.commands.arm.intake.advanced;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.arm.yeet.Arm2PosCooldown;
import frc.robot.subsystems.arm.ArmPositions;

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
