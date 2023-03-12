package frc.robot.commands.swerve;

import edu.wpi.first.wpilibj2.command.InstantCommand;

public class ToggleMetachromia extends InstantCommand {
    @Override
    public void initialize() {
        if (SwerveControl.MetachromasiaEnabled) {
            SwerveControl.MetachromasiaEnabled = false;
        } else {
            SwerveControl.MetachromasiaEnabled = true;
        }
    }
}
