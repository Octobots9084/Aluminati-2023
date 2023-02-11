package frc.robot.robot;

import com.fasterxml.jackson.databind.node.POJONode;

import edu.wpi.first.apriltag.AprilTag;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.arm.Arm;
import frc.robot.commands.arm.ArmExtensionTest;
import frc.robot.commands.arm.ArmZero;
import frc.robot.commands.arm.ClawsTest;
import frc.robot.commands.arm.SetArmAngle;
import frc.robot.commands.autonomous.BalanceChargeStation;
import frc.robot.commands.autonomous.driveToPos;
import frc.robot.commands.swerve.SetDriverAssist;
import frc.robot.commands.swerve.ZeroGyro;
import frc.robot.commands.vision.RotateTo;
import frc.robot.subsystems.swerve.DriveTrain;
import frc.robot.util.Gyro;

public class ButtonConfig {
    public void initTeleop() {
        // DRIVER LEFT

        // new JoystickButton(ControlMap.DRIVER_BUTTONS, 13)
        //     .whileTrue(new SetDriverAssist(true));
        
        // new JoystickButton(ControlMap.DRIVER_BUTTONS, 13)
        //     .whileFalse
        //     (new SetDriverAssist(false));

        // // new JoystickButton(ControlMap.DRIVER_LEFT, 1)
        // //     .onTrue(new RotateTo(0));

        // new JoystickButton(ControlMap.DRIVER_BUTTONS, 11)
        //     .onTrue(new ZeroGyro());

        // new JoystickButton(ControlMap.DRIVER_BUTTONS, 10) 
        //     .onTrue(new BalanceChargeStation(DriveTrain.getInstance(), Gyro.getInstance()));

        // new JoystickButton(ControlMap.DRIVER_BUTTONS, 9) 
            // .onTrue(new driveToPos(new Pose2d(14, 0.7, new Rotation2d(0))));
        new JoystickButton(ControlMap.XBOX, 1) 
            .onTrue(new ArmExtensionTest());
        // new JoystickButton(ControlMap.DRIVER_BUTTONS, 5) 
        //     .onTrue(new ClawsTest());
        new JoystickButton(ControlMap.XBOX, 4)
        .onTrue(new SetArmAngle(0.875));
        new JoystickButton(ControlMap.XBOX, 2).onTrue(new ArmZero());
        

    }
}
 