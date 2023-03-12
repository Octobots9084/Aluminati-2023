package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.swerve.DriveTrain;
import frc.robot.util.MathUtil;

public class DriveLeft extends CommandBase{
    double speed = 0;
    DriveLeft(double speed) {
        this.speed = speed;
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        DriveTrain.getInstance().drive(0,speed * DriveTrain.MAX_TURN_SPEED, DriveTrain.getInstance().getRotationSpeed(), false);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

}
