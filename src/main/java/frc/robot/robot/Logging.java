package frc.robot.robot;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.autonomous.PathPlannerAutos;
import frc.robot.commands.autonomous.arm.AutoConeLow;
import frc.robot.commands.autonomous.arm.AutoConeMid;
import frc.robot.commands.autonomous.arm.AutoConeTop;
import frc.robot.commands.autonomous.arm.AutoCubeLow;
import frc.robot.commands.autonomous.arm.AutoCubeMid;
import frc.robot.commands.autonomous.arm.AutoCubeTop;
import frc.robot.commands.autonomous.arm.AutoGroundIntakeCone;
import frc.robot.commands.autonomous.arm.AutoGroundIntakeCube;
import frc.robot.subsystems.arm.ArmExtension;
import frc.robot.subsystems.arm.CaliGirls;
import frc.robot.subsystems.swerve.DriveTrain;
import frc.robot.util.Gyro;
import frc.robot.util.shuffleboard.RSTab;
import frc.robot.util.shuffleboard.RSTileOptions;
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
    private static SendableChooser<Command> autoChooser;

    private Logging(CaliGirls caliGirls, ArmExtension armExtension, DriveTrain driveTrain, Gyro gyro) {
        this.caliGirls = caliGirls;
        this.armExtension = armExtension;
        this.drive = driveTrain;
        this.gyro = gyro;
        this.autoChooser = new SendableChooser<Command>();
        addAutoOptions();
    }

    private void addAutoOptions() {
        //
        autoChooser.setDefaultOption("No Auto", null);

        autoChooser.addOption("Auto Cone Middle", new AutoConeMid());
        autoChooser.addOption("Auto Cone Top", new AutoConeTop());
        autoChooser.addOption("Auto Cone Low", new AutoConeLow());
        autoChooser.addOption("Auto Cube Top", new AutoCubeTop());
        autoChooser.addOption("Auto Cube Middle", new AutoCubeMid());
        autoChooser.addOption("Auto Cube Low", new AutoCubeLow());
        autoChooser.addOption("Auto Intake Cone", new AutoGroundIntakeCone());
        autoChooser.addOption("Auto Intake Cube", new AutoGroundIntakeCube());
        autoChooser.addOption("Balance Charge Station", PathPlannerAutos.BalanceChargeStation());
        autoChooser.addOption("Move and Grab Cone", PathPlannerAutos.TestAutoOne());

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

    }

    public static void addAutoChooser() {
        autoDashboard.setSendable("Auto Chooser", autoChooser, new RSTileOptions(2, 1, 0, 0));
    }

    public static SendableChooser<Command> getAutoChooser() {
        return autoChooser;
    }

}
