package frc.robot;

import com.fasterxml.jackson.databind.node.POJONode;

import edu.wpi.first.apriltag.AprilTag;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Autonomous.PathPlannerAutos;
import frc.robot.commands.BalanceChargeStation;
import frc.robot.commands.driveToPos;
import frc.robot.commands.RotateTo;
import frc.robot.commands.SetDriverAssist;
import frc.robot.commands.ZeroGyro;
import frc.robot.swerve.DriveTrain;
import frc.robot.util.Gyro;
import frc.robot.vision.Constants.FieldConstants;

public class ButtonConfig {
    public void initTeleop() {
        // DRIVER LEFT

        new JoystickButton(ControlMap.DRIVER_BUTTONS, 13)
            .whileTrue(new SetDriverAssist(true));
        
        new JoystickButton(ControlMap.DRIVER_BUTTONS, 13)
            .whileFalse
            (new SetDriverAssist(false));

        new JoystickButton(ControlMap.DRIVER_LEFT, 1)
            .onTrue(new RotateTo(0));

        new JoystickButton(ControlMap.DRIVER_BUTTONS, 11)
            .onTrue(new ZeroGyro());

        new JoystickButton(ControlMap.DRIVER_BUTTONS, 10) 
            .onTrue(new BalanceChargeStation(DriveTrain.getInstance(), Gyro.getInstance()));

        new JoystickButton(ControlMap.DRIVER_BUTTONS, 9) 
            .onTrue(new driveToPos(new Pose2d(15, 0.7, new Rotation2d(0))));


    }
}
 