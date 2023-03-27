
package frc.robot.subsystems.arm;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANSparkMaxLowLevel.PeriodicFrame;
import com.revrobotics.SparkMaxAbsoluteEncoder.Type;
import com.revrobotics.SparkMaxPIDController;

import edu.wpi.first.math.controller.ArmFeedforward;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.robot.Logging;
import frc.robot.robot.MotorIDs;
import frc.robot.robot.Tuning;
import frc.robot.util.PIDConfig;

public class CaliGirls extends SubsystemBase {
    private CANSparkMax motorTop, motorBottom, motorBottomFollower;
    public double lastPosTop, lastPosBottom;
    private SparkMaxPIDController pidControllerTop;
    private SparkMaxPIDController pidControllerBottom;


    //these values are likely wrong right now, just testing. also, kA may be unnecessary
    private ArmFeedforward feedforward = new ArmFeedforward(0.01, 0.2, 0);
    private static CaliGirls caliGirls;

    public static CaliGirls getInstance() {
        if (caliGirls == null) {
            caliGirls = new CaliGirls();
        }
        return caliGirls;
    }

    public CaliGirls() {
        //Up Motor
        this.motorTop = new CANSparkMax(MotorIDs.ARM_WRIST_ANGLE, MotorType.kBrushless);
        motorTop.setSmartCurrentLimit(Tuning.CALI_TOP_STALL, Tuning.CALI_TOP_FREE);

        //Bottom Motor
        this.motorBottom = new CANSparkMax(MotorIDs.ARM_PIVOT_ANGLE, MotorType.kBrushless);
        motorBottom.setSmartCurrentLimit(Tuning.CALI_BOTTOM_STALL, Tuning.CALI_BOTTOM_FREE);

        // Top Motor PID
        this.pidControllerTop = motorTop.getPIDController();
        pidControllerTop.setFeedbackDevice(motorTop.getAbsoluteEncoder(Type.kDutyCycle));
        pidControllerTop.setP(Tuning.CALI_TOP_PID.getP());
        pidControllerTop.setI(Tuning.CALI_TOP_PID.getI());
        pidControllerTop.setD(Tuning.CALI_TOP_PID.getD());
        pidControllerTop.setFF(Tuning.CALI_TOP_PID.getF());
        pidControllerTop.setOutputRange(-1.0, 1.0);
        this.lastPosTop = 0.5;
        this.motorTop.getAbsoluteEncoder(Type.kDutyCycle).setInverted(false);
        this.motorTop.setInverted(false);
        this.motorTop.getPIDController().setPositionPIDWrappingEnabled(false);
        this.motorTop.getAbsoluteEncoder(Type.kDutyCycle).setZeroOffset(0.163);
        pidControllerTop.setSmartMotionAllowedClosedLoopError(0.01, 0);
        //setTopPos(lastPosTop);

        // Bottom Motor PID
        this.pidControllerBottom = motorBottom.getPIDController();
        pidControllerBottom.setFeedbackDevice(motorBottom.getAbsoluteEncoder(Type.kDutyCycle));
        pidControllerBottom.setP(Tuning.CALI_BOTTOM_PID.getP());
        pidControllerBottom.setI(Tuning.CALI_BOTTOM_PID.getI());
        pidControllerBottom.setD(Tuning.CALI_BOTTOM_PID.getD());
        pidControllerBottom.setFF(Tuning.CALI_BOTTOM_PID.getF());
        // pidControllerBottom.setIZone(0.2);
        pidControllerBottom.setOutputRange(-1.0, 1.0);
        motorBottom.getAbsoluteEncoder(Type.kDutyCycle).setZeroOffset(0.28);

        pidControllerBottom.setPositionPIDWrappingEnabled(false);
        motorBottom.getAbsoluteEncoder(Type.kDutyCycle).setInverted(true);
        motorBottom.setInverted(false);

        this.lastPosBottom = 0.722;
        //setBottomPos(lastPosBottom);

        this.motorBottomFollower = new CANSparkMax(MotorIDs.ARM_PIVOT_ANGLE_FOLLOWER, MotorType.kBrushless);
        this.motorBottomFollower.setSmartCurrentLimit(Tuning.CALI_TOP_FOLLOWER_STALL, Tuning.CALI_TOP_FOLLOWER_FREE);
        this.motorBottomFollower.follow(motorBottom, true);


        this.motorBottom.setPeriodicFramePeriod(PeriodicFrame.kStatus0, 255);
        this.motorBottom.setPeriodicFramePeriod(PeriodicFrame.kStatus1, 255);
        this.motorBottom.setPeriodicFramePeriod(PeriodicFrame.kStatus2, 255);
        this.motorBottom.setPeriodicFramePeriod(PeriodicFrame.kStatus3, 255);
        this.motorBottom.setPeriodicFramePeriod(PeriodicFrame.kStatus4, 255);
        this.motorBottom.setPeriodicFramePeriod(PeriodicFrame.kStatus5, 255);
        this.motorBottom.setPeriodicFramePeriod(PeriodicFrame.kStatus6, 255);

        this.motorTop.setPeriodicFramePeriod(PeriodicFrame.kStatus0, 255);
        this.motorTop.setPeriodicFramePeriod(PeriodicFrame.kStatus1, 255);
        this.motorTop.setPeriodicFramePeriod(PeriodicFrame.kStatus2, 255);
        this.motorTop.setPeriodicFramePeriod(PeriodicFrame.kStatus3, 255);
        this.motorTop.setPeriodicFramePeriod(PeriodicFrame.kStatus4, 255);
        this.motorTop.setPeriodicFramePeriod(PeriodicFrame.kStatus5, 255);
        this.motorTop.setPeriodicFramePeriod(PeriodicFrame.kStatus6, 255);

        this.motorBottomFollower.setPeriodicFramePeriod(PeriodicFrame.kStatus0, 255);
        this.motorBottomFollower.setPeriodicFramePeriod(PeriodicFrame.kStatus1, 255);
        this.motorBottomFollower.setPeriodicFramePeriod(PeriodicFrame.kStatus2, 255);
        this.motorBottomFollower.setPeriodicFramePeriod(PeriodicFrame.kStatus3, 255);
        this.motorBottomFollower.setPeriodicFramePeriod(PeriodicFrame.kStatus4, 255);
        this.motorBottomFollower.setPeriodicFramePeriod(PeriodicFrame.kStatus5, 255);
        this.motorBottomFollower.setPeriodicFramePeriod(PeriodicFrame.kStatus6, 255);
    }



