package frc.robot.commands.arm.basic.tolerance;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.arm.ArmExtension;
import frc.robot.util.MathUtil;

public class ArmExtension2PosTolerance extends CommandBase {
    double target;
    ArmExtension armExtension;
    public ArmExtension2PosTolerance(double pos) {

        this.target = pos;
        this.armExtension = ArmExtension.getInstance();
    }

    @Override
    public void initialize() {
        armExtension.setPosition(target, false);
    }


    @Override
    public boolean isFinished() {
        return MathUtil.isWithinTolerance(armExtension.getPosition(), target, 8);
    }
}
