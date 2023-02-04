
package frc.robot.subsystems.arm;

import com.ctre.phoenix.sensors.Pigeon2;
import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxAbsoluteEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxAbsoluteEncoder.Type;

import frc.robot.util.PIDConfig;

public class ArmExtension {
    private CANSparkMax motor;
    private static ArmExtension armExtension;
    private RelativeEncoder encoder;
    public SparkMaxPIDController pidController;
    private PIDConfig pidConfig;
    private double gearing = 1.0;

    public static ArmExtension getInstance(){
        if(armExtension == null){
            armExtension = new ArmExtension();
        }
        return armExtension;
    }
    public ArmExtension(){
        this.motor = new CANSparkMax(0, MotorType.kBrushless);
        this.encoder = motor.getEncoder();
        this.pidController = motor.getPIDController();
        this.pidConfig = new PIDConfig(2, 0, 0);
        encoder.setPosition(0);
        pidController.setFeedbackDevice(encoder);
        pidController.setP(pidConfig.getP());
        pidController.setI(pidConfig.getI());
        pidController.setD(pidConfig.getD());
        pidController.setOutputRange(-1, 1);
    }

    public void SetPosition(double position){
        pidController.setReference(position, ControlType.kPosition);
    }
}
