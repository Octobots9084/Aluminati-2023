package frc.robot.subsystems.arm;

import com.revrobotics.CANSparkMax;
import frc.robot.robot.MotorIDs;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.CANSparkMaxLowLevel.PeriodicFrame;
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
        this.motor.setSmartCurrentLimit(20, 35);
        this.motor.setPeriodicFramePeriod(PeriodicFrame.kStatus0, 255);
        this.motor.setPeriodicFramePeriod(PeriodicFrame.kStatus1, 255);
        this.motor.setPeriodicFramePeriod(PeriodicFrame.kStatus2, 255);
        this.motor.setPeriodicFramePeriod(PeriodicFrame.kStatus3, 255);
        this.motor.setPeriodicFramePeriod(PeriodicFrame.kStatus4, 255);
        this.motor.setPeriodicFramePeriod(PeriodicFrame.kStatus5, 255);
        this.motor.setPeriodicFramePeriod(PeriodicFrame.kStatus6, 255);
        this.motor.setCANTimeout(1000);
        
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
