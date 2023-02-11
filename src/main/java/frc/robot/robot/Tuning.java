package frc.robot.robot;

import com.ctre.phoenix.motorcontrol.StatorCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;

import frc.robot.util.PIDConfig;
import frc.robot.util.SmartMotionConfig;

public class Tuning {
    //Currents
    //Wrist
    public static final int CALI_TOP_FREE = 10;
    public static final int CALI_TOP_STALL = 12;
    //Pivot
    public static final int CALI_BOTTOM_FREE = 20;
    public static final int CALI_BOTTOM_STALL = 22;
    //Extension
    public static final int EXTENSION_FREE = 10;
    public static final int EXTENSION_STALL = 12;
    

    //Movement Characteristics
    //Wrist
    public static final PIDConfig CALI_TOP_PID = new PIDConfig(0.1,0,0,0);
    public static final SmartMotionConfig CALI_TOP_SM = new SmartMotionConfig(true, 
        10000.0,
        10000.0,
        10000.0,
        0.1
    );
    public static final double CALI_TOP_ENCODER_RESOLUTION = 1.0;
    
    //Pivot
    public static final PIDConfig CALI_BOTTOM_PID = new PIDConfig(8,0,0,0);
    public static final SmartMotionConfig CALI_BOTTOM_SM = new SmartMotionConfig(true, 
        10000.0,
        9000.0,
        10000.0,
        0.1
    );
    public static final double CALI_BOTTOM_ENCODER_RESOLUTION = 1.0;    

    //Extension
    public static final PIDConfig EXTENSION_PID = new PIDConfig(2, 0, 0);
    public static final double EXTENSION_MAX_OUT = 1;
    public static final double EXTENSION_MIN_OUT = -1;
}
