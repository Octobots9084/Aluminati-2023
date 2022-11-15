package org.octobots.robot;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveDriveOdometry;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveTrain extends SubsystemBase {
    public static final double kMaxSpeed = 0.0; //meters per sec
    public static final double kMaxAngularSpeed = Math.PI; //rotation per sec

    @Override
    public void periodic() {

    }

    public DriveTrain() {

    }
    public void drive(ChassisSpeeds chassisSpeeds) {
        drive(false, chassisSpeeds);
    }
    public void drive(boolean fieldCentric, ChassisSpeeds chassisSpeeds) {

    }
}
