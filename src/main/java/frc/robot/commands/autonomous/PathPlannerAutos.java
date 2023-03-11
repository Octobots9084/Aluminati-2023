package frc.robot.commands.autonomous;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pathplanner.lib.PathConstraints;
import com.pathplanner.lib.PathPlanner;
import com.pathplanner.lib.PathPlannerTrajectory;
import com.pathplanner.lib.auto.PIDConstants;
import com.pathplanner.lib.auto.SwerveAutoBuilder;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.commands.autonomous.arm.AutoConeLow;
import frc.robot.commands.autonomous.arm.AutoConeMid;
import frc.robot.commands.autonomous.arm.AutoConeTop;
import frc.robot.commands.autonomous.arm.AutoCubeLow;
import frc.robot.commands.autonomous.arm.AutoCubeMid;
import frc.robot.commands.autonomous.arm.AutoCubeTop;
import frc.robot.commands.autonomous.arm.AutoGroundIntakeCone;
import frc.robot.commands.autonomous.arm.AutoGroundIntakeCube;
import frc.robot.subsystems.swerve.DriveTrain;

public final class PathPlannerAutos {
    private static final Map<String, Command> eventMap = new HashMap<>(Map.ofEntries(
            Map.entry("driveToPos", new DriveToPosition(new Pose2d(14, 0.7, new Rotation2d(0)))),
            Map.entry("BalanceChargeStation", new BalanceChargeStation()),
            Map.entry("ConeTop", new AutoConeTop()),
            Map.entry("ConeMid", new AutoConeMid()),
            Map.entry("ConeLow", new AutoConeLow()),
            Map.entry("CubeTop", new AutoCubeTop()),
            Map.entry("CubeMid", new AutoCubeMid()),
            Map.entry("CubeLow", new AutoCubeLow()),
            Map.entry("IntakeCone", new AutoGroundIntakeCone()),
            Map.entry("IntakeCube", new AutoGroundIntakeCube())
            ));

    public static final SwerveAutoBuilder autoBuilder = new SwerveAutoBuilder(
            DriveTrain.getInstance()::getPose2d,
            DriveTrain.getInstance().getPoseEstimator()::resetPose,
            new PIDConstants(0, 0, 0),
            new PIDConstants(2, 0, 0),
            DriveTrain.getInstance()::driveAutos,
            eventMap,
            true,
            DriveTrain.getInstance());

    public static CommandBase testPath() {
        List<PathPlannerTrajectory> pathgroup = PathPlanner.loadPathGroup("New New Path", new PathConstraints(1, 0.5));
        SmartDashboard.putString("TEST", "Test");
        SmartDashboard.putString("P654375ath", pathgroup.get(0).getEndState().toString());
        return autoBuilder.fullAuto(pathgroup);
    }

    public static CommandBase Onemeter() {
        DriveTrain.getInstance().setUseDriverAssist(true);
        return autoBuilder.fullAuto(PathPlanner.loadPathGroup("1m", new PathConstraints(1, 3)));
    }

    

    public static CommandBase OneMeterSpin() {
        return autoBuilder.fullAuto(PathPlanner.loadPathGroup("TestingPath", new PathConstraints(0.2, 0.2)));
    }

    public static CommandBase PlaceConeAndBalance() {
        return autoBuilder.fullAuto(PathPlanner.loadPathGroup("ChargeStationMoveAndGoOutsideCommunity", new PathConstraints(3, 2)));
    }
    public static CommandBase PlaceConeAndMoveBackBottom() {
        return autoBuilder.fullAuto(PathPlanner.loadPathGroup("MoveAndGrabConeBottom", new PathConstraints(2, 1)));
    }

    public static CommandBase PlaceConeAndMoveBackTop() {
        return autoBuilder.fullAuto(PathPlanner.loadPathGroup("MoveAndGrabConeTop", new PathConstraints(2, 1)));
    }

    public static CommandBase ConeMiddleAndCollectTop() {
        return autoBuilder.fullAuto(PathPlanner.loadPathGroup("MiddleMoveAndGrabConeTop", new PathConstraints(2, 1)));
    }

    public static CommandBase ConeMiddleAndCollectBottom() {
        return autoBuilder.fullAuto(PathPlanner.loadPathGroup("MiddleMoveAndGrabConeBottom", new PathConstraints(2, 1)));
    }

    public static CommandBase ConeMiddleAndCollectTopAndCharge() {
        return autoBuilder.fullAuto(PathPlanner.loadPathGroup("MiddleMoveAndGrabConeTopBalance", new PathConstraints(2, 1)));
    }

    public static CommandBase ConeMiddleandCollectBottomAndCharge() {
        return autoBuilder.fullAuto(PathPlanner.loadPathGroup("MiddleMoveAndGrabConeBottomBalance", new PathConstraints(2, 1)));
    }


    public static CommandBase Place2Cone() {
        return autoBuilder.fullAuto(PathPlanner.loadPathGroup("MoveAndGrabConeBottomAndComeBack", new PathConstraints(2, 1)));
    }
    

    public static CommandBase none() {
        return Commands.none();
    }

    private PathPlannerAutos() {
        throw new UnsupportedOperationException("This is a utility class!");
    }
}