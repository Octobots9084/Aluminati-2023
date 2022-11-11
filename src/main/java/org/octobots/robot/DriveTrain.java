package org.octobots.robot;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveDriveOdometry;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.math.kinematics;

public class DriveTrain {
    public static final double kMaxSpeed = 0.0; //meters per sec
    public static final double kMaxAngularSpeed = Math.PI; //rotation per sec
    private final Translation2d m_frontLeftLocation = new Translation2d(0, 0);
    private final Translation2d m_frontRightLocation = new Translation2d(0, 0);
    private final Translation2d m_backLeftLocation = new Translation2d(0, 0);
    private final Translation2d m_backRightLocation = new Translation2d(0, 0);

    SwerveDriveKinematics m_kinematics = new SwerveDriveKinematics(m_frontLeftLocation, m_frontRightLocation, m_backLeftLocation, m_backRightLocation);

    SwerveDriveOdometry m_odometry = new SwerveDriveOdometry(m_kinematics, getGyroHeading(), new Pose2d(0.0, 0.0, new Rotation2d()));

    public void toSwerveModuleStates(ChassisSpeeds speeds) {
        speeds = new ChassisSpeeds(0, 0, 0);

        SwerveModuleState[] moduleStates = kinematics.toSwerveModuleStates(speeds);
        SwerveModuleState frontLeft = moduleStates[0];
        SwerveModuleState frontRight = moduleStates[1];
        SwerveModuleState backLeft = moduleStates[2];
        SwerveModuleState backRight = moduleStates[3];
    }

    public SwerveModuleState optimize() {
        //code
    }

    public void toChassisSpeeds(SwerveModuleState states) {
        var frontLeftState = new SwerveModuleState(0, Rotation2d.fromDegrees(0));
        var frontRightState = new SwerveModuleState(0, Rotation2d.fromDegrees(0));
        var backLeftState = new SwerveModuleState(0, Rotation2d.fromDegrees(0));
        var backRightState = new SwerveModuleState(0, Rotation2d.fromDegrees(0));

        ChassisSpeeds chassisSpeeds = m_kinematics.toChassisSpeeds(frontLeftState, frontRightState, backLeftState, backRightState);

        double forward = chassisSpeeds.vxMetersPerSecond;
        double sideways = chassisSpeeds.vyMetersPerSecond;
        double angular = chassisSpeeds.omegaRadiansPerSecond;
    }

    @Override
    public void periodic() {
        var gyroAngle = Rotation2d.fromDegrees(-m_gyro.getAngle());

        m_pose = m_odometry.update(gyroAngle, m_frontLeftModule.getState(), m_frontRightModule.getState(), m_backLeftModule.getState(), m_backRightModule.getState());
    }

    public void resetPose() {
        //code
    }

    public void updateOdometry() {
    m_odometry.update(
            m_gyro.getRotation2d(),
            m_frontLeft.getPosition(),
            m_frontRight.getPosition(),
            m_backLeft.getPosition(),
            m_backRight.getPosition());
    }
}
