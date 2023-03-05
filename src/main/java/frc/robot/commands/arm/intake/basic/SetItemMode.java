package frc.robot.commands.arm.intake.basic;

import java.util.Set;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.arm.Roller;

public class SetItemMode extends InstantCommand{
   public SetItemMode(boolean itemMode) {
        Roller.getInstance().SetItemMode(itemMode);
   } 
}
