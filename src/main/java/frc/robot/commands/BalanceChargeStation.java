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

 package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.swerve.DriveTrain;
import frc.robot.util.Gyro;
import frc.robot.util.MathUtil;

public class BalanceChargeStation extends PIDCommand {
    DriveTrain driveTrain;
    Gyro gyro;
    public BalanceChargeStation(DriveTrain driveTrain, Gyro gyro) {
        super(
            new PIDController(0.045, 0.00085, 0),
            gyro::getRoll,
            0,
            output -> driveTrain.drive(output * 0.55,0, 0, true),
            driveTrain
        );

        getController().setTolerance(20, 20);

        
    }

    @Override
    public boolean isFinished() {
        SmartDashboard.putBoolean("Error", MathUtil.isWithinTolerance(getController().getPositionError(),0,2.5));
        SmartDashboard.putNumber("POsition error", getController().getPositionError());
        return false;//(MathUtil.isWithinTolerance(getController().getPositionError(),0,3));
    }
            
}
 