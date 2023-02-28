package frc.robot.commands.arm.Intake;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.arm.CaliGirlsBottomMoveDownALittle;
import frc.robot.commands.arm.LowLevel.MoveArmRotationToPos;
import frc.robot.subsystems.arm.CaliGirls;

public class ConeInject extends SequentialCommandGroup {
    public ConeInject() {
        addCommands(
                // new SetArmAngle(pos - 0.1, caliGirls.getBottomKf()),
                //new MoveArmRotationToPos(pos, caliGirls.getBottomKf()),
                new CaliGirlsBottomMoveDownALittle(),
                new WaitCommand(1),
                new IntakeNone());
    }

}
