
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
        motorTop.getAbsoluteEncoder(Type.kDutyCycle).setZeroOffset(0.54);
        this.lastPosTop = 0.510;
        pidControllerTop.setSmartMotionAllowedClosedLoopError(0,0);
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
        this.lastPosBottom = 0.85;
        //setBottomPos(lastPosBottom);

    }

    public void setTopPos(double angle) {
        if (angle < 0.02) {
            angle = 0.02;
        }
        if (angle > 0.55) {
            angle = 0.55;
        }
        lastPosTop = angle;
        pidControllerTop.setReference(angle, ControlType.kPosition);
        SmartDashboard.putNumber("Wrist Attempted Drive", angle);
        SmartDashboard.putNumber("Wrist Angle", motorTop.getAbsoluteEncoder(Type.kDutyCycle).getPosition());
    }

    public void setBottomPos(double angle) {
        if (angle < 0.585) {
            angle = 0.585;
        }
        if (angle > 0.81) {
            angle = 0.81;
        }
        lastPosBottom = angle;
        pidControllerBottom.setReference(angle, ControlType.kPosition);
        SmartDashboard.putNumber("Arm Attempted Drive", angle);
        SmartDashboard.putNumber("Arm Angle", motorBottom.getAbsoluteEncoder(Type.kDutyCycle).getPosition());
    }

    // public void setTopPos(double angle) {
    //     motorTop.getPIDController().setReference(angle, ControlType.kSmartMotion);
    // }

}
