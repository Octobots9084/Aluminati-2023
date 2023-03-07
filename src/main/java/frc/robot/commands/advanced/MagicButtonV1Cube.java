package frc.robot.commands.advanced;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.autonomous.DriveToPos;
public class MagicButtonV1Cube extends SequentialCommandGroup{

    public MagicButtonV1Cube() {

        addCommands(new DriveToPos(new Pose2d(13, 3, new Rotation2d())));
        //addCommands(new driveToPos(new Pose2d(12,0, new Rotation2d())));//, new WaitCommand(5),new AutoCubeTop());
    }
}
