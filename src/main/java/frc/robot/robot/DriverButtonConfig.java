package frc.robot.robot;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.advanced.CollectFloor;
import frc.robot.commands.advanced.CollectSubstation;
import frc.robot.commands.advanced.ConeInjectHigh;
import frc.robot.commands.advanced.ConeInjectMid;
import frc.robot.commands.advanced.CubeInjectHigh;
import frc.robot.commands.advanced.CubeInjectMid;
import frc.robot.commands.arm.basic.SetItemMode;
import frc.robot.commands.arm.basic.instant.ArmPosInstant;
import frc.robot.commands.arm.basic.instant.IntakeSpeedInstant;
import frc.robot.commands.arm.manual.ArmZero;
import frc.robot.commands.arm.yeet.Arm2PosStow;
import frc.robot.commands.autonomous.BalanceChargeStation;
import frc.robot.commands.spatula.SetSpatulaVoltageAndPos;
import frc.robot.commands.swerve.SetDriveAngle;
import frc.robot.commands.swerve.SetDriverAssist;
import frc.robot.commands.swerve.ZeroGyro;
import frc.robot.commands.vision.AutoAlign;
import frc.robot.subsystems.Light;
import frc.robot.subsystems.arm.ArmPositions;

public class DriverButtonConfig {
	public void initTeleop() {
		// DRIVER LEFT
		//Button 1 reserved for Hippo In
		//Button 2 reserved for Hippo Out

		//Button 3 reserved for victory dance


		//new JoystickButton(ControlMap.DRIVER_BUTTONS, 5).onTrue(new InstantCommand(() -> Light.getInstance().cycleMode()));
		//new JoystickButton(ControlMap.DRIVER_BUTTONS, 15).onTrue(new InstantCommand(() -> Light.getInstance().AdrUpdateStrobe(255, 255, 255, 0)));

		new JoystickButton(ControlMap.DRIVER_BUTTONS, 6)
				.onTrue(new ZeroGyro());


		new JoystickButton(ControlMap.DRIVER_BUTTONS, 1)
				.onTrue(new BalanceChargeStation());

		new JoystickButton(ControlMap.DRIVER_BUTTONS, 3)
				.onTrue(new InstantCommand(() -> Light.getInstance().setRainbow()));

		
		// new JoystickButton(ControlMap.DRIVER_BUTTONS, 8).onTrue(new SetSpatulaVoltageAndPos(-12, 0));
		// new JoystickButton(ControlMap.DRIVER_BUTTONS, 10).onTrue(new SetSpatulaVoltageAndPos(0, 0.45));
		// new JoystickButton(ControlMap.DRIVER_BUTTONS, 12).onTrue(new SetSpatulaVoltageAndPos(4, 0.2).andThen(new WaitCommand(1.5)).andThen(new SetSpatulaVoltageAndPos(0, 0.45)));


		new JoystickButton(ControlMap.DRIVER_BUTTONS, 13)
				.whileTrue(new SetDriverAssist(true));

		new JoystickButton(ControlMap.DRIVER_BUTTONS, 13)
				.whileFalse(new SetDriverAssist(false));

		//switch 14 reserved for feild-centric toggle, testing to be moved to codriver

		new JoystickButton(ControlMap.DRIVER_BUTTONS, 15)
				.whileTrue(new BalanceChargeStation());

		//Switch 16 reserved for auto align toggle

		//Driver Joystick Left
		new JoystickButton(ControlMap.DRIVER_RIGHT, 1)
				.whileTrue(new AutoAlign());


		//Driver Joystick Right
		new JoystickButton(ControlMap.DRIVER_LEFT, 1)
				.onTrue(new SetDriveAngle(0).withTimeout(1));

		new JoystickButton(ControlMap.DRIVER_LEFT, 2)
				.onTrue(new SetDriveAngle(180).withTimeout(1));

		////END DRIVER 1//////////////////////////

		////CO DRIVER////////////////

		new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 1)
				.onTrue(new CollectFloor().alongWith(new SetSpatulaVoltageAndPos(0, 0.45)));

		new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 4)
				.onTrue(new SetSpatulaVoltageAndPos(-12, .12).alongWith(new Arm2PosStow(ArmPositions.STOW)).andThen(new WaitCommand(0.2)).andThen(new SetSpatulaVoltageAndPos(-8, .12)));

		new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 2)
				.onTrue(new CollectSubstation().alongWith(new SetSpatulaVoltageAndPos(0, 0.45)));

		new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 3)
				.onTrue(new SetSpatulaVoltageAndPos(-0.5, 0.45).andThen(new Arm2PosStow(ArmPositions.DRIVE_POSITION)));

		new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 5)
				.onTrue(new SetSpatulaVoltageAndPos(0, 0.12).alongWith(new WaitCommand(0.12)).andThen(new SetSpatulaVoltageAndPos(4, 0.12)).andThen(new WaitCommand(0.3)).andThen(new SetSpatulaVoltageAndPos(0, 0.45)));

		new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 6)
				.whileTrue(new ArmZero());

		new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 7)
				.onTrue(new SequentialCommandGroup(new SetItemMode(false).alongWith(new SetSpatulaVoltageAndPos(0, 0.45)), new CubeInjectHigh()));

		new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 8)
				.onTrue(new ConeInjectHigh().alongWith(new SetSpatulaVoltageAndPos(0, 0.45)));

		new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 9)
				.onTrue(new SequentialCommandGroup(new SetItemMode(false).alongWith(new SetSpatulaVoltageAndPos(0, 0.45)), new CubeInjectMid()));

		new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 10)
				.onTrue(new ConeInjectMid().alongWith(new SetSpatulaVoltageAndPos(0, 0.45)));

		new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 11)
				.onTrue(new ArmPosInstant(ArmPositions.AUTO_ALIGN));

		new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 12)
				.onTrue(new ArmPosInstant(ArmPositions.STOW).alongWith(new SetSpatulaVoltageAndPos(-0.5, 0.45)));

		new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 13)
				.onTrue(new InstantCommand(() -> Light.getInstance().AdrUpdateStrobe(200, 0, 255, 0.5)));

		new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 14)
				.onTrue(new InstantCommand(() -> Light.getInstance().AdrUpdateStrobe(255, 255, 0, 0.5)));

				
		new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 13)
			.onFalse(new InstantCommand(() -> Light.getInstance().AdrUpdateStrobe(0, 0, 255, 1)));

		new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 14)
			.onFalse(new InstantCommand(() -> Light.getInstance().AdrUpdateStrobe(0, 0, 255, 1)));

		//Switch 15 reserved for manual override

		//Co-Driver Joystick Left

		//Button 1 reserved for Hippo In

		//Button 2 reserved for Hippo out

		//Driver Joystick Right
		new JoystickButton(ControlMap.CO_DRIVER_RIGHT, 1)
				.onTrue(new IntakeSpeedInstant(-10));

		new JoystickButton(ControlMap.CO_DRIVER_RIGHT, 2)
				.onTrue(new SequentialCommandGroup(new IntakeSpeedInstant(3), new WaitCommand(2), new IntakeSpeedInstant(0)));

		////END CO-DRIVER//////////////////////////
	}
}
