package frc.robot.subsystems.arm;

import com.revrobotics.CANSparkMax;
import frc.robot.robot.MotorIDs;
import com.revrobotics.CANSparkMaxLowLevel;
public class Roller {
    private static Roller INSTANCE;

    public static Roller getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Roller();
        }
        return INSTANCE;
    }

    private final CANSparkMax motor;

    public Roller() {
        this.motor = new CANSparkMax(MotorIDs.ARM_ROLLER, CANSparkMaxLowLevel.MotorType.kBrushless);
        this.motor.setSmartCurrentLimit(1, 1);
        setRollerVoltage(0);
    }

    public void setRollerVoltage(double v) {
        motor.setVoltage(v);

    }
}