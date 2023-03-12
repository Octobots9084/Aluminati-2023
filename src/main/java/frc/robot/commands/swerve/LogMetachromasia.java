package frc.robot.commands.swerve;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.commands.swerve.SwerveControl;
public class LogMetachromasia extends InstantCommand{
    @Override
    public void initialize() {
        


        Double[] xSpeedsDouble = new Double[SwerveControl.xSpeeds.size()];
        xSpeedsDouble = SwerveControl.xSpeeds.toArray(xSpeedsDouble);

        Double[] ySpeedsDouble = new Double[SwerveControl.ySpeeds.size()];
        ySpeedsDouble = SwerveControl.xSpeeds.toArray(ySpeedsDouble);

        Double[] rotsDouble = new Double[SwerveControl.rots.size()];
        rotsDouble = SwerveControl.rots.toArray(rotsDouble);

        SmartDashboard.putNumber("test", 0);
        SmartDashboard.putString("xSpeeds", xSpeedsDouble.toString());
        SmartDashboard.putString("ySpeeds", ySpeedsDouble.toString());
        SmartDashboard.putString("rot", rotsDouble.toString());
    }
}
