package frc.robot.commands.arm;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.arm.ArmExtension;
import frc.robot.util.MathUtil;

public class MoveExtensionToPos extends CommandBase {
    double target;

    ArmExtension armExtension;

    public MoveExtensionToPos(double pos) {

        this.target = pos;
    }

    @Override
    public void initialize() {
        this.armExtension = ArmExtension.getInstance();
        armExtension.SetPosition(target, false);
    }

    @Override
    public boolean isFinished() {
        return (MathUtil.isWithinTolerance(armExtension.GetPosition(),target,5));
    }
}
