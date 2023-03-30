package frc.robot.commands.swerve;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.swerve.DriveTrain;
import frc.robot.util.Gyro;
import frc.robot.util.MathUtil;


/**
 * Represents a swerve drive style drivetrain.
*/
public class SetDriveAngle extends InstantCommand {
    private final DriveTrain dt;
    private final double angle;
    public SetDriveAngle(double angle) {
        this.dt = DriveTrain.getInstance();
        this.angle = angle;
    }

    @Override
    public void initialize() {
        
        double gyroUnwrappedAngle = Gyro.getInstance().getUnwrappedAngle();
        double diff = MathUtil.wrapToCircle(angle) - MathUtil.wrapToCircle(gyroUnwrappedAngle);

        if (Math.abs(diff)>180) {
            diff = -Math.signum(diff)*360 + diff;
        }

        dt.setTargetRotationAngle(gyroUnwrappedAngle+diff);
    }

    //dt.setTargetRotationAngle((Gyro.getInstance().getUnwrappedAngle()-(Gyro.getInstance().getUnwrappedAngle()%360))+angle);

}