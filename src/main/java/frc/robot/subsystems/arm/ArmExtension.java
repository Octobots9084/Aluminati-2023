
package frc.robot.subsystems.arm;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.robot.MotorIDs;
import frc.robot.util.PIDConfig;

public class ArmExtension extends SubsystemBase{
    private CANSparkMax motor;
    private static ArmExtension armExtension;
    private RelativeEncoder encoder;
    public SparkMaxPIDController pidController;
    private PIDConfig pidConfig;
    //gear reduction 1:25
    private double gearing = 1.0/25.0;
    public double lastpos;
    public double offset = 0;

    public static ArmExtension getInstance(){
        if(armExtension == null){
            armExtension = new ArmExtension();
        }
        return armExtension;
    }
    public ArmExtension(){
        this.motor = new CANSparkMax(MotorIDs.INTAKE_EXTENSION, MotorType.kBrushless);
        this.encoder = motor.getEncoder();
        this.pidController = motor.getPIDController();
        this.motor.setSmartCurrentLimit(10, 10);
        this.pidConfig = new PIDConfig(2, 0, 0);
        encoder.setPosition(0);
        pidController.setFeedbackDevice(encoder);
        pidController.setP(pidConfig.getP());
        pidController.setI(pidConfig.getI());
        pidController.setD(pidConfig.getD());
        pidController.setOutputRange(-1, 1);
    }
    public void setOffset() {
        this.offset = motor.getEncoder().getPosition();
    }

    public void SetPosition(double position){
        lastpos = position;
        pidController.setReference(gearing * -(position + offset), ControlType.kPosition);
    }
}
