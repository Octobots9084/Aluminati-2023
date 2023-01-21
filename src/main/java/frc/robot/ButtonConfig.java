package frc.robot;

import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.commands.SetDriverAssist;

public class ButtonConfig {
    public void initTeleop() {
        // DRIVER LEFT

        new JoystickButton(ControlMap.DRIVER_BUTTONS, 13)
            .onTrue(new SetDriverAssist(true));
        
        new JoystickButton(ControlMap.DRIVER_BUTTONS, 13)
            .onFalse(new SetDriverAssist(false));
    }
}
