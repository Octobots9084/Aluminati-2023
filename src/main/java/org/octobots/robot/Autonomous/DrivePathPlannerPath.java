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

 package org.octobots.robot.Autonomous;

 import com.pathplanner.lib.PathPlanner;
 import edu.wpi.first.wpilibj2.command.CommandBase;
 import org.octobots.robot.swerve.DriveTrain;
 import org.octobots.robot.util.Gyro;
 import org.octobots.robot.util.TrajectoryFollower;
 
 public class DrivePathPlannerPath extends CommandBase {
     private final DriveTrain driveTrain;
     private final Gyro gyro;
     private final double maxVel;
     private final double maxAccel;
     private final String trajectoryJSON;
     private TrajectoryFollower trajectoryFollower;
 
     public DrivePathPlannerPath(String path, double maxVelocity, double maxAcceleration) {
         this.trajectoryJSON = path;
         this.driveTrain = DriveTrain.getInstance();
         this.gyro = Gyro.getInstance();
         this.maxAccel = maxAcceleration;
         this.maxVel = maxVelocity;
         addRequirements(driveTrain);
     }
 
     @Override
     public void initialize() {
         this.trajectoryFollower = new TrajectoryFollower(
                 PathPlanner.loadPath(trajectoryJSON, maxVel, maxAccel, false),
                 true, gyro, driveTrain
         );
     }
 
     @Override
     public void execute() {
         trajectoryFollower.followController();
     }
 
     @Override
     public boolean isFinished() {
         return trajectoryFollower.isFinished();
     }
 
     @Override
     public void end(boolean interrupted) {
         DriveTrain.getInstance().drive(0, 0, 0, true);
     }
 }
 