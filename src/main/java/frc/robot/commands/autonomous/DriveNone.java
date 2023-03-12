package frc.robot.commands.autonomous;


import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.swerve.DriveTrain;
import frc.robot.util.MathUtil;

public class DriveNone extends CommandBase{


    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        DriveTrain.getInstance().drive(0,0, DriveTrain.getInstance().getRotationSpeed(), false);
    }

    @Override
    public boolean isFinished() {
        return true;
    }

}
