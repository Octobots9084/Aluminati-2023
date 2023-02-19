package frc.robot.robot;

import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.arm.ArmZero;
import frc.robot.commands.arm.Grab;
import frc.robot.commands.arm.MoveArmToPositionGoingDown;
import frc.robot.commands.arm.MoveArmToPositionGoingUp;
import frc.robot.commands.arm.SetArmAngle;
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
        new JoystickButton(ControlMap.DRIVER_BUTTONS, 1)
                .onTrue(new MoveArmToPositionGoingUp(ArmPositions.CONE_PLACE_HIGH));
        new JoystickButton(ControlMap.DRIVER_BUTTONS, 2)
                .onTrue(new MoveArmToPositionGoingUp(ArmPositions.CONE_PLACE_MID));
        new JoystickButton(ControlMap.DRIVER_BUTTONS, 3)
                .onTrue(new MoveArmToPositionGoingDown(ArmPositions.FLOOR_INTAKE_CONE));
        new JoystickButton(ControlMap.DRIVER_BUTTONS, 4)
                .onTrue(new MoveArmToPositionGoingUp(ArmPositions.DRIVE_WITH_PIECE));

        new JoystickButton(ControlMap.DRIVER_BUTTONS, 7)
                .whileTrue(new ArmZero());
        new JoystickButton(ControlMap.DRIVER_BUTTONS, 9)
                .onTrue(new SetArmAngle(0.65));
        new JoystickButton(ControlMap.DRIVER_BUTTONS, 5)
                .onTrue(new Grab(10));
        new JoystickButton(ControlMap.DRIVER_BUTTONS, 6)
                .onTrue(new Grab(-10));
        new JoystickButton(ControlMap.DRIVER_BUTTONS, 8)
                .onTrue(new Grab(0));


    }
}
