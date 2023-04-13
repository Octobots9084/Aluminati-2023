package frc.robot.commands.arm;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.arm.CaliGirls;

public class CaliGirlsBottomMoveDownALittle extends InstantCommand {

    @Override
    public void initialize() {
        // Units of 0.04 are in PERCENT OF ARM ROTATION
        double pos = CaliGirls.getInstance().lastPosBottom - 0.045;
        CaliGirls.getInstance().setBottomPos(pos);
    }

}
