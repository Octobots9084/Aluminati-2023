package frc.robot.robot;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.advanced.CollectCone;
import frc.robot.commands.advanced.CollectConeSubstation;
import frc.robot.commands.advanced.MagicButtonV1Cone;
import frc.robot.commands.advanced.MagicButtonV1Cube;
import frc.robot.commands.arm.intake.advanced.ConeInject;
import frc.robot.commands.arm.intake.advanced.CubeInject;
import frc.robot.commands.arm.intake.advanced.IntakeOutWithTimeout;
import frc.robot.commands.arm.intake.advanced.SmartEject;
import frc.robot.commands.arm.intake.basic.IntakeIn;
import frc.robot.commands.arm.intake.basic.IntakeNone;
import frc.robot.commands.arm.intake.basic.IntakeOut;
import frc.robot.commands.arm.intake.basic.SetItemMode;
import frc.robot.commands.arm.manual.ArmZero;
import frc.robot.commands.arm.manual.TiltControl;
import frc.robot.commands.arm.slow.MoveArmToPositionGoingDown;
import frc.robot.commands.arm.slow.MoveArmToPositionGoingUp;
import frc.robot.commands.arm.slow.SafeMoveArmGoingDown;
import frc.robot.commands.arm.slow.SafeMoveArmGoingUp;
import frc.robot.commands.arm.yeet.Arm2PosCollect;
import frc.robot.commands.arm.yeet.Arm2PosFull;
import frc.robot.commands.arm.yeet.Arm2PosHalfways;
import frc.robot.commands.arm.yeet.Arm2PosStow;
import frc.robot.commands.swerve.SetDriveAngle;
import frc.robot.commands.swerve.SetDriverAssist;
import frc.robot.commands.swerve.ZeroGyro;
import frc.robot.subsystems.Light;
import frc.robot.subsystems.arm.ArmPositions;

public class DriverButtonConfig {
	public void initTeleop() {
		// DRIVER LEFT
		new JoystickButton(ControlMap.DRIVER_BUTTONS, 1)
				.onTrue(new MoveArmToPositionGoingDown(ArmPositions.DRIVE_WITH_PIECE));

		new JoystickButton(ControlMap.DRIVER_BUTTONS, 4)
				.onTrue(new ZeroGyro());

		new JoystickButton(ControlMap.DRIVER_BUTTONS, 6)
				.onTrue(new Arm2PosStow(ArmPositions.DRIVE_WITHOUT_PIECE));
		new JoystickButton(ControlMap.DRIVER_BUTTONS, 7)
				.onTrue(new SetDriveAngle(0).withTimeout(1));
		new JoystickButton(ControlMap.DRIVER_BUTTONS, 8)
				.onTrue(new SetDriveAngle(180).withTimeout(1));
		new JoystickButton(ControlMap.DRIVER_BUTTONS, 9)
				.onTrue(new MagicButtonV1Cone());
		new JoystickButton(ControlMap.DRIVER_BUTTONS, 10)
				.onTrue(new MagicButtonV1Cube());
		

		new JoystickButton(ControlMap.DRIVER_BUTTONS, 13)
				.whileTrue(new SetDriverAssist(true));
		new JoystickButton(ControlMap.DRIVER_BUTTONS, 13)
				.whileFalse(new SetDriverAssist(false));

		//Driver Joystick Left
		new JoystickButton(ControlMap.DRIVER_LEFT, 1)
				.onTrue(new IntakeIn());
		new JoystickButton(ControlMap.DRIVER_LEFT, 2)
				.onTrue(new IntakeOutWithTimeout());

		//Driver Joystick Right
		new JoystickButton(ControlMap.DRIVER_RIGHT, 1)
				.onTrue(new SmartEject());
		new JoystickButton(ControlMap.DRIVER_RIGHT, 2)
				.onTrue(new CollectCone());

		////END DRIVER 1//////////////////////////

		////CO DRIVER////////////////

		new JoystickButton(ControlMap.CO_DRIVER_RIGHT, 1)
				.onTrue(new MoveArmToPositionGoingUp(ArmPositions.DRIVE_WITHOUT_PIECE));

		new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 2)
				.onTrue(new CollectCone());

		new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 1)
				.onTrue(new CollectConeSubstation());

		new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 4)
				.onTrue(new ZeroGyro());

		new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 5)
				.whileTrue(new ArmZero());

		new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 6)
				.onTrue(new Arm2PosStow(ArmPositions.DRIVE_WITHOUT_PIECE));

		new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 7)
				.onTrue(new SequentialCommandGroup(new  SetItemMode(false), new Arm2PosHalfways(ArmPositions.CUBE_PLACE_HIGH)));

		new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 8)
				.onTrue(new SequentialCommandGroup(new  SetItemMode(true), new Arm2PosFull(ArmPositions.PRE_CONE_PLACE_HIGH)));

		new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 9)
				.onTrue(new SequentialCommandGroup(new  SetItemMode(false), new Arm2PosCollect(ArmPositions.CUBE_PLACE_MID)));

		new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 10)
				.onTrue(new SequentialCommandGroup(new  SetItemMode(true), new Arm2PosHalfways(ArmPositions.PRE_CONE_PLACE_MID)));

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

		//Arm Override Enable
		new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 15)
				.whileTrue(new TiltControl());

		new JoystickButton(ControlMap.CO_DRIVER_RIGHT, 2)
				.whileTrue(new ArmZero());

		////END CO-DRIVER//////////////////////////
	}
}
