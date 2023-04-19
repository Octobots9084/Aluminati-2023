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
import frc.robot.commands.advanced.CollectFloor;
import frc.robot.commands.advanced.ConeInjectHigh;
import frc.robot.commands.advanced.CubeInjectHigh;
import frc.robot.commands.arm.basic.instant.ArmPosInstant;
import frc.robot.commands.arm.basic.tolerance.CaliBottomPosTolerance;
import frc.robot.commands.arm.basic.tolerance.CaliTopPosTolerance;
import frc.robot.commands.arm.basic.tolerance.ExtensionPosTolerance;
import frc.robot.commands.arm.yeet.Arm2PosStow;
import frc.robot.commands.autonomous.arm.AutoConeLow;
import frc.robot.commands.autonomous.arm.AutoConeMid;
import frc.robot.commands.autonomous.arm.AutoConeTopBalance;
import frc.robot.commands.autonomous.arm.AutoCubeLow;
import frc.robot.commands.autonomous.arm.AutoCubeMid;
import frc.robot.commands.autonomous.arm.AutoCubeTop;
import frc.robot.commands.autonomous.arm.AutoGroundIntakeCube;
import frc.robot.commands.spatula.SetSpatulaVoltageAndPos;
import frc.robot.commands.vision.AutoAutoAlign;
import frc.robot.subsystems.arm.ArmPositions;
import frc.robot.subsystems.arm.CaliGirls;
import frc.robot.subsystems.swerve.DriveTrain;

public final class PathPlannerAutos {
    private static final Map<String, Command> eventMap = new HashMap<>(Map.ofEntries(
            Map.entry("driveToPos", new DriveToPosition(new Pose2d(14, 0.7, new Rotation2d(0)))),
            Map.entry("BalanceChargeStation", new BalanceChargeStation()),
            Map.entry("ConeTop", new ConeInjectHigh()),
            Map.entry("AutoAlign", new AlignAndPlace()),
            Map.entry("AlignArm", new CaliBottomPosTolerance(ArmPositions.AUTO_ALIGN.armAngle,0.0)),
            Map.entry("ConeTopBalance", new AutoConeTopBalance()),
            Map.entry("ConeMid", new AutoConeMid()),
            Map.entry("ConeLow", new AutoConeLow()),
            Map.entry("CubeTop", new CubeInjectHigh()),
            Map.entry("CubeMid", new AutoCubeMid()),
            Map.entry("CubeLow", new AutoCubeLow()),
            Map.entry("IntakeCone", new CollectFloor()),
            Map.entry("IntakeCube", new AutoGroundIntakeCube()),
            Map.entry("StowArm", new Arm2PosStow(ArmPositions.STOW)),
            Map.entry("DrivePosition", new ExtensionPosTolerance(0.0).andThen(new CaliTopPosTolerance(ArmPositions.PRE_DRIVE_POSITION.wrist)).andThen(new CaliBottomPosTolerance(ArmPositions.PRE_DRIVE_POSITION.armAngle, CaliGirls.getInstance().getBottomKf())).alongWith(new WaitCommand(0.3)).andThen(new CaliTopPosTolerance(ArmPositions.DRIVE_POSITION.wrist))),
            Map.entry("Wait1", new WaitCommand(1)),
            Map.entry("OtherCollect",new SetSpatulaVoltageAndPos(-12, 0).alongWith(new Arm2PosStow(ArmPositions.STOW))),
            Map.entry("OtherIntakeIn",new SetSpatulaVoltageAndPos(-0.5, 0.34)),
            Map.entry("RotateTo0",new DriveDriverAssist(0)),
            Map.entry("RotateTo180", new DriveDriverAssist(180)),
            Map.entry("AlignPosition", new ArmPosInstant(ArmPositions.AUTO_ALIGN)),
            Map.entry("OtherPlace", new SetSpatulaVoltageAndPos(0, 0.12).alongWith(new WaitCommand(0.12)).andThen(new SetSpatulaVoltageAndPos(4, 0.12)).andThen(new WaitCommand(0.3)).andThen(new SetSpatulaVoltageAndPos(0, 0.45)))));

    public static final SwerveAutoBuilder autoBuilder = new SwerveAutoBuilder(
            DriveTrain.getInstance()::getPose2dPathplanner,
            DriveTrain.getInstance().getPoseEstimator()::resetPose,
            new PIDConstants(1.7,0, 0),
            new PIDConstants(2, 0, 0),
            DriveTrain.getInstance()::driveAutos,
            eventMap,
            true,
            DriveTrain.getInstance());

    // public static CommandBase testPath() {
    //     List<PathPlannerTrajectory> pathgroup = PathPlanner.loadPathGroup("New New Path", new PathConstraints(1, 0.5));
    //     SmartDashboard.putString("TEST", "Test");
    //     SmartDashboard.putString("P654375ath", pathgroup.get(0).getEndState().toString());
    //     return autoBuilder.fullAuto(pathgroup);
    // }

    public static CommandBase CableSideBlue() {
        // DriveTrain.getInstance().setTargetRotationAngle(0);
        // DriveTrain.getInstance().setUseDriverAssist(true);
        return autoBuilder.fullAuto(PathPlanner.loadPathGroup("BluCableConHigCubLo", new PathConstraints(4.5, 4)));
    }

    
    public static CommandBase CableSideBlue2() {
        // DriveTrain.getInstance().setTargetRotationAngle(0);
        // DriveTrain.getInstance().setUseDriverAssist(true);
        return autoBuilder.fullAuto(PathPlanner.loadPathGroup("CableConHigCubHigBlue", new PathConstraints(4.5, 4)));
    }


    public static CommandBase CableSideRed() {
        // DriveTrain.getInstance().setTargetRotationAngle(0);
        // DriveTrain.getInstance().setUseDriverAssist(true);
        return autoBuilder.fullAuto(PathPlanner.loadPathGroup("CableConHigCubHigRed", new PathConstraints(4.5, 4)));
    }

    public static CommandBase DriveCollectChargeBlue() {
        // DriveTrain.getInstance().setTargetRotationAngle(0);
        // DriveTrain.getInstance().setUseDriverAssist(true);
        return autoBuilder.fullAuto(PathPlanner.loadPathGroup("RedCableConHigCubLo", new PathConstraints(4.5, 4)));
    }

    public static CommandBase DriveCollectChargeRed() {
        // DriveTrain.getInstance().setTargetRotationAngle(0);
        // DriveTrain.getInstance().setUseDriverAssist(true);
        return autoBuilder.fullAuto(PathPlanner.loadPathGroup("RedCableConHigCubLo", new PathConstraints(4.5, 4)));
    }

    

    // public static CommandBase Square() {


    //     return autoBuilder.fullAuto(PathPlanner.loadPathGroup("PlacingTesting", new PathConstraints(3, 2)));
    // }


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