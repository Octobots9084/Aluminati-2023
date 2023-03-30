package frc.robot.commands.swerve;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.swerve.DriveTrain;
import frc.robot.util.Gyro;
import frc.robot.util.MathUtil;


/**
 * Represents a swerve drive style drivetrain.
*/
public class SetDriveAngle extends CommandBase {
    private final DriveTrain dt;
    private final double angle;
    private double startTime;
    private double currentTime;

    public SetDriveAngle(double angle) {
        this.dt = DriveTrain.getInstance();
        this.angle = angle;
        
    }


    @Override
    public void initialize() {
        this.startTime = Timer.getFPGATimestamp();
    }
    @Override
    public void execute() {
        this.currentTime = Timer.getFPGATimestamp();
        double gyroUnwrappedAngle = Gyro.getInstance().getUnwrappedAngle();
        double diff = MathUtil.wrapToCircle(angle) - MathUtil.wrapToCircle(gyroUnwrappedAngle);

        if (Math.abs(diff)>180) {
            diff = -Math.signum(diff)*360 + diff;
        }

        dt.setTargetRotationAngle(gyroUnwrappedAngle+diff);
    }

    @Override
    public boolean isFinished() {
        if (Math.abs(dt.getRotationSpeed())<0.1 && !MathUtil.isWithinTolerance(startTime, currentTime, 0.1)) {
            return true;
        }

        if (!MathUtil.isWithinTolerance(startTime, currentTime, 0.5)) {
            return true;
        }
        return false;
    }

    //dt.setTargetRotationAngle((Gyro.getInstance().getUnwrappedAngle()-(Gyro.getInstance().getUnwrappedAngle()%360))+angle);

}