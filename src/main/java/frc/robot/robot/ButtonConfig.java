package frc.robot.robot;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.arm.MoveArmToPositionGoingDown;
import frc.robot.commands.arm.MoveArmToPositionGoingUp;
import frc.robot.commands.arm.Intake.IntakeIn;
import frc.robot.commands.arm.Intake.IntakeNone;
import frc.robot.commands.arm.Intake.IntakeOut;
import frc.robot.commands.arm.LowLevel.SetArmAngle;
import frc.robot.commands.arm.LowLevel.SetWristAngle;
import frc.robot.commands.arm.ManualControl.ArmZero;
import frc.robot.commands.autonomous.DriveToPosBB;
import frc.robot.commands.autonomous.PathPlannerAutos;
import frc.robot.commands.autonomous.driveToPos;
import frc.robot.commands.swerve.SetDriverAssist;
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

        new JoystickButton(ControlMap.DRIVER_BUTTONS, 7)
                .onTrue(new SafeMoveArmGoingDown(ArmPositions.CUBE_PLACE_HIGH));
        new JoystickButton(ControlMap.DRIVER_BUTTONS, 9)
                .onTrue(new SafeMoveArmGoingDown(ArmPositions.CUBE_PLACE_MID));
        new JoystickButton(ControlMap.DRIVER_BUTTONS, 11)
                .onTrue(new SafeMoveArmGoingDown(ArmPositions.CUBE_PLACE_LOW));
        
        new JoystickButton(ControlMap.DRIVER_BUTTONS, 8)
                .onTrue(new SafeMoveArmGoingUp(ArmPositions.CONE_PLACE_HIGH));
        new JoystickButton(ControlMap.DRIVER_BUTTONS, 10)
                .onTrue(new SafeMoveArmGoingUp(ArmPositions.CONE_PLACE_MID));
        new JoystickButton(ControlMap.DRIVER_BUTTONS, 12)
                .onTrue(new SafeMoveArmGoingDown(ArmPositions.CONE_PLACE_LOW));


        new JoystickButton(ControlMap.DRIVER_LEFT, 1)
                .onTrue(new IntakeIn());
        new JoystickButton(ControlMap.DRIVER_LEFT, 2)
                .onTrue(new IntakeOut());
        new JoystickButton(ControlMap.DRIVER_RIGHT, 1)
                .onTrue(new IntakeNone());
        new JoystickButton(ControlMap.DRIVER_RIGHT, 2)
                .whileTrue(new ArmZero());

        new JoystickButton(ControlMap.DRIVER_BUTTONS, 6)
                .onTrue(new driveToPos(new Pose2d(13.8,0.5,new Rotation2d(0,0))));
        new JoystickButton(ControlMap.DRIVER_BUTTONS, 13)
                .whileTrue(new SetDriverAssist(true));
        new JoystickButton(ControlMap.DRIVER_BUTTONS, 13)
                .whileFalse(new SetDriverAssist(false));
        // // Co-Driver
        // new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 7)
        //         .onTrue(new SafeMoveArm(ArmPositions.CUBE_PLACE_HIGH));
        // new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 9)
        //         .onTrue(new SafeMoveArm(ArmPositions.CUBE_PLACE_MID));
        // new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 11)
        //         .onTrue(new SafeMoveArm(ArmPositions.CUBE_PLACE_LOW));
        
        // new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 7)
        //         .onTrue(new SafeMoveArm(ArmPositions.CONE_PLACE_HIGH));
        // new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 9)
        //         .onTrue(new SafeMoveArm(ArmPositions.CONE_PLACE_MID));
        // new JoystickButton(ControlMap.CO_DRIVER_BUTTONS, 11)
        //         .onTrue(new SafeMoveArm(ArmPositions.CONE_PLACE_LOW));

        // new JoystickButton(ControlMap.CO_DRIVER_LEFT, 1)
        //         .onTrue(new IntakeIn());
        // new JoystickButton(ControlMap.CO_DRIVER_LEFT, 2)
        //         .onTrue(new IntakeOut());
        // new JoystickButton(ControlMap.CO_DRIVER_RIGHT, 1)
        //         .onTrue(new IntakeNone());

        // new JoystickButton(ControlMap.CO_DRIVER_RIGHT, 2)
        //         .onTrue(new ArmZero());







        // new JoystickButton(ControlMap.DRIVER_BUTTONS, 13)
        //         .whileTrue(new SetDriverAssist(true));

        // new JoystickButton(ControlMap.DRIVER_BUTTONS, 13)
        //         .whileFalse(new SetDriverAssist(false));
        // new JoystickButton(ControlMap.DRIVER_BUTTONS, 1)
        //         .onTrue(new MoveArmToPositionGoingUp(ArmPositions.CONE_PLACE_HIGH));
        // new JoystickButton(ControlMap.DRIVER_BUTTONS, 2)
        //         .onTrue(new SafeMoveArm(ArmPositions.CONE_PLACE_MID));
        // new JoystickButton(ControlMap.DRIVER_BUTTONS, 3)
        //         .onTrue(new MoveArmToPositionGoingDown(ArmPositions.FLOOR_INTAKE_CONE));
        // new JoystickButton(ControlMap.DRIVER_BUTTONS, 4)
        //         .onTrue(new MoveArmToPositionGoingUp(ArmPositions.DRIVE_WITH_PIECE));

        // new JoystickButton(ControlMap.DRIVER_BUTTONS, 7)
        //         .whileTrue(new ArmZero());
        // new JoystickButton(ControlMap.DRIVER_BUTTONS, 9)
        //         .onTrue(new SetArmAngle(0.65));
        // new JoystickButton(ControlMap.DRIVER_BUTTONS, 5)
        //         .onTrue(new IntakeIn());
        // new JoystickButton(ControlMap.DRIVER_BUTTONS, 6)
        //         .onTrue(new IntakeOut());
        // new JoystickButton(ControlMap.DRIVER_BUTTONS, 8)
        //         .onTrue(new IntakeNone());
        // new JoystickButton(ControlMap.DRIVER_RIGHT, 2)
        //         .onTrue(new CollectCone());
        // new JoystickButton(ControlMap.CO_DRIVER_RIGHT, 2)
        //         .onTrue(new CollectCone());


    }
}
