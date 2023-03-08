package frc.robot.commands.swerve;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.swerve.DriveTrain;


/**
 * Represents a swerve drive style drivetrain.
*/
public class SetDriveAngle extends InstantCommand {
    private final DriveTrain dt;
    private final double angle;
    public SetDriveAngle(double angle) {
        this.dt = DriveTrain.getInstance();
        this.angle = angle;
    }

    @Override
    public void initialize() {
        dt.setTargetRotationAngle(angle);
    }

}