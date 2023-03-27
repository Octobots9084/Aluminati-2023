package frc.robot.robot;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.advanced.CollectCone;
import frc.robot.commands.advanced.CollectConeSubstation;
import frc.robot.commands.arm.intake.advanced.ConeInject;
import frc.robot.commands.arm.intake.advanced.CubeInject;
import frc.robot.commands.arm.intake.advanced.IntakeOutWithTimeout;
import frc.robot.commands.arm.intake.advanced.SmartEject;
import frc.robot.commands.arm.intake.basic.IntakeIn;
import frc.robot.commands.arm.intake.basic.SetItemMode;
import frc.robot.commands.arm.manual.ArmZero;
import frc.robot.commands.arm.slow.MoveArmToPositionGoingUp;
import frc.robot.commands.arm.yeet.Arm2PosStow;
import frc.robot.commands.autonomous.BalanceChargeStation;
import frc.robot.commands.autonomous.DriveToPosition;
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

		new JoystickButton(ControlMap.DRIVER_BUTTONS, 2)
		.onTrue(new InstantCommand(() -> RollingPins.getInstance().setRollingPinVoltage(-5)));

		new JoystickButton(ControlMap.DRIVER_BUTTONS, 3)
		.onTrue(new InstantCommand(() -> RollingPins.getInstance().setRollingPinVoltage(3)));

		new JoystickButton(ControlMap.DRIVER_BUTTONS, 4)
				.onTrue(new IntakeIn());

		new JoystickButton(ControlMap.DRIVER_BUTTONS, 5)
				.onTrue(new IntakeOutWithTimeout());

		new JoystickButton(ControlMap.DRIVER_BUTTONS, 6)
				.onTrue(new ZeroGyro());

		//buttons 8-12 reserved for AutoAlign
		new JoystickButton(ControlMap.DRIVER_BUTTONS, 7).onTrue(new CollectCone());
		//Button 8 Reserved for Hippo Intake
		new JoystickButton(ControlMap.DRIVER_BUTTONS, 9).onTrue(new Arm2PosStow(ArmPositions.DRIVE_WITHOUT_PIECE));

		// new JoystickButton(ControlMap.DRIVER_BUTTONS, 11).onTrue(new Arm2PosStow(ArmPositions.DRIVE_WITH_PIECE));

		new JoystickButton(ControlMap.DRIVER_BUTTONS, 13)
				.whileTrue(new SetDriverAssist(true));

		new JoystickButton(ControlMap.DRIVER_BUTTONS, 13)
				.whileFalse(new SetDriverAssist(false));

		//switch 14 reserved for feild-centric toggle, testing to be moved to codriver

		new JoystickButton(ControlMap.DRIVER_BUTTONS, 15)
				.whileTrue(new BalanceChargeStation());

		//Switch 16 reserved for auto align toggle

		//Driver Joystick Left
		new JoystickButton(ControlMap.DRIVER_LEFT, 1)
				.whileTrue(new AutoAlign());

		new JoystickButton(ControlMap.DRIVER_LEFT, 2)
				.whileTrue(new AutoAlignWithID());

		//Driver Joystick Right
		new JoystickButton(ControlMap.DRIVER_RIGHT, 1)
				.onTrue(new SetDriveAngle(0).withTimeout(1));

		new JoystickButton(ControlMap.DRIVER_RIGHT, 2)
				.onTrue(new SetDriveAngle(180).withTimeout(1));

		////END DRIVER 1//////////////////////////

		////CO DRIVER////////////////

		new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 1)
				.onTrue(new CollectCone());

		// Button 2 reserved for Hippo Intake

		new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 3)
				.onTrue(new CollectConeSubstation());

		new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 4)
				.onTrue(new Arm2PosStow(ArmPositions.DRIVE_WITHOUT_PIECE));

		new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 5)
				.onTrue(new Arm2PosStow(ArmPositions.DRIVE_WITH_PIECE));

		new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 6)
				.whileTrue(new ArmZero());

		new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 7)
				.onTrue(new SequentialCommandGroup(new SetItemMode(false),
						new MoveArmToPositionGoingUp(ArmPositions.CUBE_PLACE_HIGH)));

		new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 8)
				.onTrue(new SequentialCommandGroup(new SetItemMode(true),
						new MoveArmToPositionGoingUp(ArmPositions.PRE_CONE_PLACE_HIGH)));

		new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 9)
				.onTrue(new SequentialCommandGroup(new SetItemMode(false),
						new MoveArmToPositionGoingUp(ArmPositions.CONE_PLACE_MID)));

		new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 10)
				.onTrue(new SequentialCommandGroup(new SetItemMode(true),
						new MoveArmToPositionGoingUp(ArmPositions.PRE_CONE_PLACE_MID)));

		new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 11)
				.onTrue(new CubeInject());

		new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 12)
				.onTrue(new ConeInject());

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
