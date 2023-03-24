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

package frc.robot.commands.swerve;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.robot.ControlMap;
import frc.robot.subsystems.swerve.DriveTrain;
import frc.robot.util.Gyro;
import frc.robot.util.MathUtil;

public class SwerveControl extends CommandBase {
    private final DriveTrain driveTrain;
    private final Gyro gyro;
    public static ArrayList<Double> xSpeeder;
    public static ArrayList<Double> ySpeeder;
    public static ArrayList<Double> rotser;
    public static double[] xSpeeds;
    public static double[] ySpeeds;
    public static double[] rots;
    public static boolean MetachromasiaEnabled = false;
    public static double xSpeed ;
    public static double ySpeed;
    public static double rot;

    public static Joystick leftJoystick = ControlMap.DRIVER_LEFT;
    public static Joystick rightJoystick = ControlMap.DRIVER_RIGHT;


    public SwerveControl() {
        // Initialization
        this.driveTrain = DriveTrain.getInstance();
        this.gyro = Gyro.getInstance();
        xSpeeder = new ArrayList<Double>();
        ySpeeder = new ArrayList<Double>();
        rotser = new ArrayList<Double>();
        addRequirements(this.driveTrain);
        xSpeed = 0.0;
        ySpeed = 0.0;
        rot = 0.0;
    }

    @Override
    public void initialize() {
        driveTrain.setTargetRotationAngle(gyro.getUnwrappedAngle());
    }

    @Override
    public void execute() {
        // Link joysticks
        


        // Get speeds from joysticks
        xSpeed = MathUtil.fitDeadband(-leftJoystick.getY()) * DriveTrain.MAX_TURN_SPEED;
        ySpeed = MathUtil.fitDeadband(leftJoystick.getX()) * DriveTrain.MAX_TURN_SPEED;

        // Calculate the deadband
        rot = MathUtil.fitDeadband(rightJoystick.getX()) * DriveTrain.MAX_ANGULAR_SPEED;

        if (MetachromasiaEnabled) {
            xSpeeder.add(xSpeed);
            ySpeeder.add(ySpeed);
            rotser.add(rot);
        }
        
        xSpeeds = new double[xSpeeder.size()];
        xSpeeds = xSpeeder.stream().mapToDouble(v -> v.doubleValue()).toArray();
        ySpeeds = new double[ySpeeder.size()];
        ySpeeds = ySpeeder.stream().mapToDouble(v -> v.doubleValue()).toArray();
        rots = new double[rotser.size()];
        rots = rotser.stream().mapToDouble(v -> v.doubleValue()).toArray();


        //just drive lol
        driveTrain.drive(xSpeed, ySpeed, rot, driveTrain.getFieldCentric());

        // Check driver assist and drive
        // if (rot == 0 && driveTrain.useDriverAssist()) {
        // driveTrain.drive(xSpeed, ySpeed, -driveTrain.getRotationSpeed(), driveTrain.getFieldCentric());
        // } else {
        //     // SmartDashboard.putNumber("rotation", rot);
        //     Logging.driveDashboard.setEntry("rotation", rot);

        //     driveTrain.drive(xSpeed, ySpeed, rot, driveTrain.getFieldCentric());
        //     Gyro.getInstance().updateRotation2D();
        //     driveTrain.setTargetRotationAngle(gyro.getRotation2d().getDegrees());
        // }
    }
}
