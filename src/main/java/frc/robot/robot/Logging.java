package frc.robot.robot;

import frc.robot.util.shuffleboard.RSTab;
import frc.robot.util.shuffleboard.RobotShuffleboard;

// import frc.robot.util.shuffleboard.RSTab;

public class Logging {
    public static final RobotShuffleboard robotShuffleboard = new RobotShuffleboard();
    public static RSTab driveDashboard = robotShuffleboard.getTab("drive");
    public static RSTab armDashboard = robotShuffleboard.getTab("arm");

    private Logging() {
        // var extension = armDashboard.add("Arm Extension", 0).getEntry();
        // var claw_rotation = armDashboard.add("Claw Rotation", 0).getEntry();
        // var arm_rotation = armDashboard.add("Arm Rotation", 0).getEntry();
    }

}