    public void setTopPos(double angle) {
        if (angle > 0.67) {
            angle = 0.67;
        } else if (angle < 0.252) {
            angle = 0.252;
        }
        lastPosTop = angle;
        pidControllerTop.setReference(angle, ControlType.kPosition);
        // SmartDashboard.putNumber("Wrist Set Angle", angle);
        Logging.armDashboard.setEntry("Wrist Set Angle", angle);
    }

    public void setBottomPos(double angle) {
        double lower = 0;//getLowerBounding();
        if (angle > 0.84) {
            angle = 0.84;
        } else if (angle < lower) {
            angle = lower;
        }

        lastPosBottom = angle;
        pidControllerBottom.setReference(angle, ControlType.kPosition);
        // SmartDashboard.putNumber("Arm Set Angle", angle);
        Logging.armDashboard.setEntry("Arm Set Angle", angle);
    }

    public double getLowerBounding() {
        return ((ArmExtension.getInstance().lastpos / 650) + 0.5);
    }

    @Override
    public void periodic() {
        // pidControllerBottom();
        setBottomKf();

        Logging.armDashboard.setEntry("bottom_kf", getBottomKf());

        //System.out.println("bottom kf value: " + getBottomKf());
    }

    public double getBottomKf() {
        return pidControllerBottom.getFF();
    }

    public void setBottomKf() {
        pidControllerBottom.setFF(feedforward.calculate((getBottomPos() - 0.5)*Math.PI*2, getBottomVel()*Math.PI*2));
        //pidControllerBottom.setFF(0.1);
    }

    public double getTopPos() {
        return motorTop.getAbsoluteEncoder(Type.kDutyCycle).getPosition();
    }

    public double getBottomPos() {
        return motorBottom.getAbsoluteEncoder(Type.kDutyCycle).getPosition();
    }

    public double getBottomVel(){
        return motorBottom.getAbsoluteEncoder(Type.kDutyCycle).getVelocity();
    }

}