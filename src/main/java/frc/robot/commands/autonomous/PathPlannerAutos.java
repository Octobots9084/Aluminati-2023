package frc.robot.commands.autonomous;

import com.pathplanner.lib.PathConstraints;
import com.pathplanner.lib.PathPlanner;
import com.pathplanner.lib.auto.PIDConstants;
import com.pathplanner.lib.auto.SwerveAutoBuilder;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.WaitCommand;

import java.util.HashMap;
import java.util.Map;

import frc.robot.subsystems.swerve.DriveTrain;
import frc.robot.util.Gyro;

public final class PathPlannerAutos {
  private static final Map<String, Command> eventMap = new HashMap<>(Map.ofEntries(
    Map.entry("testcommand", new WaitCommand(5)),
    Map.entry("BalanceChargeStation", new BalanceChargeStation(DriveTrain.getInstance(), Gyro.getInstance()))
  ));

  public static final SwerveAutoBuilder autoBuilder = new SwerveAutoBuilder(
    DriveTrain.getInstance()::getPose2d,
    DriveTrain.getInstance().getPoseEstimator()::resetPose,
    new PIDConstants(0, 0, 0),
    new PIDConstants(0, 0, 0),
    DriveTrain.getInstance()::drive,
    eventMap,
    DriveTrain.getInstance()
  );

  public static CommandBase testPath() {
    return autoBuilder.fullAuto(PathPlanner.loadPathGroup("TestingPath", new PathConstraints(1, 0.5)));
  }

  public static CommandBase Onemeter() {
    DriveTrain.getInstance().setUseDriverAssist(true);
    return autoBuilder.fullAuto(PathPlanner.loadPathGroup("1m", new PathConstraints(1, 3)));
  }

  public static CommandBase BalanceChargeStation() {
    DriveTrain.getInstance().setUseDriverAssist(true);
    return autoBuilder.fullAuto(PathPlanner.loadPathGroup("ChargeStation", new PathConstraints(3, 2)));
  }



  public static CommandBase none() {
    return Commands.none();
  }

  private PathPlannerAutos() {
    throw new UnsupportedOperationException("This is a utility class!");
  }
}