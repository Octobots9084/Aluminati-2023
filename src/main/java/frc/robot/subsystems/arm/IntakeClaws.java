package frc.robot.subsystems.arm;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.robot.MotorIDs;
// can sparkmax brushless
public class IntakeClaws extends SubsystemBase{
    private static IntakeClaws INSTANCE;
    public static IntakeClaws getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new IntakeClaws();
        }
        return INSTANCE;
    }
    private final CANSparkMax leftMoter;
    private final CANSparkMax rightMoter;
    public IntakeClaws(){
        this.leftMoter=new CANSparkMax(MotorIDs.INTAKE_LEFT_CLAW, CANSparkMaxLowLevel.MotorType.kBrushless);
        this.rightMoter=new CANSparkMax(MotorIDs.INTAKE_RIGHT_CLAW, CANSparkMaxLowLevel.MotorType.kBrushless);
        leftMoter.setInverted(true);
    }
    public void setClawVoltage(double v){
        leftMoter.setVoltage(v);
        rightMoter.setVoltage(v);
    }
}
