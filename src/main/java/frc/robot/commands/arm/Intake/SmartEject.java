package frc.robot.commands.arm.Intake;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.arm.CaliGirlsBottomMoveDownALittle;
import frc.robot.subsystems.arm.Roller;

public class SmartEject extends SequentialCommandGroup{
    
    private boolean itemMode;
    private Roller roller;
    
    public SmartEject() {
        this.roller = Roller.getInstance();
        this.itemMode = roller.getItemMode();
        if (itemMode) {
            addCommands(new ConeInject());
        } else {
            addCommands(new IntakeOut().withTimeout(2));
        }
    }

}
