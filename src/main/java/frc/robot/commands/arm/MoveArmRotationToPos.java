package frc.robot.commands.arm;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.arm.CaliGirls;
import frc.robot.util.MathUtil;

public class MoveArmRotationToPos extends CommandBase {
    double target;

    CaliGirls caliGirls;

    public MoveArmRotationToPos(double pos) {

        this.target = pos;
    }

    @Override
    public void initialize() {
        this.caliGirls = CaliGirls.getInstance();
        caliGirls.setBottomPos(target);
    }

    @Override
    public boolean isFinished() {
        
        return (MathUtil.isWithinTolerance(caliGirls.getBottomPos(),target,0.01));
    }
}
