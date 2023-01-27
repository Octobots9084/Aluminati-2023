package frc.robot.robot;

import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.autonomous.BalanceChargeStation;
import frc.robot.commands.swerve.SetDriverAssist;
import frc.robot.commands.swerve.ZeroGyro;
import frc.robot.commands.vision.RotateTo;
import frc.robot.subsystems.swerve.DriveTrain;
import frc.robot.util.Gyro;

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

    }
}
 