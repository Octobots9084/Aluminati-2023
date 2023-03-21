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
import frc.robot.commands.arm.yeet.Arm2PosFull;
import frc.robot.commands.arm.yeet.Arm2PosHalfways;
import frc.robot.commands.arm.yeet.Arm2PosStow;
import frc.robot.commands.autonomous.BalanceChargeStation;
import frc.robot.commands.autonomous.DriveToPosition;
import frc.robot.commands.swerve.SetDriveAngle;
import frc.robot.commands.swerve.SetDriverAssist;
import frc.robot.commands.swerve.ZeroGyro;
import frc.robot.commands.vision.GoTowardsTarget;
import frc.robot.subsystems.Light;
import frc.robot.subsystems.arm.ArmPositions;
import frc.robot.util.PoseFinder;

public class ButtonConfig {
	public void initTeleop() {
		// DRIVER LEFT
		new JoystickButton(ControlMap.DRIVER_BUTTONS, 1)
				.onTrue(new Arm2PosStow(ArmPositions.DRIVE_WITHOUT_PIECE));

		//Button 2 reserved for substation auto drive

		new JoystickButton(ControlMap.DRIVER_BUTTONS, 3)
				.onTrue(new SmartEject());

		new JoystickButton(ControlMap.DRIVER_BUTTONS, 4)
				.onTrue(new SetDriveAngle(0).withTimeout(1));
		
		new JoystickButton(ControlMap.DRIVER_BUTTONS, 5)
				.onTrue(new SetDriveAngle(180).withTimeout(1));

		new JoystickButton(ControlMap.DRIVER_BUTTONS, 6)
				.onTrue(new ZeroGyro());

		//buttons 8-12 reserved for AutoAlign
		new JoystickButton(ControlMap.DRIVER_BUTTONS, 7)
				.onTrue(new SequentialCommandGroup(
					new InstantCommand(() -> ControlMap.DRIVER_BUTTONS.setOutput(1, true)), 
					new InstantCommand(() -> ControlMap.DRIVER_BUTTONS.setOutput(3, false)), 
					new InstantCommand(() -> ControlMap.DRIVER_BUTTONS.setOutput(5, false)),
					new DriveToPosition(PoseFinder.getPositionFromID(PoseFinder.getGrid(), 1))));

		new JoystickButton(ControlMap.DRIVER_BUTTONS, 8)
				.onTrue(new SequentialCommandGroup(
					new InstantCommand(() -> PoseFinder.setGrid(1)), 
					new InstantCommand(() -> ControlMap.DRIVER_BUTTONS.setOutput(2, true)), 
					new InstantCommand(() -> ControlMap.DRIVER_BUTTONS.setOutput(4, false)), 
					new InstantCommand(() -> ControlMap.DRIVER_BUTTONS.setOutput(6, false))));

				
		new JoystickButton(ControlMap.DRIVER_BUTTONS, 9)
				.onTrue(new SequentialCommandGroup(
					new InstantCommand(() -> ControlMap.DRIVER_BUTTONS.setOutput(1, false)), 
					new InstantCommand(() -> ControlMap.DRIVER_BUTTONS.setOutput(3, true)), 
					new InstantCommand(() -> ControlMap.DRIVER_BUTTONS.setOutput(5, false)),
					new DriveToPosition(PoseFinder.getPositionFromID(PoseFinder.getGrid(), 2))));
		
		new JoystickButton(ControlMap.DRIVER_BUTTONS, 10)
				.onTrue(new SequentialCommandGroup(
					new InstantCommand(() -> PoseFinder.setGrid(2)), 
					new InstantCommand(() -> ControlMap.DRIVER_BUTTONS.setOutput(2, false)), 
					new InstantCommand(() -> ControlMap.DRIVER_BUTTONS.setOutput(4, true)), 
					new InstantCommand(() -> ControlMap.DRIVER_BUTTONS.setOutput(6, false))));
			
				
		new JoystickButton(ControlMap.DRIVER_BUTTONS, 11)
				.onTrue(new SequentialCommandGroup(
					new InstantCommand(() -> ControlMap.DRIVER_BUTTONS.setOutput(1, false)), 
					new InstantCommand(() -> ControlMap.DRIVER_BUTTONS.setOutput(3, false)), 
					new InstantCommand(() -> ControlMap.DRIVER_BUTTONS.setOutput(5, true)),
					new DriveToPosition(PoseFinder.getPositionFromID(PoseFinder.getGrid(), 3))));

		new JoystickButton(ControlMap.DRIVER_BUTTONS, 12)
				.onTrue(new SequentialCommandGroup(
					new InstantCommand(() -> PoseFinder.setGrid(3)), 
					new InstantCommand(() -> ControlMap.DRIVER_BUTTONS.setOutput(2, false)), 
					new InstantCommand(() -> ControlMap.DRIVER_BUTTONS.setOutput(4, false)), 
					new InstantCommand(() -> ControlMap.DRIVER_BUTTONS.setOutput(6, true))));

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
				.onTrue(new IntakeIn());

		new JoystickButton(ControlMap.DRIVER_LEFT, 2)
				.onTrue(new IntakeOutWithTimeout());

		//Driver Joystick Right
		new JoystickButton(ControlMap.DRIVER_RIGHT, 1)
				.onTrue(new Arm2PosStow(ArmPositions.DRIVE_WITH_PIECE));

		new JoystickButton(ControlMap.DRIVER_RIGHT, 2)
				.onTrue(new CollectCone());

		////END DRIVER 1//////////////////////////

		////CO DRIVER////////////////

		new JoystickButton(ControlMap.CO_DRIVER_RIGHT, 1)
				.onTrue(new CollectCone());

		new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 2)
				.onTrue(new CollectConeSubstation());

		new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 3)
				.onTrue(new Arm2PosStow(ArmPositions.DRIVE_WITH_PIECE));

		new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 4)
				.onTrue(new Arm2PosStow(ArmPositions.DRIVE_WITHOUT_PIECE));

		new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 6)
				.whileTrue(new GoTowardsTarget());

		new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 7)
				.onTrue(new SequentialCommandGroup(new  SetItemMode(false), new Arm2PosHalfways(ArmPositions.CUBE_PLACE_HIGH)));

		new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 8)
				.onTrue(new SequentialCommandGroup(new  SetItemMode(true), new Arm2PosFull(ArmPositions.PRE_CONE_PLACE_HIGH)));

		new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 9)
				.onTrue(new SequentialCommandGroup(new  SetItemMode(false), new Arm2PosHalfways(ArmPositions.CUBE_PLACE_MID)));

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

		////END CO-DRIVER//////////////////////////
	}
}
