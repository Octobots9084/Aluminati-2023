package frc.robot.commands.swerve;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.swerve.DriveTrain;

public class SetDriveAngle extends InstantCommand{
    
    private double angle;
    public SetDriveAngle(double angle) {
        this.angle = angle;
    }
    
    @Override
    public void initialize() {
        DriveTrain.getInstance().setTargetRotationAngle(angle);
    }
}
