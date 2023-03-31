package frc.robot.robot;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.advanced.CollectCone;
import frc.robot.commands.advanced.CollectConeSubstation;
import frc.robot.commands.arm.intake.advanced.ConeInjectHigh;
import frc.robot.commands.arm.intake.advanced.ConeInjectMid;
import frc.robot.commands.arm.intake.advanced.CubeInjectHigh;
import frc.robot.commands.arm.intake.advanced.CubeInjectMid;
import frc.robot.commands.arm.intake.advanced.IntakeOutWithTimeout;
import frc.robot.commands.arm.intake.advanced.SmartEject;
import frc.robot.commands.arm.intake.basic.IntakeIn;
import frc.robot.commands.arm.intake.basic.SetItemMode;
import frc.robot.commands.arm.manual.ArmZero;
import frc.robot.commands.arm.slow.MoveArmToPositionGoingDown;
import frc.robot.commands.arm.slow.MoveArmToPositionGoingUp;
import frc.robot.commands.arm.yeet.Arm2PosStow;
import frc.robot.commands.autonomous.BalanceChargeStation;
import frc.robot.commands.autonomous.DriveToPosition;
import frc.robot.commands.spatula.SetSpatulaVoltageAndPos;
import frc.robot.commands.swerve.SetDriveAngle;
import frc.robot.commands.swerve.SetDriverAssist;
import frc.robot.commands.swerve.ZeroGyro;
import frc.robot.commands.vision.AutoAlign;
import frc.robot.commands.vision.AutoAlignWithID;
import frc.robot.subsystems.Light;
import frc.robot.subsystems.arm.ArmPositions;
import frc.robot.subsystems.spatula.RollingPins;
import frc.robot.subsystems.spatula.SpatulaFlip;
import frc.robot.util.PoseFinder;

public class DriverButtonConfig {
	public void initTeleop() {
		// DRIVER LEFT
		//Button 1 reserved for Hippo In
		//Button 2 reserved for Hippo Out

		//Button 3 reserved for victory dance

		new JoystickButton(ControlMap.DRIVER_BUTTONS, 6)
				.onTrue(new ZeroGyro());


		new JoystickButton(ControlMap.DRIVER_BUTTONS, 1)
				.onTrue(new BalanceChargeStation());
		
		// new JoystickButton(ControlMap.DRIVER_BUTTONS, 8).onTrue(new SetSpatulaVoltageAndPos(-12, 0));
		// new JoystickButton(ControlMap.DRIVER_BUTTONS, 10).onTrue(new SetSpatulaVoltageAndPos(0, 0.34));
		// new JoystickButton(ControlMap.DRIVER_BUTTONS, 12).onTrue(new SetSpatulaVoltageAndPos(4, 0.2).andThen(new WaitCommand(1.5)).andThen(new SetSpatulaVoltageAndPos(0, 0.34)));


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

		new JoystickButton(ControlMap.DRIVER_RIGHT, 2)
				.whileTrue(new AutoAlignWithID());

		//Driver Joystick Right
		new JoystickButton(ControlMap.DRIVER_LEFT, 1)
				.onTrue(new SetDriveAngle(0).withTimeout(1));

		new JoystickButton(ControlMap.DRIVER_LEFT, 2)
				.onTrue(new SetDriveAngle(180).withTimeout(1));

		////END DRIVER 1//////////////////////////

		////CO DRIVER////////////////

		new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 1)
				.onTrue(new CollectCone().alongWith(new SetSpatulaVoltageAndPos(0, 0.34)));

		new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 4)
				.onTrue(new SetSpatulaVoltageAndPos(-12, 0).alongWith(new Arm2PosStow(ArmPositions.STOW)));

		new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 2)
				.onTrue(new CollectConeSubstation().alongWith(new SetSpatulaVoltageAndPos(0, 0.34)));

		new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 3)
				.onTrue(new SetSpatulaVoltageAndPos(-0.5, 0.34).alongWith(new Arm2PosStow(ArmPositions.DRIVE_POSITION)));

		new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 5)
				.onTrue(new SetSpatulaVoltageAndPos(0, 0.1).alongWith(new Arm2PosStow(ArmPositions.STOW)).andThen(new WaitCommand(0.15)).andThen(new SetSpatulaVoltageAndPos(4, 0.1)).andThen(new WaitCommand(1.2)).andThen(new SetSpatulaVoltageAndPos(0, 0.34)));

		new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 6)
				.whileTrue(new ArmZero());

		new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 7)
				.onTrue(new SequentialCommandGroup(new SetItemMode(false).alongWith(new SetSpatulaVoltageAndPos(0, 0.34)), new CubeInjectHigh()));

		new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 8)
				.onTrue(new ConeInjectHigh().alongWith(new SetSpatulaVoltageAndPos(0, 0.34)));

		new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 9)
				.onTrue(new SequentialCommandGroup(new SetItemMode(false).alongWith(new SetSpatulaVoltageAndPos(0, 0.34)), new WaitCommand(0), new CubeInjectMid()));

		new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 10)
				.onTrue(new ConeInjectMid().alongWith(new SetSpatulaVoltageAndPos(0, 0.34)));

		new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 11)
				.onTrue(new MoveArmToPositionGoingUp(ArmPositions.AUTO_ALIGN));

		new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 12)
				.onTrue(new MoveArmToPositionGoingDown(ArmPositions.STOW).alongWith(new SetSpatulaVoltageAndPos(-0.5, 0.34)));

		new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 13)
				.onTrue(new InstantCommand(() -> Light.getInstance().setStrobeAnimationPurple()));

		new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 14)
				.onTrue(new InstantCommand(() -> Light.getInstance().setStrobeAnimationYellow()));

		new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 13)
				.onFalse(new InstantCommand(() -> Light.getInstance().setStrobeAnimationRed()));

		new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 14)
				.onFalse(new InstantCommand(() -> Light.getInstance().setStrobeAnimationRed()));

		//Switch 15 reserved for manual override

		//Co-Driver Joystick Left

		//Button 1 reserved for Hippo In

		//Button 2 reserved for Hippo out

		//Driver Joystick Right
		new JoystickButton(ControlMap.CO_DRIVER_RIGHT, 1)
				.onTrue(new IntakeIn());

		new JoystickButton(ControlMap.CO_DRIVER_RIGHT, 2)
				.onTrue(new IntakeOutWithTimeout());

		////END CO-DRIVER//////////////////////////
	}
}
