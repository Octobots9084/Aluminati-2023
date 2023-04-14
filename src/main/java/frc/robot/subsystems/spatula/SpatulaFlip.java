package frc.robot.subsystems.spatula;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANSparkMaxLowLevel.PeriodicFrame;
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
        pidControllerSpatula.setIZone(.07);
        pidControllerSpatula.setOutputRange(-1.0, 1.0);

        this.spatulaMotor.setPeriodicFramePeriod(PeriodicFrame.kStatus0, 255);
        this.spatulaMotor.setPeriodicFramePeriod(PeriodicFrame.kStatus1, 255);
        this.spatulaMotor.setPeriodicFramePeriod(PeriodicFrame.kStatus2, 255);
        this.spatulaMotor.setPeriodicFramePeriod(PeriodicFrame.kStatus3, 255);
        this.spatulaMotor.setPeriodicFramePeriod(PeriodicFrame.kStatus4, 255);
        this.spatulaMotor.setPeriodicFramePeriod(PeriodicFrame.kStatus5, 255);
        this.spatulaMotor.setPeriodicFramePeriod(PeriodicFrame.kStatus6, 255);
        this.spatulaMotor.setCANTimeout(1000);
        //Spatula Angle Position
        this.lastPosSpatula = 0.15;
        spatulaMotor.setInverted(true);
        this.spatulaMotor.getPIDController().setPositionPIDWrappingEnabled(false);
        // pidControllerSpatula.setSmartMotionAllowedClosedLoopError(0.1, 0);
        setSpatulaPos(0.45);

    }

    

    public void setSpatulaPos(double angle) {
        if (angle > 0.48) {
            angle = 0.48;
        } else if (angle < 0.05) {
            angle = 0.05;
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
