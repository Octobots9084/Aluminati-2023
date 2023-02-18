package frc.robot.subsystems.arm;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.robot.MotorIDs;

// can sparkmax brushless
public class IntakeClaws extends SubsystemBase {
    private static IntakeClaws INSTANCE;


    public static IntakeClaws getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new IntakeClaws();
        }
        return INSTANCE;
    }

    private final CANSparkMax moter;

    public IntakeClaws() {
        this.moter = new CANSparkMax(MotorIDs.INTAKE_LEFT_CLAW, CANSparkMaxLowLevel.MotorType.kBrushless);
    }

    public void setClawVoltage(double v) {
        moter.setVoltage(v);
    }
}
