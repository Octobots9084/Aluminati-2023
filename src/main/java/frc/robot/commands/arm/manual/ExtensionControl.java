package frc.robot.commands.arm.manual;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.robot.ControlMap;
import frc.robot.subsystems.arm.ArmExtension;
import frc.robot.util.MathUtil;

public class ExtensionControl extends CommandBase {
    private ArmExtension armExtension;
    // private XboxController xboxController;

    public ExtensionControl() {
        this.armExtension = ArmExtension.getInstance();
        // this.xboxController = ControlMap.XBOX;
        addRequirements(armExtension);
    }

    @Override
    public void execute() {
        var pos = (6 * Math.signum(ControlMap.CO_DRIVER_RIGHT.getX())
                * MathUtil.fitDeadband(Math.pow(ControlMap.CO_DRIVER_RIGHT.getX(), 2), 0.01));

        armExtension.setPosition(armExtension.lastpos + pos, false);

        
    }
}
