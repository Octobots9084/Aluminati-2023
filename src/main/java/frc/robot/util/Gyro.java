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

package frc.robot.util;

import java.util.concurrent.atomic.AtomicReference;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.SPI;

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
        return MathUtil.wrapToCircle(navX.getAngle(), 360);
    }

    public Rotation2d getRotation2d() {
        return new Rotation2d(-atomicReference.get().getRadians());
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

    public double getRoll() {
        // SmartDashboard.putNumber("Gryo pitch", navX.getRoll());
        // Logging.driveDashboard.setEntry("Gyro Pitch", navX.getRoll());
        return navX.getRoll();
    }

    public void resetGyro() {
        navX.reset();
    }
}