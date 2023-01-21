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

package frc.robot;

public class MotorIDs {
    // Drive Train
    public static final int FRONT_RIGHT_DRIVE = 4;
    public static final int FRONT_RIGHT_STEER = 3;
    public static final int FRONT_LEFT_DRIVE = 2;
    public static final int FRONT_LEFT_STEER = 1;
    public static final int BACK_LEFT_DRIVE = 6;
    public static final int BACK_LEFT_STEER = 5;
    public static final int BACK_RIGHT_DRIVE = 8;
    public static final int BACK_RIGHT_STEER = 7;

    // Climb
    public static final int SOLENOID_LOW_OPEN = 4;
    public static final int SOLENOID_LOW_CLOSED = 7;
    public static final int SOLENOID_MID_OPEN = 2;
    public static final int SOLENOID_MID_CLOSED = 5;
    public static final int SOLENOID_HIGH_OPEN = 3;
    public static final int SOLENOID_HIGH_CLOSED = 6;
    public static final int CLIMB_ROTATE_A = 10;
    public static final int CLIMB_ROTATE_B = 11;
    public static final int LOW_SWITCH_A = 1;
    public static final int LOW_SWITCH_B = 2;
    public static final int MID_SWITCH_A = 3;
    public static final int MID_SWITCH_B = 4;
    public static final int HIGH_SWITCH_A = 5;
    public static final int HIGH_SWITCH_B = 6;
    public static final int CLIMB_ENCODER = 7;

    // Collect
    public static final int COLLECT_INTAKE = 13;
    public static final int COLLECT_BELTS = 12;
    public static final int COLLECT_WHEEL = 14;

    // Shooter
    public static final int SHOOTER_LEFT = 15;
    public static final int SHOOTER_ANGLE = 16;
    public static final int SHOOTER_RIGHT = 17;

    private MotorIDs() {
    }
}
