package frc.robot.commands.arm;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.arm.CaliGirls;

public class SetArmAngle extends InstantCommand{
    private CaliGirls caliGirls;
    private double angle;

    public SetArmAngle(double angle) {
        this.caliGirls = CaliGirls.getInstance();
        this.angle = angle;
    }

    @Override
    public void initialize() {
        caliGirls.setBottomPos(angle);
    }
}