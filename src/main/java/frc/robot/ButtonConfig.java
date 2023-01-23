package frc.robot;

import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.commands.SetDriverAssist;

public class ButtonConfig {
    public void initTeleop() {
        // DRIVER LEFT

        // new JoystickButton(ControlMap.DRIVER_LEFT, 1).whileTrue(new
        // TurnToTrackedTarget());
        new JoystickButton(ControlMap.DRIVER_LEFT, 1).whenPressed(new TurnToTrackedTargetWithID());

        new JoystickButton(ControlMap.DRIVER_LEFT, 2).whenPressed(new GoTowardsTargetWithID());

        new JoystickButton(ControlMap.DRIVER_BUTTONS, 13)
            .whileTrue(new SetDriverAssist(true));
        
        new JoystickButton(ControlMap.DRIVER_BUTTONS, 13)
            .whileFalse(new SetDriverAssist(false));

        new JoystickButton(ControlMap.DRIVER_LEFT, 1)
            .onTrue(new RotateTo(0));

        new JoystickButton(ControlMap.DRIVER_BUTTONS, 11)
            .onTrue(new ZeroGyro());
    }
}
