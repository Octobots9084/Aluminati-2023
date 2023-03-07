package frc.robot.robot;

import java.util.ArrayList;

import com.ctre.phoenix.motorcontrol.StatorCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;

import frc.robot.util.MotionMagicConfig;
import frc.robot.util.PIDConfig;
import frc.robot.util.SmartMotionConfig;

public class Tuning {
    //Currents
    //Drive
    public static final StatorCurrentLimitConfiguration DRIVE_STATOR_LIMIT = new StatorCurrentLimitConfiguration(
            true,
            60,
            60,
            0.05);

    public static final SupplyCurrentLimitConfiguration DRIVE_SUPPLY_LIMIT = new SupplyCurrentLimitConfiguration(
            true,
            63,
            63,
            0.05);

    //Wrist
    public static final int CALI_TOP_FREE = 35;
    public static final int CALI_TOP_STALL = 35;
    //Pivot
    public static final int CALI_BOTTOM_FREE = 30;
    public static final int CALI_BOTTOM_STALL = 30;
    public static final int CALI_TOP_FOLLOWER_STALL = 30;
    public static final int CALI_TOP_FOLLOWER_FREE = 30;
    //Extension
    public static final int EXTENSION_FREE = 20;
    public static final int EXTENSION_STALL = 20;

    //Extension Zeroing
    public static final int EXTENSION_FREE_ZERO = 5;
    public static final int EXTENSION_STALL_ZERO = 5;

    //Movement Characteristics
    //Wrist
    public static final PIDConfig CALI_TOP_PID = new PIDConfig(2, 0.000, 1, 0.02);
    public static final SmartMotionConfig CALI_TOP_SM = new SmartMotionConfig(true,
            10000.0,
            0.0,
            10000.0,
            0.0);
    public static final double CALI_TOP_ENCODER_RESOLUTION = 1.0;

    //Arm Pivot
    //     public static final PIDConfig CALI_BOTTOM_PID = new PIDConfig(13, 0.0002, 2.5, 0.0003);
    //     public static final SmartMotionConfig CALI_BOTTOM_SM = new SmartMotionConfig(true,
    //             10000.0,
    //             0.0,
    //             10000.0,
    //             0.0);

    //     public static final PIDConfig CALI_BOTTOM_PID = new PIDConfig(3, 0.0005, 0.2, 0.03);
    public static final PIDConfig CALI_BOTTOM_PID = new PIDConfig(4, 0.00, 0, 0.05);
    public static final SmartMotionConfig CALI_BOTTOM_SM = new SmartMotionConfig(true,
            1.0,
            0.0,
            1.0,
            0.0);
    public static final double CALI_BOTTOM_ENCODER_RESOLUTION = 1.0;

    //Extension
    public static final PIDConfig EXTENSION_PID = new PIDConfig(0.1, 0, 0);
    public static final SmartMotionConfig EXTENSION_SM = new SmartMotionConfig(true,
            0.12,
            0.0,
            0.4,
            0.0);
    public static final double EXTENSION_MAX_OUT = 1;
    public static final double EXTENSION_MIN_OUT = -1;

    //Drive Motors
    public static final PIDConfig FL_DRIVE_PID = new PIDConfig(0.06, 0.0001, 0, 0.06);
    public static final PIDConfig FR_DRIVE_PID = new PIDConfig(0.07, 0.0001, 0, 0.06);
    public static final PIDConfig BL_DRIVE_PID = new PIDConfig(0.06, 0.0001, 0, 0.06);
    public static final PIDConfig BR_DRIVE_PID = new PIDConfig(0.05, 0.0001, 0, 0.06);
    public static final MotionMagicConfig DM_MM_CONFIG = new MotionMagicConfig(
            new ArrayList<>(), true,
            10000.0, 10000.0,
            300, 2,
            60, 10);

    //Turn
    public static final int TURN_MOTOR_FREE = 30;
    public static final int TURN_MOTOR_STALL = 31;

    //Turn Motors
    public static final PIDConfig FL_TURN_PID = new PIDConfig(10, 0, 0);
    public static final PIDConfig FR_TURN_PID = new PIDConfig(10, 0, 0);
    public static final PIDConfig BL_TURN_PID = new PIDConfig(10, 0, 0);
    public static final PIDConfig BR_TURN_PID = new PIDConfig(10, 0, 0);
    public static final SmartMotionConfig TM_SM_CONFIG = new SmartMotionConfig(
            true,
            20000.0,
            18000.0,
            20000.0,
            0.1);

    //Claw
    public static final double defaultConeCollectingVoltage = -0.15;
    public static final double defaultCubeCollectingVoltage = -0.2;

    //Command Characteristics
    //Autos
}
