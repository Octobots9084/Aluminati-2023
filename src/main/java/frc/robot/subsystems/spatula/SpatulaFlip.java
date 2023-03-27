package frc.robot.subsystems.spatula;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxPIDController;
import frc.robot.robot.MotorIDs;
import frc.robot.robot.Tuning;
import com.revrobotics.SparkMaxAbsoluteEncoder.Type;
import frc.robot.robot.Logging;

public class SpatulaFlip extends SubsystemBase {
    public CANSparkMax spatulaMotor;
    public double lastPosSpatula;
    private SparkMaxPIDController pidControllerSpatula;
    private static SpatulaFlip spatulaFlip;

    public static SpatulaFlip getInstance() {
        if (spatulaFlip == null) {
            spatulaFlip = new SpatulaFlip();
        }
        return spatulaFlip;
    }

    public SpatulaFlip() {
        //Spatula Angle Motor
        this.spatulaMotor = new CANSparkMax(MotorIDs.SPATULA_ANGLE, MotorType.kBrushless);
        spatulaMotor.setSmartCurrentLimit(Tuning.SPATULA_ANGLE_TUNE);

        //Spatula Angle PID
        this.pidControllerSpatula = spatulaMotor.getPIDController();
        pidControllerSpatula.setFeedbackDevice(spatulaMotor.getAbsoluteEncoder(Type.kDutyCycle));
        pidControllerSpatula.setP(Tuning.SPATULA_PID.getP());
        pidControllerSpatula.setI(Tuning.SPATULA_PID.getI());
        pidControllerSpatula.setD(Tuning.SPATULA_PID.getD());
        pidControllerSpatula.setFF(Tuning.SPATULA_PID.getF());
        pidControllerSpatula.setOutputRange(-1.0, 1.0);

        //Spatula Angle Position
        this.lastPosSpatula = 0.15;
        this.spatulaMotor.getPIDController().setPositionPIDWrappingEnabled(false);
        // pidControllerSpatula.setSmartMotionAllowedClosedLoopError(0.1, 0);

    }

    

    public void setSpatulaPos(double angle) {
        if (angle > 0.25) {
            angle = 0.25;
        } else if (angle < 0.02) {
            angle = 0.02;
        }
        lastPosSpatula = angle;
        pidControllerSpatula.setReference(angle, ControlType.kPosition);
        // SmartDashboard.putNumber("Spatula Set Angle", angle);
        Logging.armDashboard.setEntry("Spatula Set Angle", angle);
    }

    public double getSpatulaPos() {
        return spatulaMotor.getAbsoluteEncoder(Type.kDutyCycle).getPosition();
    }
}
