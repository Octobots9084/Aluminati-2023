/*
 * This file is part of OctoSwerve-Revamp, licensed under the GNU General Public License (GPLv3).
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

package org.octobots.robot.util;

import edu.wpi.first.wpilibj2.command.CommandBase;
import org.octobots.robot.swerve.DriveTrain;

public class DrivePathweaverPath extends CommandBase {
    private final String path;
    private final DriveTrain driveTrain;
    private TrajectoryFollower trajectoryFollower;

    public DrivePathweaverPath(String path) {
        // Get drivetrain and path
        this.path = path;
        this.driveTrain = DriveTrain.getInstance();
        addRequirements(driveTrain);
    }

    @Override
    public void initialize() {
        this.trajectoryFollower = new TrajectoryFollower(TrajectoryFollower.getTrajectoryFromPathweaver(path), true, Gyro.getInstance(), driveTrain);
    }

    @Override
    public void execute() {
        this.trajectoryFollower.followController();
    }

    @Override
    public boolean isFinished() {
        return trajectoryFollower.isFinished();
    }
}
