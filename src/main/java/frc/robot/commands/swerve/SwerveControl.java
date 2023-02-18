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

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.robot.ControlMap;
import frc.robot.subsystems.swerve.DriveTrain;
import frc.robot.util.Gyro;
import frc.robot.util.MathUtil;

public class SwerveControl extends CommandBase {
    private final DriveTrain driveTrain;
    private final Gyro gyro;

    public SwerveControl() {
        // Initialization
        this.driveTrain = DriveTrain.getInstance();
        this.gyro = Gyro.getInstance();
        addRequirements(this.driveTrain);
    }

    @Override
    public void initialize() {
        driveTrain.setTargetRotationAngle(gyro.getRotation2d().getDegrees());
    }

    @Override
    public void execute() {
        // Link joysticks
        var leftJoystick = ControlMap.DRIVER_LEFT;
        var rightJoystick = ControlMap.DRIVER_RIGHT;
        // var xbox = ControlMap.XBOX;
        // var heliStick = ControlMap.HelicopterStick;
        var xSpeed = 0.0;
        var ySpeed = 0.0;
        var rot = 0.0;

        // if(sendable.getSelected() != null && sendable.getSelected().equals(2))
        // {
        //     // Get speeds from joysticks
        //     xSpeed = MathUtil.fitDeadband(-xbox.getLeftY()) * DriveTrain.MAX_TURN_SPEED;
        //     ySpeed = MathUtil.fitDeadband(xbox.getLeftX()) * DriveTrain.MAX_TURN_SPEED;

        //     // Calculate the deadband
        //     rot = MathUtil.fitDeadband(xbox.getRightY()) * DriveTrain.MAX_ANGULAR_SPEED;
        // } else if(sendable.getSelected() != null && sendable.getSelected().equals(3))
        // {
        //     // Get speeds from joysticks
        //     xSpeed = MathUtil.fitDeadband(-heliStick.getY()) * DriveTrain.MAX_TURN_SPEED;
        //     ySpeed = MathUtil.fitDeadband(heliStick.getX()) * DriveTrain.MAX_TURN_SPEED;

        //     // Calculate the deadband
        //     rot = MathUtil.fitDeadband(heliStick.getZ()) * DriveTrain.MAX_ANGULAR_SPEED;
        // } else if(sendable.getSelected() != null && sendable.getSelected().equals(1))
        // {
        // Get speeds from joysticks
        xSpeed = MathUtil.fitDeadband(-leftJoystick.getY()) * DriveTrain.MAX_TURN_SPEED;
        ySpeed = MathUtil.fitDeadband(leftJoystick.getX()) * DriveTrain.MAX_TURN_SPEED;

        // Calculate the deadband
        rot = MathUtil.fitDeadband(-rightJoystick.getX()) * DriveTrain.MAX_ANGULAR_SPEED;
        // }
        // else{
        //     xSpeed = 0.0;
        //     ySpeed = 0.0;
        //     rot = 0.0;
        // }

        // Check driver assist and drive
        if (rot == 0 && driveTrain.useDriverAssist()) {
            driveTrain.drive(xSpeed, ySpeed, driveTrain.getRotationSpeed(), driveTrain.getFieldCentric());
        } else {
            SmartDashboard.putNumber("rotation", rot);
            driveTrain.drive(xSpeed, ySpeed, rot, driveTrain.getFieldCentric());
            Gyro.getInstance().updateRotation2D();
            driveTrain.setTargetRotationAngle(gyro.getRotation2d().getDegrees());
        }
    }
}
