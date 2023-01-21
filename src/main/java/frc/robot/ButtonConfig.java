package frc.robot;

import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.RotateTo;
import frc.robot.commands.SetDriverAssist;
import frc.robot.commands.ZeroGyro;

public class ButtonConfig {
    public void initTeleop() {
        // DRIVER LEFT

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
