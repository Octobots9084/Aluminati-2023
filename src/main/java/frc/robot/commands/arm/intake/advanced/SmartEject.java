package frc.robot.commands.arm.intake.advanced;

import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.InstantCommand;
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
            CommandScheduler.getInstance().schedule(new ConeInjectHigh());
        } else {
            CommandScheduler.getInstance().schedule(new CubeInjectMid());
        }
    }

}
