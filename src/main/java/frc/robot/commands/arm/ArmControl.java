package frc.robot.commands.arm;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.robot.ControlMap;
import frc.robot.subsystems.arm.ArmExtension;
import frc.robot.util.MathUtil;

public class ArmControl extends CommandBase {
    private ArmExtension armExtension;
    // private XboxController xboxController;

    public ArmControl() {
        this.armExtension = ArmExtension.getInstance();
        // this.xboxController = ControlMap.XBOX;
        addRequirements(armExtension);
    }

    @Override
    public void execute() {
        // var pos = armExtension.lastpos + 0.025 * MathUtil.fitDeadband(Math.pow(ControlMap.CO_DRIVER_RIGHT.getX(), 3));
        // SmartDashboard.putNumber("lastarmpos", armExtension.lastpos);
        // SmartDashboard.putNumber("armpos", pos);
        armExtension.SetPosition(
                armExtension.lastpos + 0.025 * MathUtil.fitDeadband(Math.pow(ControlMap.CO_DRIVER_RIGHT.getX(), 3)),
                false);
    }
}
