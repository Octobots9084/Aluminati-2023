package org.octobots.robot.util;

import edu.wpi.first.wpilibj2.command.CommandBase;
import org.octobots.robot.swerve.DriveTrain;

public class DrivePathweaverPath extends CommandBase {
    private final String path;
    private final DriveTrain driveTrain;
    private TrajectoryFollower trajectoryFollower;

    public DrivePathweaverPath(String path) {
        this.path = path;
        this.driveTrain = DriveTrain.getInstance();
        addRequirements(driveTrain);
    }

    @Override
    public void initialize() {
        this.trajectoryFollower = new TrajectoryFollower(TrajectoryFollower.getTrajectoryFromPathweaver(path), true, Gyro.getInstance(), driveTrain);
    }

    @Override
    public void execute() {
        this.trajectoryFollower.followController();
    }

    @Override
    public boolean isFinished() {
        return trajectoryFollower.isFinished();
    }
}
