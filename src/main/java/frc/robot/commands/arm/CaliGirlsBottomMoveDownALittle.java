package frc.robot.commands.arm;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.arm.CaliGirls;

public class CaliGirlsBottomMoveDownALittle extends InstantCommand {


    @Override
    public void initialize() {
        double pos = CaliGirls.getInstance().lastPosBottom-0.04;
        System.out.println(pos);
        CaliGirls.getInstance().setBottomPos(pos);
    }

}
    