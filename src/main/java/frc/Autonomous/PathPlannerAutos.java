package frc.Autonomous;

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

import frc.subsystems.swerve.DriveTrain;

public final class PathPlannerAutos {
  private static final Map<String, Command> eventMap = new HashMap<>(Map.ofEntries(
    Map.entry("testcommand", new WaitCommand(5))
  ));

  private static final SwerveAutoBuilder autoBuilder = new SwerveAutoBuilder(
    DriveTrain.getInstance()::getPose2d,
    DriveTrain.getInstance().getPoseEstimator()::resetPose,
    new PIDConstants(1.2, 0, 0),
    new PIDConstants(1.2, 0, 0),
    DriveTrain.getInstance()::drive,
    eventMap,
    DriveTrain.getInstance()
  );

  public static CommandBase exampleAuto() {
    return autoBuilder.fullAuto(PathPlanner.loadPathGroup("New Path", new PathConstraints(4, 3)));
  }

  public static CommandBase Onemeter() {
    return autoBuilder.fullAuto(PathPlanner.loadPathGroup("1m", new PathConstraints(4, 3)));
  }



  public static CommandBase none() {
    return Commands.none();
  }

  private PathPlannerAutos() {
    throw new UnsupportedOperationException("This is a utility class!");
  }
}