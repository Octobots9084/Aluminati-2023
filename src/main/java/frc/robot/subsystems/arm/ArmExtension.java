
package frc.robot.subsystems.arm;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxAbsoluteEncoder.Type;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.robot.MotorIDs;
import frc.robot.robot.Tuning;
import frc.robot.util.MotorUtil;
import frc.robot.util.PIDConfig;

public class ArmExtension extends SubsystemBase{
    private CANSparkMax motor;
    private static ArmExtension armExtension;
    public SparkMaxPIDController pidController;
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
        this.motor.setSmartCurrentLimit(Tuning.EXTENSION_STALL, Tuning.EXTENSION_FREE);
        
        pidController.setFeedbackDevice(motor.getAbsoluteEncoder(Type.kDutyCycle));
        pidController.setP(Tuning.EXTENSION_PID.getP());
        pidController.setI(Tuning.EXTENSION_PID.getI());
        pidController.setD(Tuning.EXTENSION_PID.getD());
        pidController.setOutputRange(Tuning.EXTENSION_MIN_OUT, Tuning.EXTENSION_MAX_OUT);
    }
    public void setOffset() {
        this.offset = motor.getEncoder().getPosition();
    }

    public void SetPosition(double position){
        lastpos = position;
        motor.getPIDController().setReference(gearing * -(position + offset), ControlType.kPosition);
    }
}
