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
    //true is cone, false is cube
    private boolean itemMode;

    public Roller() {
        this.motor = new CANSparkMax(MotorIDs.ARM_ROLLER, CANSparkMaxLowLevel.MotorType.kBrushless);
        this.motor.setSmartCurrentLimit(30, 30);
    }

    public void SetItemMode(boolean itemMode) {
        this.itemMode = itemMode;
    }

    public boolean getItemMode() {
        return this.itemMode;
    }

    public void setRollerVoltage(double v) {
        motor.setVoltage(v);

    }
}
