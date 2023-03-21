package frc.robot.commands.swerve;
import java.util.Arrays;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.InstantCommand;
public class LogMetachromasia extends InstantCommand{
    @Override
    public void initialize() {
        SmartDashboard.putNumber("test", 0);
        SmartDashboard.putString("xSpeeds", SwerveControl.xSpeeds.toString());
        SmartDashboard.putString("ySpeeds", SwerveControl.ySpeeds.toString());
        SmartDashboard.putString("rot", SwerveControl.rots.toString());
    }
}