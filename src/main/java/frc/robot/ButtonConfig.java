package frc.robot;

import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.commands.SetDriverAssist;
import frc.commands.TurnToTrackedTarget;
import frc.commands.TurnToTrackedTargetWithID;

public class ButtonConfig {
    public void initTeleop() {
        // DRIVER LEFT

        // new JoystickButton(ControlMap.DRIVER_LEFT, 1).whileTrue(new
        // TurnToTrackedTarget());
        new JoystickButton(ControlMap.DRIVER_LEFT, 1).whenPressed(new TurnToTrackedTarget());

        new JoystickButton(ControlMap.DRIVER_LEFT, 2).whenPressed(new TurnToTrackedTargetWithID());

        new JoystickButton(ControlMap.DRIVER_BUTTONS, 13)
            .onTrue(new SetDriverAssist(true));
        
        new JoystickButton(ControlMap.DRIVER_BUTTONS, 13)
            .onFalse(new SetDriverAssist(false));
    }
}
