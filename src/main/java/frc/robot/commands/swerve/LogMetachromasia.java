package frc.robot.commands.swerve;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.InstantCommand;
public class LogMetachromasia extends InstantCommand{
    @Override
    public void initialize() {
        Double[] xSpeedsDouble = new Double[SwerveControl.xSpeeds.size()];
        Double[] ySpeedsDouble = new Double[SwerveControl.ySpeeds.size()];
        Double[] rotsDouble = new Double[SwerveControl.rots.size()];
        
        xSpeedsDouble = SwerveControl.xSpeeds.toArray(xSpeedsDouble);

        
        ySpeedsDouble = SwerveControl.xSpeeds.toArray(ySpeedsDouble);

        
        rotsDouble = SwerveControl.rots.toArray(rotsDouble);

        SmartDashboard.putNumber("test", 0);
        SmartDashboard.putString("xSpeeds", xSpeedsDouble.toString());
        SmartDashboard.putString("ySpeeds", ySpeedsDouble.toString());
        SmartDashboard.putString("rot", rotsDouble.toString());
    }
}