
package frc.robot.subsystems.arm;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxAbsoluteEncoder.Type;
import com.revrobotics.SparkMaxPIDController;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.robot.MotorIDs;
import frc.robot.robot.Tuning;
import frc.robot.util.MotorUtil;

public class CaliGirls extends SubsystemBase {
    private CANSparkMax motorTop, motorBottom;
    public double lastPosTop, lastPosBottom;
    private SparkMaxPIDController pidControllerTop;
    private SparkMaxPIDController pidControllerBottom;

    private static CaliGirls caliGirls;

    public static CaliGirls getInstance() {
        if (caliGirls == null) {
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

        //Up Motor
        this.motorTop = new CANSparkMax(MotorIDs.ARM_WRIST_ANGLE, MotorType.kBrushless);
        motorTop.setSmartCurrentLimit(Tuning.CALI_TOP_STALL, Tuning.CALI_TOP_FREE);

        //Bottom Motor
        this.motorBottom = new CANSparkMax(MotorIDs.ARM_PIVOT_ANGLE, MotorType.kBrushless);
        motorBottom.setSmartCurrentLimit(Tuning.CALI_BOTTOM_STALL, Tuning.CALI_BOTTOM_FREE);

        // MotorUtil.setupSmartMotion(
        //     Type.kDutyCycle, 
        //     Tuning.CALI_BOTTOM_PID, 
        //     Tuning.CALI_BOTTOM_SM, 
        //     Tuning.CALI_BOTTOM_ENCODER_RESOLUTION, 
        //     motorBottom
        // );

        // Top Motor PID
        this.pidControllerTop = motorTop.getPIDController();
        pidControllerTop.setFeedbackDevice(motorTop.getAbsoluteEncoder(Type.kDutyCycle));
        pidControllerTop.setP(Tuning.CALI_TOP_PID.getP());
        pidControllerTop.setI(Tuning.CALI_TOP_PID.getI());
        pidControllerTop.setD(Tuning.CALI_TOP_PID.getD());
        pidControllerTop.setFF(Tuning.CALI_TOP_PID.getF());
        pidControllerTop.setOutputRange(-1.0, 1.0);
        this.lastPosTop = 0.510;
        this.motorTop.getAbsoluteEncoder(Type.kDutyCycle).setInverted(false);
        this.motorTop.setInverted(true);
        this.motorTop.getPIDController().setPositionPIDWrappingEnabled(false);
        this.motorTop.getAbsoluteEncoder(Type.kDutyCycle).setZeroOffset(0.3);
        pidControllerTop.setSmartMotionAllowedClosedLoopError(0.1,0);
        this.motorTop.setSmartCurrentLimit(5,5);
        //setTopPos(lastPosTop);

        // Bottom Motor PID
        this.pidControllerBottom = motorBottom.getPIDController();
        pidControllerBottom.setFeedbackDevice(motorBottom.getAbsoluteEncoder(Type.kDutyCycle));
        pidControllerBottom.setP(Tuning.CALI_BOTTOM_PID.getP());
        pidControllerBottom.setI(Tuning.CALI_BOTTOM_PID.getI());
        pidControllerBottom.setD(Tuning.CALI_BOTTOM_PID.getD());
        pidControllerBottom.setFF(Tuning.CALI_BOTTOM_PID.getF());
        pidControllerBottom.setOutputRange(-1.0, 1.0);
        motorBottom.getAbsoluteEncoder(Type.kDutyCycle).setZeroOffset(0.28);
        
        MotorUtil.setupSmartMotion(Type.kDutyCycle, Tuning.CALI_BOTTOM_PID, Tuning.CALI_BOTTOM_SM, 1, motorBottom);
        pidControllerBottom.setPositionPIDWrappingEnabled(false);
        motorBottom.getAbsoluteEncoder(Type.kDutyCycle).setInverted(true);
        motorBottom.setInverted(false);
    
        this.lastPosBottom = 0.85;
        //setBottomPos(lastPosBottom);

    }

    public void setTopPos(double angle) {
        lastPosTop = angle;
        pidControllerTop.setReference(angle, ControlType.kPosition);
        SmartDashboard.putNumber("Wrist Set Angle", angle);
    }

    public void setBottomPos(double angle) {
  
        lastPosBottom = angle;
        pidControllerBottom.setReference(angle, ControlType.kSmartMotion);
        SmartDashboard.putNumber("Arm Set Angle", angle);
    }

    // public void setTopPos(double angle) {
    //     motorTop.getPIDController().setReference(angle, ControlType.kSmartMotion);
    // }

    public double getTopPos() {
        return motorTop.getAbsoluteEncoder(Type.kDutyCycle).getPosition();
    }

    public double getBottomPos() {
        return motorBottom.getAbsoluteEncoder(Type.kDutyCycle).getPosition();
    }

}
