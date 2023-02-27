package frc.robot.commands.arm.Intake;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.arm.LowLevel.MoveArmRotationToPos;
import frc.robot.subsystems.arm.CaliGirls;

public class ConeInject extends SequentialCommandGroup {
    CaliGirls caliGirls;

    public ConeInject() {
        this.caliGirls = CaliGirls.getInstance();
        var pos = caliGirls.getBottomPos() - 0.05;

        addCommands(
                // new SetArmAngle(pos - 0.1, caliGirls.getBottomKf()),
                new MoveArmRotationToPos(pos, caliGirls.getBottomKf()),
                new WaitCommand(1),
                new IntakeNone());
    }

}
