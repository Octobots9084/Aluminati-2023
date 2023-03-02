package frc.robot.robot;

import frc.robot.subsystems.arm.ArmExtension;
import frc.robot.subsystems.arm.CaliGirls;
import frc.robot.subsystems.swerve.DriveTrain;
import frc.robot.util.Gyro;
import frc.robot.util.shuffleboard.RSTab;
import frc.robot.util.shuffleboard.RobotShuffleboard;

// import frc.robot.util.shuffleboard.RSTab;

public class Logging {
    private static Logging INSTANCE;

    public static Logging getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Logging(CaliGirls.getInstance(), ArmExtension.getInstance(), DriveTrain.getInstance(),
                    Gyro.getInstance());
        }
        return INSTANCE;
    }

    public static final RobotShuffleboard robotShuffleboard = new RobotShuffleboard();
    public static final RSTab driveDashboard = robotShuffleboard.getTab("drive");
    public static final RSTab armDashboard = robotShuffleboard.getTab("arm");
    public static final RSTab autoDashboard = robotShuffleboard.getTab("auto");

    private static CaliGirls caliGirls;
    private static ArmExtension armExtension;
    private static DriveTrain drive;
    private static Gyro gyro;

    private Logging(CaliGirls caliGirls, ArmExtension armExtension, DriveTrain driveTrain, Gyro gyro) {
        this.caliGirls = caliGirls;
        this.armExtension = armExtension;
        this.drive = driveTrain;
        this.gyro = gyro;

    }

    public static void updateLogging() {
        //Drive
        driveDashboard.setEntry("X-Pos", drive.getPoseEstimator().getRobotPose().getX());
        driveDashboard.setEntry("Y-Pos", drive.getPoseEstimator().getRobotPose().getY());
        driveDashboard.setEntry("Rot Deg", drive.getPoseEstimator().getRobotPose().getRotation().getDegrees());

        driveDashboard.setEntry("Gyro Pitch", gyro.getRoll());

        //Arm
        armDashboard.setEntry("Arm Extension", armExtension.getPosition());
        armDashboard.setEntry("Claw Rotation", caliGirls.getTopPos());
        armDashboard.setEntry("Arm Rotation", caliGirls.getBottomPos());
        // armDashboard.setEntry("Motor Velocity", armExtension.motor.getEncoder().getVelocity());

    }

}
