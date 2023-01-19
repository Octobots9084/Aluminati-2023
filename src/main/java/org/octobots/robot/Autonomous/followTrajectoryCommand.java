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
import com.pathplanner.lib.PathPlannerTrajectory;
import com.pathplanner.lib.commands.PPSwerveControllerCommand;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.Subsystem;

import org.octobots.robot.swerve.DriveTrain;
import org.octobots.robot.util.Gyro;
import org.octobots.robot.util.PoseEstimator;
import org.octobots.robot.util.TrajectoryFollower;
 
 public class followTrajectoryCommand extends CommandBase {
     private final DriveTrain driveTrain;
     private final double maxVel;
     private final double maxAccel;
     private boolean isFirstPath;
     private String path;
     private PathPlannerTrajectory traj;
     private SwerveDriveKinematics kinematics;
     public followTrajectoryCommand(String path, double maxVelocity, double maxAcceleration, boolean isFirstPath) {
         this.driveTrain = DriveTrain.getInstance();
         this.isFirstPath = isFirstPath;
         this.path = path;
         this.maxAccel = maxAcceleration;
         this.maxVel = maxVelocity;
         addRequirements(this.driveTrain);
     }
 
     @Override
     public void initialize() {
      SmartDashboard.putString("Path", "among us");
        traj = PathPlanner.loadPath(path, maxVel, maxAccel);
        kinematics = this.driveTrain.getSwerveDriveKinematics();
     }
 
     @Override
     public void execute() {
      
        new SequentialCommandGroup(
            new InstantCommand(() -> {
              // Reset odometry for the first path you run during auto
              if(isFirstPath){
                this.driveTrain.getPoseEstimator().resetPose();
              }
            }), // The errors are very sussy imposter
            new PPSwerveControllerCommand(
                traj, 
                this::getPose, // Pose supplier
                this.kinematics, // SwerveDriveKinematics
                new PIDController(1, 0, 0), // X controller. Tune these values for your robot. Leaving them 0 will only use feedforwards.
                new PIDController(1, 0, 0), // Y controller (usually the same values as X controller)
                new PIDController(1, 0, 0), // Rotation controller. Tune these values for your robot. Leaving them 0 will only use feedforwards.
                this::setModuleStates,
                driveTrain
            )
        );
        SmartDashboard.putString("Runnin1g", "theimposter");
     }

     public Pose2d getPose() {
        return this.driveTrain.getPoseEstimator().getRobotPose();
     }

     public SwerveModuleState[] setModuleStates(SwerveModuleState[] swerveModuleStates) {
        this.driveTrain.setModuleStates(swerveModuleStates);
        return this.driveTrain.getModuleStates();
     }

 }
 