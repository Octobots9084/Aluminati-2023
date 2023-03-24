package frc.robot.commands.swerve;
import java.util.Arrays;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.robot.Logging;
public class LogMetachromasia extends InstantCommand{
    @Override
    public void initialize() {
        Logging.driveDashboard.setEntry("xSpeeds", SwerveControl.xSpeeder.toString());
        Logging.driveDashboard.setEntry("ySpeeds", SwerveControl.ySpeeder.toString());
        Logging.driveDashboard.setEntry("rot", SwerveControl.rotser.toString());
    }
}