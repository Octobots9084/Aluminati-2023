package frc.robot.robot;

import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.arm.ArmExtensionPos;
import frc.robot.commands.arm.ArmZero;
import frc.robot.commands.arm.CloseClaw;
import frc.robot.commands.arm.Grab;
import frc.robot.commands.arm.MoveArmToPosition;
import frc.robot.commands.arm.OpenClaw;
import frc.robot.commands.arm.SetArmAngle;
import frc.robot.commands.arm.SetIntakeVoltage;
import frc.robot.commands.arm.SetWristAngle;
import frc.robot.commands.swerve.SetDriverAssist;
import frc.robot.subsystems.arm.ArmPositions;

public class ButtonConfig {
    public void initTeleop() {
        // DRIVER LEFT

        new JoystickButton(ControlMap.DRIVER_BUTTONS, 13)
                .whileTrue(new SetDriverAssist(true));

        new JoystickButton(ControlMap.DRIVER_BUTTONS, 13)
                .whileFalse(new SetDriverAssist(false));

        // // new JoystickButton(ControlMap.DRIVER_LEFT, 1)
        // //     .onTrue(new RotateTo(0));

        // new JoystickButton(ControlMap.DRIVER_BUTTONS, 11)
        //     .onTrue(new ZeroGyro());

        // new JoystickButton(ControlMap.DRIVER_BUTTONS, 10) 
        //     .onTrue(new BalanceChargeStation(DriveTrain.getInstance(), Gyro.getInstance()));

        // new JoystickButton(ControlMap.DRIVER_BUTTONS, 9) 
        // .onTrue(new driveToPos(new Pose2d(14, 0.7, new Rotation2d(0))));

        // // Xbox A Button
        // new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 2)
        //         .onTrue(new SetWristAngle(0.66));//ArmPositions.MIDDLE_CONE.wrist));
        // new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 2)
        //         .onTrue(new SetArmAngle(0.758));
        // new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 2)
        //         .onTrue(new ArmExtensionPos(1091 / 25));

        // // Xbox B Button

        // new JoystickButton(ControlMap.DRIVER_BUTTONS, 3)
        //         .onTrue(new SetWristAngle(0.457));
        // new JoystickButton(ControlMap.DRIVER_BUTTONS, 3)
        //         .onTrue(new SetArmAngle(0.586));
        // new JoystickButton(ControlMap.DRIVER_BUTTONS, 3)
        //         .onTrue(new ArmExtensionPos(183 / 25));

        // // Xbox X  Button
        // new JoystickButton(ControlMap.DRIVER_BUTTONS, 3)
        //         .onTrue(new SetWristAngle(0.344));

        // // Xbox Y Button

        // new JoystickButton(ControlMap.DRIVER_BUTTONS, 1)
        //         .onTrue(new SetWristAngle(0.315));
        // new JoystickButton(ControlMap.DRIVER_BUTTONS, 1)
        //         .onTrue(new SetArmAngle(0.783));
        // new JoystickButton(ControlMap.DRIVER_BUTTONS, 1)
        //         .onTrue(new ArmExtensionPos(3400 / 25));


        new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 2)
                .onTrue(new MoveArmToPosition(ArmPositions.CONE_PLACE_MID));
        new JoystickButton(ControlMap.DRIVER_BUTTONS, 5)
                .onTrue(new CloseClaw());
        new JoystickButton(ControlMap.DRIVER_BUTTONS, 6)
                .onTrue(new OpenClaw());

        new JoystickButton(ControlMap.DRIVER_BUTTONS, 7)
                .whileTrue(new ArmZero());
        new JoystickButton(ControlMap.DRIVER_BUTTONS, 9)
                .onTrue(new SetArmAngle(0.65));
        new JoystickButton(ControlMap.DRIVER_BUTTONS, 10)
            .onTrue(new Grab(7));
        new JoystickButton(ControlMap.DRIVER_BUTTONS, 11)
            .onTrue(new Grab(-7));
            new JoystickButton(ControlMap.DRIVER_BUTTONS, 12)
            .onTrue(new Grab(0));

        new JoystickButton(ControlMap.DRIVER_BUTTONS, 12)
                .onTrue(new SetIntakeVoltage(Tuning.defaultConeCollectingVoltage));
// Example movearmtoposition
// new JoystickButton(ControlMap.DRIVER_BUTTONS, 0)
// .onTrue(new MoveArmToPosition(ArmPositions.TOP_CUBE));


        // new JoystickButton(ControlMap.XBOX, 4)
        // .onTrue(new SetArmAngle(0.875));
        // new JoystickButton(ControlMap.XBOX, 2).onTrue(new ArmZero());

    }
}
