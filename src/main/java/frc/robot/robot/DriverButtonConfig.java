package frc.robot.robot;

import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.arm.GoToFull;
import frc.robot.commands.swerve.SetDriverAssist;
import frc.robot.commands.swerve.ZeroGyro;
import frc.robot.subsystems.arm.ArmPositions;

public class DriverButtonConfig {
    public void initTeleop() {
        // DRIVER LEFT

        //Zero Gyro
        new JoystickButton(ControlMap.DRIVER_BUTTONS, 1)
                .onTrue(new ZeroGyro());
        //Driver Assist
        new JoystickButton(ControlMap.DRIVER_BUTTONS, 13)
                .whileTrue(new SetDriverAssist(true));
        //Stow
        // new JoystickButton(ControlMap.DRIVER_BUTTONS, 8)
        //         .onTrue(new GoToFull(ArmPositions.STORE));
        // //Intake
        // new JoystickButton(ControlMap.DRIVER_LEFT, 1)
        //         .onTrue(null);
        // //Eject
        // new JoystickButton(ControlMap.DRIVER_LEFT, 2)
        //         .onTrue(null);
        //Floor Pickup Align
        // new JoystickButton(ControlMap.DRIVER_RIGHT, 2)
        //         .onTrue(new GoToFull(ArmPositions.TIPPED_CONE_SETUP));
        // //Floor Pickup Collect
        // new JoystickButton(ControlMap.DRIVER_RIGHT, 2)
        //         .onTrue(new GoToFull(ArmPositions.TIPPED_CONE));

        //////END DRIVER 1//////////////////////////

        //////CO DRIVER////////////////

        //Substation Deploy
        // new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 1)
        //         .onTrue(null);
        // //Substation Collect
        // new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 4)
        //         .onTrue(null);
        // //Stow
        // new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 6)
        //         .onTrue(new GoToFull(ArmPositions.STORE));
        // //Cone Place High
        // new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 7)
        //         .onTrue(new GoToFull(ArmPositions.TOP_CONE));
        // // //Cube Place High
        // // new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 8)
        // //         .onTrue(null);
        // // //Cone Place Mid
        // new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 9)
        //         .onTrue(new GoToFull(ArmPositions.MIDDLE_CONE));
        // // //Cube Place Mid
        // // new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 10)
        // //         .onTrue(null);
        // // //Cone Place Low
        // new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 11)
        //         .onTrue(new GoToFull(ArmPositions.LOW));
        // //Cube Place Low
        // new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 12)
        //         .onTrue(new GoToFull(ArmPositions.LOW));
        //Arm Override Enable
        // new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 13)
        //         .whileTrue(null);

        //////END CO-DRIVER//////////////////////////
    }
}
