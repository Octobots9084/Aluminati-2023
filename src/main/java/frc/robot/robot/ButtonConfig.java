package frc.robot.robot;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.advanced.CollectFloor;
import frc.robot.commands.advanced.CollectSubstation;
import frc.robot.commands.advanced.ConeInjectHigh;
import frc.robot.commands.advanced.CubeInjectMid;
import frc.robot.commands.arm.basic.SetItemMode;
import frc.robot.commands.arm.basic.instant.IntakeSpeedInstant;
import frc.robot.commands.arm.manual.ArmZero;
import frc.robot.commands.arm.slow.MoveArmToPositionGoingUp;
import frc.robot.commands.arm.yeet.Arm2PosStow;
import frc.robot.commands.autonomous.BalanceChargeStation;
import frc.robot.commands.swerve.SetDriveAngle;
import frc.robot.commands.swerve.SetDriverAssist;
import frc.robot.commands.swerve.ZeroGyro;
import frc.robot.commands.vision.AutoAlign;
import frc.robot.commands.vision.AutoAlignWithID;
import frc.robot.subsystems.arm.ArmPositions;

public class ButtonConfig {
	public void initTeleop() {
		// DRIVER LEFT
		//Button 1 reserved for Hippo In
		//Button 2 reserved for Hippo Out

		//Button 3 reserved for victory dance

		// new JoystickButton(ControlMap.DRIVER_BUTTONS, 3)
		// .onTrue(new SmartEject());

		new JoystickButton(ControlMap.DRIVER_BUTTONS, 4)
				.onTrue(new IntakeSpeedInstant(-10));

		new JoystickButton(ControlMap.DRIVER_BUTTONS, 5)
				.onTrue(new SequentialCommandGroup(new IntakeSpeedInstant(3), new WaitCommand(2), new IntakeSpeedInstant(0)));

		new JoystickButton(ControlMap.DRIVER_BUTTONS, 6)
				.onTrue(new ZeroGyro());

		//buttons 8-12 reserved for AutoAlign
		new JoystickButton(ControlMap.DRIVER_BUTTONS, 7).onTrue(new CollectFloor());
		//Button 8 Reserved for Hippo Intake
		new JoystickButton(ControlMap.DRIVER_BUTTONS, 9).onTrue(new Arm2PosStow(ArmPositions.STOW));

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
				.onTrue(new CollectFloor());

		// Button 2 reserved for Hippo Intake

		new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 3)
				.onTrue(new CollectSubstation());

		new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 4)
				.onTrue(new Arm2PosStow(ArmPositions.STOW));

		new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 5)
				.onTrue(new Arm2PosStow(ArmPositions.STOW));

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
						new MoveArmToPositionGoingUp(ArmPositions.DEPRECIATED_CONE_PLACE_MID)));

		new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 10)
				.onTrue(new SequentialCommandGroup(new SetItemMode(true),
						new MoveArmToPositionGoingUp(ArmPositions.PRE_CONE_PLACE_MID)));

		new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 11)
				.onTrue(new CubeInjectMid());

		new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 12)
				.onTrue(new ConeInjectHigh());

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
