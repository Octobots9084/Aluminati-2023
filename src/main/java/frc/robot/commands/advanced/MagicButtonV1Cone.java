package frc.robot.commands.advanced;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.autonomous.DriveToPosition;
public class MagicButtonV1Cone extends SequentialCommandGroup{

    public MagicButtonV1Cone() {

        addCommands(new DriveToPosition(new Pose2d(13, 2.5, new Rotation2d())));
        //addCommands(new driveToPos(new Pose2d(12,0, new Rotation2d())));//, new WaitCommand(5),new AutoCubeTop());
    }
}
