package frc.robot.robot;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.arm.MoveArmToPositionGoingDown;
import frc.robot.commands.arm.MoveArmToPositionGoingUp;
import frc.robot.commands.arm.ManualControl.TiltControl;
import frc.robot.commands.autonomous.driveToPos;
import frc.robot.commands.arm.Intake.IntakeIn;
import frc.robot.commands.arm.Intake.IntakeNone;
import frc.robot.commands.arm.Intake.IntakeOut;
import frc.robot.commands.arm.LowLevel.SetArmAngle;
import frc.robot.commands.arm.LowLevel.SetWristAngle;
import frc.robot.commands.arm.ManualControl.ArmZero;
import frc.robot.commands.swerve.SetDriverAssist;
import frc.robot.commands.swerve.ZeroGyro;
import frc.robot.subsystems.arm.ArmPositions;
import frc.robot.commands.advanced.CollectCone;
import frc.robot.commands.advanced.SafeMoveArmGoingDown;
import frc.robot.commands.advanced.SafeMoveArmGoingUp;

public class ButtonConfig {
    public void initTeleop() {
        // DRIVER LEFT
        new JoystickButton(ControlMap.DRIVER_BUTTONS, 1)
        .onTrue(new SafeMoveArmGoingDown(ArmPositions.DRIVE_WITH_PIECE));
new JoystickButton(ControlMap.DRIVER_BUTTONS, 2)
        .onTrue(new SafeMoveArmGoingDown(ArmPositions.CONE_INTAKE_GROUND));
new JoystickButton(ControlMap.DRIVER_BUTTONS, 3)
        .onTrue(new SafeMoveArmGoingDown(ArmPositions.INTAKE_SUBSTATION));

new JoystickButton(ControlMap.DRIVER_BUTTONS, 7)
        .onTrue(new SafeMoveArmGoingUp(ArmPositions.CUBE_PLACE_HIGH));
new JoystickButton(ControlMap.DRIVER_BUTTONS, 9)
        .onTrue(new SafeMoveArmGoingUp(ArmPositions.CUBE_PLACE_MID));
new JoystickButton(ControlMap.DRIVER_BUTTONS, 11)
        .onTrue(new SafeMoveArmGoingDown(ArmPositions.CUBE_PLACE_LOW));

new JoystickButton(ControlMap.DRIVER_BUTTONS, 8)
        .onTrue(new SafeMoveArmGoingUp(ArmPositions.CONE_PLACE_HIGH));
new JoystickButton(ControlMap.DRIVER_BUTTONS, 10)
        .onTrue(new SafeMoveArmGoingUp(ArmPositions.CONE_PLACE_MID));
new JoystickButton(ControlMap.DRIVER_BUTTONS, 12)
        .onTrue(new SafeMoveArmGoingDown(ArmPositions.CONE_PLACE_LOW));
new JoystickButton(ControlMap.DRIVER_BUTTONS, 4)
        .onTrue(new ZeroGyro());
new JoystickButton(ControlMap.DRIVER_BUTTONS, 5)
        .whileTrue(new ArmZero());

new JoystickButton(ControlMap.DRIVER_LEFT, 1)
        .onTrue(new IntakeIn());
new JoystickButton(ControlMap.DRIVER_LEFT, 2)
        .onTrue(new IntakeOut());
new JoystickButton(ControlMap.DRIVER_RIGHT, 1)
        .onTrue(new IntakeNone());
new JoystickButton(ControlMap.DRIVER_BUTTONS, 6)
        .onTrue(new SafeMoveArmGoingDown(ArmPositions.DRIVE_WITHOUT_PIECE));

new JoystickButton(ControlMap.DRIVER_BUTTONS, 13)
        .whileTrue(new SetDriverAssist(true));
new JoystickButton(ControlMap.DRIVER_BUTTONS, 13)
        .whileFalse(new SetDriverAssist(false));

////END DRIVER 1//////////////////////////

////CO DRIVER////////////////

//new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 1)
         //.onTrue(null);
//new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 4)
         //.onTrue(null);
new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 1)
         .onTrue(new SafeMoveArmGoingDown(ArmPositions.DRIVE_WITH_PIECE));
 new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 2)
         .onTrue(new SafeMoveArmGoingDown(ArmPositions.CONE_INTAKE_GROUND));
new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 3)
        .onTrue(new SafeMoveArmGoingDown(ArmPositions.INTAKE_SUBSTATION));

new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 5)
         .whileTrue(new ArmZero());
new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 7)
         .onTrue(new SafeMoveArmGoingUp(ArmPositions.CUBE_PLACE_HIGH));
new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 8)
          .onTrue(new SafeMoveArmGoingUp(ArmPositions.CONE_PLACE_HIGH));
new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 9)
         .onTrue(new SafeMoveArmGoingUp(ArmPositions.CUBE_PLACE_MID));
new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 10)
        .onTrue(new SafeMoveArmGoingUp(ArmPositions.CONE_PLACE_MID));
new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 11)
         .onTrue(new SafeMoveArmGoingDown(ArmPositions.CUBE_PLACE_LOW));
 //Cube Place Low
new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 12)
         .onTrue(new SafeMoveArmGoingDown(ArmPositions.CONE_PLACE_MID));
//Arm Override Enable
new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 15)
         .whileTrue(new TiltControl());
new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 4)
        .onTrue(new ZeroGyro());



////END CO-DRIVER//////////////////////////



new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 6)
        .onTrue(new driveToPos(new Pose2d(14.2, 0.5, new Rotation2d(0,0))));


    }
}
