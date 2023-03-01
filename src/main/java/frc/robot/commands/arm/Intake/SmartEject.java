package frc.robot.commands.arm.Intake;

import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.arm.CaliGirlsBottomMoveDownALittle;
import frc.robot.subsystems.arm.Roller;

public class SmartEject extends InstantCommand{
    
    private boolean itemMode;
    private Roller roller;
    
    public SmartEject() {
        this.roller = Roller.getInstance();
        
    }

    @Override
    public void initialize() {
        this.itemMode = roller.getItemMode();
        if (itemMode) {
            CommandScheduler.getInstance().schedule(new ConeInject());
        } else {
            CommandScheduler.getInstance().schedule(new IntakeOut().withTimeout(2));
        }
    }

}
