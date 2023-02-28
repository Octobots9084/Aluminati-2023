package frc.robot.commands.advanced;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.arm.MoveArmToPositionGoingDown;
import frc.robot.commands.arm.Intake.IntakeIn;
import frc.robot.commands.autonomous.driveToPos;
import frc.robot.commands.autonomous.arm.AutoCubeTop;
import frc.robot.subsystems.arm.ArmExtension;
import frc.robot.subsystems.arm.ArmPositions;
import frc.robot.subsystems.arm.CaliGirls;
public class MagicButtonV1 extends SequentialCommandGroup{

    public MagicButtonV1() {
        addCommands(new driveToPos(new Pose2d(12.48,0, new Rotation2d())), new WaitCommand(5),new AutoCubeTop());
    }
}
