package frc.robot.commands.autonomous.arm;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.arm.LowLevel.MoveArmExtensionToPos;
import frc.robot.commands.arm.LowLevel.MoveArmRotationToPos;
import frc.robot.commands.arm.LowLevel.MoveArmWristToPos;
import frc.robot.subsystems.arm.ArmExtension;
import frc.robot.subsystems.arm.ArmPositions;
import frc.robot.subsystems.arm.CaliGirls;

public class AutoCubeLow extends SequentialCommandGroup{
    ArmPositions aPosition;
    CaliGirls caliGirls;
    ArmExtension armExtension;

    public AutoCubeLow() {
        this.aPosition = ArmPositions.CUBE_PLACE_HIGH;
        this.caliGirls = CaliGirls.getInstance();
        this.armExtension = ArmExtension.getInstance();
        //aPosition.angle was erroring (on 2/21, wasn't on 2/20), so I changed to aPosition.armAngle because it seemed to do the same thing in ArmPositions.java
        //if it's behaving unexpectedly, that may be why
        addCommands(new MoveArmRotationToPos(aPosition.armAngle), new MoveArmWristToPos(aPosition.wrist), new MoveArmExtensionToPos(aPosition.extension));

        addCommands(new MoveArmRotationToPos(aPosition.armAngle), new MoveArmWristToPos(aPosition.wrist), new MoveArmExtensionToPos(aPosition.extension));
        this.aPosition = ArmPositions.CONE_INTAKE_GROUND;
        addCommands(new MoveArmRotationToPos(aPosition.armAngle), new MoveArmWristToPos(aPosition.wrist), new MoveArmExtensionToPos(aPosition.extension));
    }
}
