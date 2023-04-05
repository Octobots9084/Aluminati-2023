package frc.robot.commands.arm.basic;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.arm.Roller;

public class SetItemMode extends InstantCommand{
   public SetItemMode(boolean itemMode) {
        Roller.getInstance().SetItemMode(itemMode);
   } 
}
