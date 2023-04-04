package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.swerve.DriveTrain;

public class DriveForwards extends CommandBase
{
    double speed = 0;
    DriveForwards(double speed) {
        this.speed = speed;
        addRequirements(DriveTrain.getInstance());
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        DriveTrain.getInstance().drive(speed * DriveTrain.MAX_TURN_SPEED,0, 0, false);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

}
