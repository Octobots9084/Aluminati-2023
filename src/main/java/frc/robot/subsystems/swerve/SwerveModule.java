/*
 * This file is part of Placeholder-2023, licensed under the GNU General Public License (GPLv3).
 *
 * Copyright (c) Octobots <https://github.com/Octobots9084>
 * Copyright (c) contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package frc.robot.subsystems.swerve;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatorCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.SparkMaxAbsoluteEncoder.Type;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.util.*;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class SwerveModule {
    // Physical Constants
    private static final double WHEEL_RADIUS = 0.03915;
    private static final double ENCODER_RESOLUTION = 1;
    private static final double GEARING = 11.0 / 40.0;
    private static final double GEARING_TURN_MOTORS = 1.0 / 1.0;
    private static final double STEER_MOTOR_TICK_TO_ANGLE = 2.0 * Math.PI / ENCODER_RESOLUTION / GEARING_TURN_MOTORS; // radians
    private static final double DRIVE_MOTOR_TICK_TO_METERS = (GEARING * 2.0 * Math.PI * WHEEL_RADIUS)/2048.0;
    private static final double DRIVE_MOTOR_TICK_TO_SPEED = 10 * GEARING * (2 * Math.PI * WHEEL_RADIUS) / 2048; // m/s
    // Controller Constants
    private static final double MAX_TURN_ACCELERATION = 20000; // Rad/s
    private static final double MAX_TURN_VELOCITY = 20000; // Rad/s
    private static final double MIN_TURN_VELOCITY = 18000; // Rad/s
    private static final double ALLOWED_CLOSED_LOOP_ERROR = 0.1;
    private static final int TIMEOUT_MS = 60;

    // Turn Motor Smart Motion
    private static final SmartMotionConfig TM_SM_CONFIG = new SmartMotionConfig(
        true,
        MAX_TURN_VELOCITY, MIN_TURN_VELOCITY, MAX_TURN_ACCELERATION, ALLOWED_CLOSED_LOOP_ERROR
    );
    // P was 30
    //private static final PIDConfig TM_SM_PID = new PIDConfig(10, 0.000, 0, 0);

    // Drive Motor Motion Magic
    private static final MotionMagicConfig DM_MM_CONFIG = new MotionMagicConfig(
        new ArrayList<>(), true,
        10000.0, 10000.0,
        300, 2,
        TIMEOUT_MS, 10
    );
    //private static final PIDConfig DM_MM_PID = new PIDConfig(0.035, 0.0001, 0);

    private static PIDConfig DM_MM_PID;
    private static PIDConfig TM_SM_PID;
    // Motors
    private final WPI_TalonFX driveMotor;
    private final CANSparkMax steeringMotor;

    // Thread-Safe angles to reduce CAN usage
    private final AtomicReference<Double> swerveAngle = new AtomicReference<>(0.0);
    private final AtomicReference<Double> swerveSpeed = new AtomicReference<>(0.0);

    /**
     * Constructs a SwerveModule.
     *
     * @param driveMotorChannel    ID for the drive motor.
     * @param steeringMotorChannel ID for the turning motor.
     */
    public SwerveModule(int driveMotorChannel, int steeringMotorChannel, boolean steerMotorInverted, PIDConfig turnPidConfig, PIDConfig drivePidConfig) {//, double zeroTicks) {

        TM_SM_PID = turnPidConfig;
        DM_MM_PID = drivePidConfig;
        // Steer Motor
        this.steeringMotor = new CANSparkMax(steeringMotorChannel, CANSparkMaxLowLevel.MotorType.kBrushless);
        TM_SM_PID.setTolerance(0);
        this.steeringMotor.restoreFactoryDefaults();
        MotorUtil.setupSmartMotion(Type.kDutyCycle, TM_SM_PID, TM_SM_CONFIG ,ENCODER_RESOLUTION, steeringMotor);
        this.steeringMotor.getAbsoluteEncoder(Type.kDutyCycle).setInverted(false);

        // Initialize position of steering motor encoder to the same as the rio encoder
        // this.steeringMotor.getAbsoluteEncoder(Type.kDutyCycle).setZeroOffset(zeroTicks);

        // Drive Motor
        this.driveMotor = new WPI_TalonFX(driveMotorChannel);
        MotorUtil.setupMotionMagic(FeedbackDevice.IntegratedSensor, DM_MM_PID, DM_MM_CONFIG, driveMotor);
        driveMotor.configAllowableClosedloopError(0, 5);
        driveMotor.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
        driveMotor.setStatusFramePeriod(21, 10);
        driveMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 10);
        driveMotor.setNeutralMode(NeutralMode.Brake);
        StatusFrameDemolisher.demolishStatusFrames(driveMotor, false);

        // Current Limits
        this.driveMotor.configStatorCurrentLimit(new StatorCurrentLimitConfiguration(true, 60, 60, 0.05)); //How much current the motor can use (outputwise)
        this.driveMotor.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, 63, 63, 0.05)); //How much current can be supplied to the motor

        this.steeringMotor.setSmartCurrentLimit(31, 30);

        try {
            Thread.sleep(200);
        } catch (Exception e) {
            // Ignore all sleep exceptions
        }
    }

    public double getAbsoluteAngle() {
        return SwerveUtil.clampAngle(getAngle());
    }

    public double getAngle() {
        return swerveAngle.get();
    }

    public SwerveModulePosition getModulePosition() {
        return new SwerveModulePosition(DRIVE_MOTOR_TICK_TO_METERS * this.getDriveTicks(), new Rotation2d(this.getAbsoluteAngle()*-1));
    }

    public double convertAngleToTick(double angleInRads) {
        return (angleInRads / STEER_MOTOR_TICK_TO_ANGLE);
    }

    public double convertVelocityToTicksPer100ms(double velocity) {
        return velocity / DRIVE_MOTOR_TICK_TO_SPEED;
    }

    public double getPosTicks() {
        return steeringMotor.getAbsoluteEncoder(Type.kDutyCycle).getPosition();
    }

    public double getDriveTicks() {
        return driveMotor.getSensorCollection().getIntegratedSensorPosition();
    }

    public double getVelocity() {
        return swerveSpeed.get();
    }

    public SwerveModuleState getState() {
        return new SwerveModuleState(getVelocity(), new Rotation2d(SwerveUtil.clampAngle(getAngle())));
    }

    public void setDriveMotorVelocity(double metersPerSecond) {
        driveMotor.set(TalonFXControlMode.Velocity, convertVelocityToTicksPer100ms(metersPerSecond));
    }

    public void setSteeringMotorAngle(double angleInRad) {
        
        steeringMotor.getPIDController().setReference(angleInRad, CANSparkMax.ControlType.kPosition);
    }

    public void updateSwerveInformation() {
        // Converts position to radians
        swerveAngle.set((steeringMotor.getAbsoluteEncoder(Type.kDutyCycle).getPosition()) * STEER_MOTOR_TICK_TO_ANGLE);
        // Converts speed to speed per 100ms
        swerveSpeed.set(driveMotor.getSensorCollection().getIntegratedSensorVelocity() * DRIVE_MOTOR_TICK_TO_SPEED);
    }

    /**
     * Sets the desired state for the module.
     *
     * @param state Desired state with speed and angle.
     */
    public void setDesiredState(SwerveModuleState state) {
        // Optimize the swerve state and set it
        var optimizedAngle = SwerveUtil.optimizeSwerveStates(state, getAngle());
        SmartDashboard.putNumber("Angle", convertAngleToTick(optimizedAngle.angle.getRadians()));
        setDriveMotorVelocity(optimizedAngle.speedMetersPerSecond);
        setSteeringMotorAngle(convertAngleToTick(optimizedAngle.angle.getRadians()));
    }
}
