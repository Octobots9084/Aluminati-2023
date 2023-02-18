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

package frc.robot.robot;

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

    //Arm
    public static final int INTAKE_LEFT_CLAW = 13;
    public static final int INTAKE_RIGHT_CLAW = 14;
    public static final int ARM_ROLLER = 11;
    public static final int INTAKE_EXTENSION = 10;
    public static final int ARM_WRIST_ANGLE = 12;
    public static final int ARM_PIVOT_ANGLE = 9;

    private MotorIDs() {
    }
}
