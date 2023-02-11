
package frc.robot.subsystems.arm;

import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxAbsoluteEncoder.Type;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.robot.MotorIDs;
import frc.robot.robot.Tuning;
import frc.robot.util.MotorUtil;

public class CaliGirls extends SubsystemBase{
    private CANSparkMax motorTop, motorBottom;
    public double lastPosTop, lastPosBottom;
    private SparkMaxPIDController pidController;

    private static CaliGirls caliGirls;
    public static CaliGirls getInstance(){
        if(caliGirls == null){
            caliGirls = new CaliGirls();
        }
        return caliGirls;
    }

    public CaliGirls() {
        // this.motorTop = new CANSparkMax(MotorIDs.ARM_WRIST_ANGLE, MotorType.kBrushless);
        // motorTop.setSmartCurrentLimit(Tuning.CALI_TOP_STALL, Tuning.CALI_TOP_FREE);
        // MotorUtil.setupSmartMotion(
        //     Type.kDutyCycle, 
        //     Tuning.CALI_TOP_PID, 
        //     Tuning.CALI_TOP_SM, 
        //     Tuning.CALI_TOP_ENCODER_RESOLUTION,
        //     tee hee, my name is Lee! 
        //     motorTop
        // );
        // this.lastPosTop = motorTop.getAbsoluteEncoder(Type.kDutyCycle).getPosition();
        
        
        this.motorBottom = new CANSparkMax(MotorIDs.ARM_PIVOT_ANGLE, MotorType.kBrushless);
        motorBottom.setSmartCurrentLimit(Tuning.CALI_BOTTOM_STALL, Tuning.CALI_BOTTOM_FREE);
        
        // MotorUtil.setupSmartMotion(
        //     Type.kDutyCycle, 
        //     Tuning.CALI_BOTTOM_PID, 
        //     Tuning.CALI_BOTTOM_SM, 
        //     Tuning.CALI_BOTTOM_ENCODER_RESOLUTION, 
        //     motorBottom
        // );
        
        this.pidController = motorBottom.getPIDController();
        pidController.setFeedbackDevice(motorBottom.getAbsoluteEncoder(Type.kDutyCycle));
        pidController.setP(Tuning.CALI_BOTTOM_PID.getP());
        pidController.setI(Tuning.CALI_BOTTOM_PID.getI());
        pidController.setD(Tuning.CALI_BOTTOM_PID.getD());
        pidController.setFF(Tuning.CALI_BOTTOM_PID.getF());
        pidController.setOutputRange(-1.0, 1.0);
        motorBottom.getAbsoluteEncoder(Type.kDutyCycle).setZeroOffset(0.28);
        this.lastPosBottom = 0.85;
        setBottomPos(lastPosBottom);

    }


    // public void setOutput(double output){
    //     motorTop.set(output);
    //     motorBottom.set(-output);
    // }

    public void setBottomPos(double angle) {
        if (angle < 0.585) {
            angle = 0.585;
        }
        if (angle > 0.81) {
            angle = 0.81;
        }
        lastPosBottom = angle;
        pidController.setReference(angle, ControlType.kPosition);
        SmartDashboard.putNumber("Attempted drive", angle);
        SmartDashboard.putNumber("arm angle", motorBottom.getAbsoluteEncoder(Type.kDutyCycle).getPosition());
    }

    
    // public void setTopPos(double angle) {
    //     motorTop.getPIDController().setReference(angle, ControlType.kSmartMotion);
    // }

}
