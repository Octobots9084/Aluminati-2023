package org.octobots.robot.util;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.SPI;

import java.util.concurrent.atomic.AtomicReference;
public class Gyro {
    private static Gyro INSTANCE;
    public static Gyro getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Gyro();
        }
        return INSTANCE;
    }

    private final AHRS navX;
    private final AtomicReference<Rotation2d> atomicReference;

    private Gyro() {
        this.navX = new AHRS(SPI.Port.kMXP);
        this.atomicReference = new AtomicReference<>(new Rotation2d(0));
    }

    private double getAngle() {
        return navX.getAngle();
    }

    public Rotation2d getRotation2d() {
        return new Rotation2d(-1 * atomicReference.get().getRadians());
    }

    public void updateRotation2D() {
        atomicReference.set(new Rotation2d(Math.toRadians(-getAngle())));
    }

    public double getRate() {
        return Math.toRadians(-navX.getRate());
    }

    public void setAngleAdjustment(double angle) {
        navX.reset();
        navX.setAngleAdjustment(angle);
    }

    public void resetGyro() {
        navX.reset();
    }
}