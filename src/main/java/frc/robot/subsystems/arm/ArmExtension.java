
package frc.robot.subsystems.arm;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANSparkMaxLowLevel.PeriodicFrame;
import com.revrobotics.SparkMaxAbsoluteEncoder;
import com.revrobotics.SparkMaxPIDController;

import edu.wpi.first.math.controller.ElevatorFeedforward;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.robot.Logging;
import frc.robot.robot.MotorIDs;
import frc.robot.robot.Tuning;
import frc.robot.util.MotorUtil;

public class ArmExtension extends SubsystemBase {
    private CANSparkMax motor;
    private static ArmExtension armExtension;
    public SparkMaxPIDController pidController;
    private ElevatorFeedforward feedforward = new ElevatorFeedforward(0.25, 0.01, 0);
    //gear reduction 1:25
    private double gearing = 5.0 / 15.0;
    public double lastpos = 0;

    private boolean isEnabled = CaliGirls.isEnabled;

    public static ArmExtension getInstance() {
        if (armExtension == null) {
            armExtension = new ArmExtension();
        }
        return armExtension;
    }

    public ArmExtension() {
        if(isEnabled) {
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
            this.motor.setCANTimeout(1000);
            this.motor.getPIDController().setFF(-3);
            setOffset();
        }
    }

    public void setOffset() {
        if(isEnabled){
            motor.getEncoder().setPosition(0);
            setPosition(0, false);
        }
    }

    public void setPosition(double position, boolean override) {
        if(isEnabled){
            if (position > 75 && !override) {
                position = 75;
            }
            if (position < 0 && !override) {
                position = 0;
            }

            lastpos = position;
            motor.getPIDController().setReference(gearing * position, ControlType.kPosition);
        }
    }

    public double getPosition() {
        if(!isEnabled) return 0;
        return motor.getEncoder().getPosition() / gearing;
    }

    public void zeroArm() {
        if(isEnabled){
            motor.setSmartCurrentLimit(Tuning.EXTENSION_STALL_ZERO, Tuning.EXTENSION_FREE_ZERO);
            motor.setInverted(true);
            motor.setVoltage(-4);
            setPosition(-3500, true);
            // SmartDashboard.putNumber("Motor Velocity", motor.getEncoder().getVelocity());
        }
    }

    @Override
    public void periodic(){
        setMotorKf();
        Logging.armDashboard.setEntry("Extension Kf", getMotorKf());
    }

    public boolean zeroDone() {
        if(!isEnabled) return false;
        return motor.getOutputCurrent() > 3;
    }

    public void resetCurrent() {
        if(isEnabled){
            this.motor.setSmartCurrentLimit(Tuning.EXTENSION_STALL, Tuning.EXTENSION_FREE);
            pidController.setOutputRange(Tuning.EXTENSION_MIN_OUT, Tuning.EXTENSION_MAX_OUT);
        }
    }

    public double getMotorPos(){
        if (!isEnabled) return 0;
        return motor.getAbsoluteEncoder(SparkMaxAbsoluteEncoder.Type.kDutyCycle).getPosition();
    }

    public double getMotorVel(){
        if(!isEnabled) return 0;
        return motor.getAbsoluteEncoder(SparkMaxAbsoluteEncoder.Type.kDutyCycle).getVelocity();
    }

    public void setMotorKf(){
        if(isEnabled) pidController.setFF(feedforward.calculate(getMotorPos(), getMotorVel()));
    }

    public double getMotorKf(){
        if(!isEnabled) return 0;
        return pidController.getFF();
    }
}
