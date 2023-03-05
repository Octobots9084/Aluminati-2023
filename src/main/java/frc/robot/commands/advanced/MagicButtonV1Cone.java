package frc.robot.commands.advanced;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.arm.intake.basic.IntakeIn;
import frc.robot.commands.arm.slow.MoveArmToPositionGoingDown;
import frc.robot.commands.autonomous.driveToPos;
import frc.robot.commands.autonomous.arm.AutoCubeTop;
import frc.robot.subsystems.arm.ArmExtension;
import frc.robot.subsystems.arm.ArmPositions;
import frc.robot.subsystems.arm.CaliGirls;
public class MagicButtonV1Cone extends SequentialCommandGroup{

    public MagicButtonV1Cone() {

        addCommands(new driveToPos(new Pose2d(13, 2.5, new Rotation2d())));
        //addCommands(new driveToPos(new Pose2d(12,0, new Rotation2d())));//, new WaitCommand(5),new AutoCubeTop());
    }
}
