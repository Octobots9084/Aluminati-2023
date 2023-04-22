package frc.robot.subsystems.spatula;

import com.revrobotics.CANSparkMax;
import frc.robot.robot.MotorIDs;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.CANSparkMaxLowLevel.PeriodicFrame;

public class RollingPins {
    private static RollingPins INSTANCE;

    public static RollingPins getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RollingPins();
        }
        return INSTANCE;
    }
    
    private final CANSparkMax motor;
    //idk how the rolling pins work, but this can be used to change motor direction
    private boolean rollingPinMode;

    public RollingPins() {
        this.motor = new CANSparkMax(MotorIDs.SPATULA_ROLLER, CANSparkMaxLowLevel.MotorType.kBrushless);
        this.motor.setSmartCurrentLimit(20, 20);
        this.motor.setPeriodicFramePeriod(PeriodicFrame.kStatus0, 500);
        this.motor.setPeriodicFramePeriod(PeriodicFrame.kStatus1, 500);
        this.motor.setPeriodicFramePeriod(PeriodicFrame.kStatus2, 500);
        this.motor.setPeriodicFramePeriod(PeriodicFrame.kStatus3, 500);
        this.motor.setPeriodicFramePeriod(PeriodicFrame.kStatus4, 500);
        this.motor.setPeriodicFramePeriod(PeriodicFrame.kStatus5, 500);
        this.motor.setPeriodicFramePeriod(PeriodicFrame.kStatus6, 500);
        this.motor.setCANTimeout(1000);
        
    }

    public void SetRollingPinMode(boolean rollingPinMode) {
        this.rollingPinMode = rollingPinMode;
    }

    public boolean getRollingPinMode() {
        return this.rollingPinMode;
    }

    public void setRollingPinVoltage(double v) {
        motor.setVoltage(v);

    }
}