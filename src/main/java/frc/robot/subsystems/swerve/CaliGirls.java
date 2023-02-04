
package frc.robot.subsystems.swerve;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxAbsoluteEncoder.Type;

public class CaliGirls {
    private CANSparkMax motorTop;
    private CANSparkMax motorBottom;

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
