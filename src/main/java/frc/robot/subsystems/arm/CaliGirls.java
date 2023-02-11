
package frc.robot.subsystems.arm;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxAbsoluteEncoder.Type;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.robot.MotorIDs;
import frc.robot.robot.Tuning;
import frc.robot.util.MotorUtil;

public class CaliGirls extends SubsystemBase{
    private CANSparkMax motorTop, motorBottom;
    public double lastPosTop, lastPosBottom;

    private static CaliGirls caliGirls;
    public static CaliGirls getInstance(){
        if(caliGirls == null){
            caliGirls = new CaliGirls();
        }
        return caliGirls;
    }

    public CaliGirls() {
        this.motorTop = new CANSparkMax(MotorIDs.ARM_WRIST_ANGLE, MotorType.kBrushless);
        motorTop.setSmartCurrentLimit(Tuning.CALI_TOP_STALL, Tuning.CALI_TOP_FREE);
        MotorUtil.setupSmartMotion(
            Type.kDutyCycle, 
            Tuning.CALI_TOP_PID, 
            Tuning.CALI_TOP_SM, 
            Tuning.CALI_TOP_ENCODER_RESOLUTION, 
            motorTop
        );
        
        this.motorBottom = new CANSparkMax(MotorIDs.ARM_PIVOT_ANGLE, MotorType.kBrushless);
        motorTop.setSmartCurrentLimit(Tuning.CALI_BOTTOM_STALL, Tuning.CALI_BOTTOM_FREE);
        MotorUtil.setupSmartMotion(
            Type.kDutyCycle, 
            Tuning.CALI_BOTTOM_PID, 
            Tuning.CALI_BOTTOM_SM, 
            Tuning.CALI_BOTTOM_ENCODER_RESOLUTION, 
            motorBottom
        );
        
        
        
    }


    public void setOutput(double output){
        motorTop.set(output);
        motorBottom.set(-output);
    }

    public void setBottomPos(double angle) {
        motorBottom.getPIDController().setReference(angle, ControlType.kSmartMotion);
    }

    
    public void setTopPos(double angle) {
        motorTop.getPIDController().setReference(angle, ControlType.kSmartMotion);
    }

}
