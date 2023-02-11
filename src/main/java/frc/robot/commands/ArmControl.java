package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.robot.ControlMap;
import frc.robot.subsystems.arm.ArmExtension;
import frc.robot.subsystems.arm.CaliGirls;

public class ArmControl extends CommandBase{
    private ArmExtension armExtension;
    private CaliGirls caliGirls;
    private XboxController xboxController;

    public ArmControl() {
        this.armExtension = ArmExtension.getInstance();
        this.caliGirls = CaliGirls.getInstance();
        this.xboxController = ControlMap.XBOX;
        addRequirements(armExtension);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        var pos = armExtension.lastpos + 20*xboxController.getLeftY();
        armExtension.SetPosition(pos);
    }
}
