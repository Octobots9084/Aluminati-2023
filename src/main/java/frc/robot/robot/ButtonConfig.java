package frc.robot.robot;

import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.arm.ArmExtensionPos;
import frc.robot.commands.arm.SetArmAngle;
import frc.robot.commands.arm.SetWristAngle;

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

        // Xbox A Button
        new JoystickButton(ControlMap.XBOX, 1)
                .onTrue(new ArmExtensionPos(1091));
        new JoystickButton(ControlMap.XBOX, 1)
                .onTrue(new SetArmAngle(0.758));

        // Xbox B Button
        new JoystickButton(ControlMap.XBOX, 2)
                .onTrue(new ArmExtensionPos(183));
        new JoystickButton(ControlMap.XBOX, 2)
                .onTrue(new SetArmAngle(0.586));

        // Xbox X  Button
        new JoystickButton(ControlMap.XBOX, 3)
                .onTrue(new SetWristAngle(0.344));

        // Xbox Y Button
        new JoystickButton(ControlMap.XBOX, 4)
                .onTrue(new ArmExtensionPos(3400));
        new JoystickButton(ControlMap.XBOX, 4)
                .onTrue(new SetArmAngle(0.783));
        // new JoystickButton(ControlMap.DRIVER_BUTTONS, 5) 
        //     .onTrue(new ClawsTest());
        // new JoystickButton(ControlMap.XBOX, 4)
        // .onTrue(new SetArmAngle(0.875));
        // new JoystickButton(ControlMap.XBOX, 2).onTrue(new ArmZero());

    }
}
