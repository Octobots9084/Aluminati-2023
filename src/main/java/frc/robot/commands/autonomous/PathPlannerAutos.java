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
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.arm.basic.tolerance.CaliBottomPosTolerance;
import frc.robot.commands.arm.basic.tolerance.CaliTopPosTolerance;
import frc.robot.commands.arm.basic.tolerance.ExtensionPosTolerance;
import frc.robot.commands.arm.yeet.Arm2PosStow;
import frc.robot.commands.autonomous.arm.AutoConeLow;
import frc.robot.commands.autonomous.arm.AutoConeMid;
import frc.robot.commands.autonomous.arm.AutoConeTop;
import frc.robot.commands.autonomous.arm.AutoConeTopBalance;
import frc.robot.commands.autonomous.arm.AutoCubeLow;
import frc.robot.commands.autonomous.arm.AutoCubeMid;
import frc.robot.commands.autonomous.arm.AutoCubeTop;
import frc.robot.commands.autonomous.arm.AutoGroundIntakeCone;
import frc.robot.commands.autonomous.arm.AutoGroundIntakeCube;
import frc.robot.commands.spatula.SetSpatulaVoltageAndPos;
import frc.robot.subsystems.arm.ArmPositions;
import frc.robot.subsystems.arm.CaliGirls;
import frc.robot.subsystems.swerve.DriveTrain;

public final class PathPlannerAutos {
    private static final Map<String, Command> eventMap = new HashMap<>(Map.ofEntries(
            Map.entry("driveToPos", new DriveToPosition(new Pose2d(14, 0.7, new Rotation2d(0)))),
            Map.entry("BalanceChargeStation", new BalanceChargeStation()),
            Map.entry("ConeTop", new AutoConeTop()),
            Map.entry("ConeTopBalance", new AutoConeTopBalance()),
            Map.entry("ConeMid", new AutoConeMid()),
            Map.entry("ConeLow", new AutoConeLow()),
            Map.entry("CubeTop", new AutoCubeTop()),
            Map.entry("CubeMid", new AutoCubeMid()),
            Map.entry("CubeLow", new AutoCubeLow()),
            Map.entry("IntakeCone", new AutoGroundIntakeCone()),
            Map.entry("IntakeCube", new AutoGroundIntakeCube()),
            Map.entry("StowArm", new Arm2PosStow(ArmPositions.STOW)),
            Map.entry("DrivePosition", new ExtensionPosTolerance(0.0).andThen(new CaliTopPosTolerance(ArmPositions.PRE_DRIVE_POSITION.wrist)).andThen(new CaliBottomPosTolerance(ArmPositions.PRE_DRIVE_POSITION.armAngle, CaliGirls.getInstance().getBottomKf())).alongWith(new WaitCommand(0.3)).andThen(new CaliTopPosTolerance(ArmPositions.DRIVE_POSITION.wrist))),
            Map.entry("Wait1", new WaitCommand(1)),
            Map.entry("OtherCollect",new SetSpatulaVoltageAndPos(-12, 0).alongWith(new Arm2PosStow(ArmPositions.STOW))),
            Map.entry("OtherIntakeIn",new SetSpatulaVoltageAndPos(-0.5, 0.34))
            ));

    public static final SwerveAutoBuilder autoBuilder = new SwerveAutoBuilder(
            DriveTrain.getInstance()::getPose2d,
            DriveTrain.getInstance().getPoseEstimator()::resetPose,
            new PIDConstants(3.5, 0, 0),
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
        DriveTrain.getInstance().setUseDriverAssist(false);
        return autoBuilder.fullAuto(PathPlanner.loadPathGroup("1MeterForward", new PathConstraints(1, 0.1)));
    }

    

    public static CommandBase Square() {
        return autoBuilder.fullAuto(PathPlanner.loadPathGroup("PlacingTesting", new PathConstraints(2, 2)));
    }


    public static CommandBase PlaceConeAndBalance() {
        return autoBuilder.fullAuto(PathPlanner.loadPathGroup("ChargeStationMove", new PathConstraints(20, 20)));
    }

    public static CommandBase PlaceConeAndBalanceAndCommunity() {
        return autoBuilder.fullAuto(PathPlanner.loadPathGroup("ChargeStationMoveAndGoOutsideCommunityNoCube", new PathConstraints(1.5, 3)));
    }
    public static CommandBase PlaceConeAndMoveBackBottom() {
        return autoBuilder.fullAuto(PathPlanner.loadPathGroup("MoveAndGrabConeBottom", new PathConstraints(2, 1)));
    }

    public static CommandBase PlaceConeAndMoveBackTop() {
        return autoBuilder.fullAuto(PathPlanner.loadPathGroup("MoveAndGrabConeTop", new PathConstraints(2, 1)));
    }

    public static CommandBase Place2Cone() {
        return autoBuilder.fullAuto(PathPlanner.loadPathGroup("MoveAndGrabConeBottomAndComeBack", new PathConstraints(2, 1)));
    }
    
    public static CommandBase threedc2Cones() {
        return autoBuilder.fullAuto(PathPlanner.loadPathGroup("3-d-c 2 ALL CONES", new PathConstraints(4, 3)));
    }

    public static CommandBase square() {
        return autoBuilder.fullAuto(PathPlanner.loadPathGroup("square", new PathConstraints(0.5, 0.5)));
    }

    public static CommandBase squarespiiin() {
        return autoBuilder.fullAuto(PathPlanner.loadPathGroup("square + spin", new PathConstraints(1, 1)));
    }
    
    public static CommandBase none() {
        return Commands.none();
    }

    public static CommandBase ConHigConHigBal() {
        return autoBuilder.fullAuto(PathPlanner.loadPathGroup("ConHigConHigBal", new PathConstraints(5, 3)));
    }

    
    public static CommandBase GetOutOfDaWay() {
        return autoBuilder.fullAuto(PathPlanner.loadPathGroup("GetOutOfDaWayCable", new PathConstraints(2, 1)));
    }
    private PathPlannerAutos() {
        throw new UnsupportedOperationException("This is a utility class!");
    }
}