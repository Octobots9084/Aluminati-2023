
package frc.robot.subsystems.arm;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANSparkMaxLowLevel.PeriodicFrame;
import com.revrobotics.SparkMaxPIDController;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.robot.MotorIDs;
import frc.robot.robot.Tuning;
import frc.robot.util.MotorUtil;

public class ArmExtension extends SubsystemBase {
    private CANSparkMax motor;
    private static ArmExtension armExtension;
    public SparkMaxPIDController pidController;
    //gear reduction 1:25
    private double gearing = 15.0 / 15.0;
    public double lastpos = 0;

    public static ArmExtension getInstance() {
        if (armExtension == null) {
            armExtension = new ArmExtension();
        }
        return armExtension;
    }

    public ArmExtension() {
        this.motor = new CANSparkMax(MotorIDs.INTAKE_EXTENSION, MotorType.kBrushless);
        this.motor.setSmartCurrentLimit(Tuning.EXTENSION_STALL, Tuning.EXTENSION_FREE);
        this.pidController = motor.getPIDController();
        pidController.setFeedbackDevice(motor.getEncoder());
        pidController.setP(Tuning.EXTENSION_PID.getP());
        pidController.setI(Tuning.EXTENSION_PID.getI());
        pidController.setD(Tuning.EXTENSION_PID.getD());
        pidController.setOutputRange(Tuning.EXTENSION_MIN_OUT, Tuning.EXTENSION_MAX_OUT);
        MotorUtil.setupSmartMotion(null, Tuning.EXTENSION_PID, Tuning.EXTENSION_SM, 1, motor);
        pidController.setPositionPIDWrappingEnabled(false);
        motor.setInverted(true);
        this.motor.setPeriodicFramePeriod(PeriodicFrame.kStatus0, 255);
        this.motor.setPeriodicFramePeriod(PeriodicFrame.kStatus1, 255);
        this.motor.setPeriodicFramePeriod(PeriodicFrame.kStatus2, 255);
        this.motor.setPeriodicFramePeriod(PeriodicFrame.kStatus3, 255);
        this.motor.setPeriodicFramePeriod(PeriodicFrame.kStatus4, 255);
        this.motor.setPeriodicFramePeriod(PeriodicFrame.kStatus5, 255);
        this.motor.setPeriodicFramePeriod(PeriodicFrame.kStatus6, 255);
    }

    public void setOffset() {
        motor.getEncoder().setPosition(0);
        setPosition(0, false);
    }

    public void setPosition(double position, boolean override) {
        if (position > 75 && !override) {
            position = 75;
        }
        if (position < 0 && !override) {
            position = 0;
        }

        lastpos = position;
        motor.getPIDController().setReference(gearing * position, ControlType.kPosition);
    }

    public double getPosition() {
        return motor.getEncoder().getPosition() / gearing;
    }

    public void zeroArm() {
        motor.setSmartCurrentLimit(Tuning.EXTENSION_STALL_ZERO, Tuning.EXTENSION_FREE_ZERO);
        motor.setInverted(true);
        motor.setVoltage(-4);
        setPosition(-3500, true);
        // SmartDashboard.putNumber("Motor Velocity", motor.getEncoder().getVelocity());
    }

    public boolean zeroDone() {
        return motor.getOutputCurrent() > 3;
    }

    public void resetCurrent() {
        this.motor.setSmartCurrentLimit(Tuning.EXTENSION_STALL, Tuning.EXTENSION_FREE);
        pidController.setOutputRange(Tuning.EXTENSION_MIN_OUT, Tuning.EXTENSION_MAX_OUT);
    }
}
