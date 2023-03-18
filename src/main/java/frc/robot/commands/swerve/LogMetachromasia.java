package frc.robot.commands.swerve;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.commands.swerve.SwerveControl;

import java.util.ArrayList;
import java.util.Collections;

public class LogMetachromasia extends InstantCommand{
    @Override
    public void initialize() {
        Double[] xSpeedsDouble = new Double[SwerveControl.xSpeeds.size()];
        xSpeedsDouble = SwerveControl.xSpeeds.toArray(xSpeedsDouble);

        Double[] ySpeedsDouble = new Double[SwerveControl.ySpeeds.size()];
        ySpeedsDouble = SwerveControl.xSpeeds.toArray(ySpeedsDouble);

        Double[] rotsDouble = new Double[SwerveControl.rots.size()];
        rotsDouble = SwerveControl.rots.toArray(rotsDouble);

        StringBuilder xString = new StringBuilder();
        StringBuilder yString = new StringBuilder();
        StringBuilder rotsString = new StringBuilder();

        for (Double d : xSpeedsDouble) {
            xString.append(' ').append(d.toString());
        }
        for (Double d : ySpeedsDouble) {
            yString.append(' ').append(d.toString());
        }
        for (Double d : rotsDouble) {
            rotsString.append(' ').append(d.toString());
        }


        SmartDashboard.putNumber("test", 0);
        SmartDashboard.putString("xSpeeds", xString.toString());
        SmartDashboard.putString("ySpeeds", yString.toString());
        SmartDashboard.putString("rot", rotsString.toString());
    }
}
