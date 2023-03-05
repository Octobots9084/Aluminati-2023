package frc.robot.robot;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.advanced.MagicButtonV1Cone;
import frc.robot.commands.advanced.MagicButtonV1Cube;
import frc.robot.commands.arm.intake.advanced.ConeInject;
import frc.robot.commands.arm.intake.advanced.IntakeOutWithTimeout;
import frc.robot.commands.arm.intake.basic.IntakeIn;
import frc.robot.commands.arm.intake.basic.IntakeNone;
import frc.robot.commands.arm.intake.basic.IntakeOut;
import frc.robot.commands.arm.manual.ArmZero;
import frc.robot.commands.arm.manual.TiltControl;
import frc.robot.commands.arm.slow.MoveArmToPositionGoingDown;
import frc.robot.commands.arm.slow.MoveArmToPositionGoingUp;
import frc.robot.commands.arm.slow.SafeMoveArmGoingDown;
import frc.robot.commands.arm.slow.SafeMoveArmGoingUp;
import frc.robot.commands.autonomous.BalanceChargeStation;
import frc.robot.commands.autonomous.driveToPos;
import frc.robot.commands.autonomous.arm.AutoConeTop;
import frc.robot.commands.lights.SetLightMode;
import frc.robot.commands.swerve.SetDriverAssist;
import frc.robot.commands.swerve.ZeroGyro;
import frc.robot.subsystems.Light;
import frc.robot.subsystems.arm.ArmPositions;
import frc.robot.subsystems.swerve.DriveTrain;
import frc.robot.util.Gyro;

public class ButtonConfig {
	public void initTeleop() {
		// DRIVER LEFT
		new JoystickButton(ControlMap.DRIVER_BUTTONS, 1)
				.onTrue(new MoveArmToPositionGoingDown(ArmPositions.DRIVE_WITH_PIECE));

		new JoystickButton(ControlMap.DRIVER_BUTTONS, 4)
				.onTrue(new ZeroGyro());
				new JoystickButton(ControlMap.DRIVER_BUTTONS, 10)
				.onTrue(new MagicButtonV1Cube());

		new JoystickButton(ControlMap.DRIVER_BUTTONS, 6)
				.onTrue(new MoveArmToPositionGoingDown(ArmPositions.DRIVE_WITHOUT_PIECE));

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
				.onTrue(new ConeInject());
		new JoystickButton(ControlMap.DRIVER_RIGHT, 2)
				.onTrue(new MoveArmToPositionGoingUp(ArmPositions.CONE_INTAKE_GROUND));

		////END DRIVER 1//////////////////////////

		////CO DRIVER////////////////

		new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 1)
				.onTrue(new MoveArmToPositionGoingDown(ArmPositions.DRIVE_WITH_PIECE));

		new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 2)
				.onTrue(new MoveArmToPositionGoingDown(ArmPositions.CONE_INTAKE_GROUND));

		new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 3)
				.onTrue(new MoveArmToPositionGoingDown(ArmPositions.INTAKE_SUBSTATION));

		// new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 4)
		// 		.onTrue(new ZeroGyro());


		new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 6)
				.onTrue(new MoveArmToPositionGoingDown(ArmPositions.DRIVE_WITHOUT_PIECE));

		new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 7)
				.onTrue(new MoveArmToPositionGoingUp(ArmPositions.CUBE_PLACE_HIGH));

		new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 8)
				.onTrue(new MoveArmToPositionGoingUp(ArmPositions.PRE_CONE_PLACE_HIGH));

		new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 9)
				.onTrue(new MoveArmToPositionGoingUp(ArmPositions.CUBE_PLACE_MID));

		new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 10)
				.onTrue(new MoveArmToPositionGoingUp(ArmPositions.PRE_CONE_PLACE_MID));

		new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 11)
				.onTrue(new IntakeOut());

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

	
		new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 4)
				.onTrue(new driveToPos(new Pose2d(14.126, 2.36, new Rotation2d())));
	}
}
