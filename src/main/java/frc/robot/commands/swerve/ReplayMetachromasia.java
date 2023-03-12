package frc.robot.commands.swerve;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.swerve.DriveTrain;

public class ReplayMetachromasia extends CommandBase{
    double[] xSpeeds;
    double[] ySpeeds;
    double[] rots;
    int i;
    ReplayMetachromasia(double[] xSpeeds, double[] ySpeeds, double[] rots) {
        this.xSpeeds = xSpeeds;
        this.ySpeeds = ySpeeds;
        this.rots = rots;
        this.i = 0;
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        DriveTrain.getInstance().drive(xSpeeds[i],ySpeeds[i], rots[i], true);
        i++;
    }

    @Override
    public boolean isFinished() {
        return i<xSpeeds.length;
    }
}
