package frc.robot.commands.arm.slow;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.arm.ArmExtension;
import frc.robot.subsystems.arm.CaliGirls;

public class MoveArmToPos extends SequentialCommandGroup{
    CaliGirls caliGirls;
    ArmExtension armExtension;


    public MoveArmToPos(Command[] armCommands) {
        this.caliGirls = CaliGirls.getInstance();
        this.armExtension = ArmExtension.getInstance();
        addCommands(armCommands[0],armCommands[1],armCommands[2]);
    }
}
