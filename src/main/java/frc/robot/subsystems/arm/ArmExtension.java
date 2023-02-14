
package frc.robot.subsystems.arm;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxPIDController;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.robot.MotorIDs;
import frc.robot.robot.Tuning;

public class ArmExtension extends SubsystemBase {
    private CANSparkMax motor;
    private static ArmExtension armExtension;
    public SparkMaxPIDController pidController;
    //gear reduction 1:25
    private double gearing = 1.0 / 25.0;
    public double lastpos;

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
        
    }

    public void setOffset() {
        motor.getEncoder().setPosition(0);
        SetPosition(0, false);
    }

    public void SetPosition(double position, boolean override) {
        if (position > 3500) {
            position = 3500;
        }
        if (position < 0 && !override) {
            position = 0;
        }

        lastpos = position;
        motor.getPIDController().setReference(gearing * -position, ControlType.kSmartMotion);
    }

    public double GetPosition() {
        return motor.getEncoder().getPosition();
    }

    public void zeroArm() {
        motor.setSmartCurrentLimit(Tuning.EXTENSION_STALL_ZERO, Tuning.EXTENSION_FREE_ZERO);
        motor.setVoltage(3);
        SetPosition(-3500, true);
    }

    public boolean zeroDone() {
        return motor.getOutputCurrent() > 1 && motor.getEncoder().getVelocity() < 20;
    }
}
