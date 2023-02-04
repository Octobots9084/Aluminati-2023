
package frc.robot.subsystems.arm;

import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkMaxAbsoluteEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxAbsoluteEncoder.Type;

public class CaliGirls {
    private CANSparkMax motorTop;
    private CANSparkMax motorBottom;
    public SparkMaxPIDController caliPIDControllerTop;
    private SparkMaxAbsoluteEncoder caliEncoderTop;
    public double kPTop, kITop, kDTop, kIzTop, kFFTop, kMaxOutputTop, kMinOutputTop;
    public SparkMaxPIDController caliPIDControllerBottom;
    private SparkMaxAbsoluteEncoder caliEncoderBottom;
    public double kPBottom, kIBottom, kDBottom, kIzBottom, kFFBottom, kMaxOutputBottom, kMinOutputBottom;

    private static CaliGirls caliGirls;
    public static CaliGirls getInstance(){
        if(caliGirls == null){
            caliGirls = new CaliGirls();
        }
        return caliGirls;
    }

    public CaliGirls() {
        this.motorTop = new CANSparkMax(0, MotorType.kBrushless);
        this.motorBottom = new CANSparkMax(0, MotorType.kBrushless);
        this.caliEncoderTop = motorTop.getAbsoluteEncoder(Type.kDutyCycle);
        this.caliEncoderBottom = motorBottom.getAbsoluteEncoder(Type.kDutyCycle);
        this.caliPIDControllerTop = motorTop.getPIDController();
        caliPIDControllerTop.setFeedbackDevice(caliEncoderTop);
        kPTop = 0.1;
        kITop = 1e-4;
        kDTop = 1;
        kIzTop = 0;
        kFFTop = 0;
        kMaxOutputTop = 1;
        kMinOutputTop = -1;
        caliPIDControllerTop.setP(kPTop);
        caliPIDControllerTop.setI(kITop);
        caliPIDControllerTop.setD(kDTop);
        caliPIDControllerTop.setIZone(kIzTop);
        caliPIDControllerTop.setFF(kFFTop);
        caliPIDControllerTop.setOutputRange(kMinOutputTop, kMaxOutputTop);
        this.caliPIDControllerBottom = motorBottom.getPIDController();
        caliPIDControllerBottom.setFeedbackDevice(caliEncoderBottom);
        kPBottom = 0.1;
        kIBottom = 1e-4;
        kDBottom = 1;
        kIzBottom = 0;
        kFFBottom = 0;
        kMaxOutputBottom = 1;
        kMinOutputBottom = -1;
        caliPIDControllerBottom.setP(kPBottom);
        caliPIDControllerBottom.setI(kIBottom);
        caliPIDControllerBottom.setD(kDBottom);
        caliPIDControllerBottom.setIZone(kIzBottom);
        caliPIDControllerBottom.setFF(kFFBottom);
        caliPIDControllerBottom.setOutputRange(kMinOutputBottom, kMaxOutputBottom);
    }


    public void setOutput(double output){
        motorTop.set(output);
        motorBottom.set(-output);
    }

    


    public double getRotation(){
        double rotTop = motorTop.getAbsoluteEncoder(Type.kDutyCycle).getPosition();
        double rotBottom = motorBottom.getAbsoluteEncoder(Type.kDutyCycle).getPosition();
        return (rotTop-rotBottom)/2;
    }

}
