package frc.robot.commands.swerve;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
    private double currentAngle;
    private double angleDiff;
    private double rotationSpeed;
    public SetDriveAngle(double angle) {
        this.dt = DriveTrain.getInstance();
        this.angle = angle;
        this.currentAngle = Gyro.getInstance().getRotation2d().getDegrees();
        this.angleDiff = angle-currentAngle;
        addRequirements(this.dt);
    }

    @Override
    public void initialize() {
        dt.setTargetRotationAngle(angle);
    }

    @Override
    public boolean isFinished() {
        return MathUtil.isWithinTolerance(currentAngle,angle,5);
    }

    @Override
    public void execute() {
        currentAngle = Gyro.getInstance().getRotation2d().getDegrees();
        angleDiff = angle-currentAngle;
        
        rotationSpeed = 9 * angleDiff/180;
        if (rotationSpeed>dt.MAX_ANGULAR_SPEED) {
            rotationSpeed= dt.MAX_ANGULAR_SPEED;
        }
        dt.drive(0, 0, rotationSpeed, true);
    }

    @Override
    public void end(boolean interrupted) {
        dt.drive(0, 0, 0, false);
        dt.setTargetRotationAngle(Gyro.getInstance().getRotation2d().getDegrees());
    }

}