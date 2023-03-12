package frc.robot.commands.swerve;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.commands.swerve.SwerveControl;
public class logRubberDucky extends InstantCommand{
    @Override
    public void initialize() {
        


        Double[] xSpeedsDouble = new Double[SwerveControl.xSpeeds.size()];
        xSpeedsDouble = SwerveControl.xSpeeds.toArray(xSpeedsDouble);

        Double[] ySpeedsDouble = new Double[SwerveControl.ySpeeds.size()];
        ySpeedsDouble = SwerveControl.xSpeeds.toArray(ySpeedsDouble);

        Double[] rotsDouble = new Double[SwerveControl.rots.size()];
        rotsDouble = SwerveControl.rots.toArray(rotsDouble);


        SmartDashboard.putNumberArray("xSpeeds", xSpeedsDouble);
        SmartDashboard.putNumberArray("ySpeeds", ySpeedsDouble);
        SmartDashboard.putNumberArray("rot", rotsDouble);
    }
}
