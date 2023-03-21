package frc.robot.commands.swerve;
import java.util.Arrays;

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

        // StringBuilder xSpeedsString;
        // StringBuilder ySpeedsString;
        // StringBuilder rotsString;

        // for(Double d : xSpeedsDouble) {
        //     SmartDashboard.putNumber("xSpeedDoubleItem", d);
        // }
        // for(Double d : ySpeedsDouble) {
        //     ySpeedsString = ySpeedsString + "," + d.toString();
        // }
        // for(Double d : rotsDouble) {
        //     rotsString = rotsString + "," + d.toString();
        // }


        SmartDashboard.putNumber("test", 0);
        SmartDashboard.putString("xSpeeds", SwerveControl.xSpeeds.toString());
        SmartDashboard.putString("ySpeeds", SwerveControl.ySpeeds.toString());
        SmartDashboard.putString("rot", SwerveControl.rots.toString());
    }
}