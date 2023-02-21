package frc.robot.subsystems.arm;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.arm.LowLevel.MoveArmExtensionToPos;
import frc.robot.commands.arm.LowLevel.MoveArmRotationToPos;
import frc.robot.commands.arm.LowLevel.MoveArmWristToPos;

public class ArmCommand {
    public Command[] commands = new Command[3];
    public ArmCommand(ArmMovement[] armMovements, ArmPositions armPositions) {
        for (int i=0; i<2; i++) {
            if (armMovements[i]==ArmMovement.angle) {
                commands[i] = new MoveArmRotationToPos(armPositions.angle);
            } else if (armMovements[i]==ArmMovement.extension) {
                commands[i] = new MoveArmExtensionToPos(armPositions.extension);
            } else if (armMovements[i]==ArmMovement.wrist) {
                commands[i] = new MoveArmWristToPos(armPositions.wrist);
            }
        }
        
    }


    
}
