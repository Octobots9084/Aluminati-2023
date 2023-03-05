package frc.robot.commands.arm.basic;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.arm.CaliGirls;

public class SetArmAngle extends InstantCommand {
    private CaliGirls caliGirls;
    private double angle,kf;

    public SetArmAngle(double angle, double kf) {
        this.caliGirls = CaliGirls.getInstance();
        this.angle = angle;
        this.kf = kf;
    }

    @Override
    public void initialize() {
        caliGirls.setBottomPos(angle);
        caliGirls.setBottomKf(kf);
    }
}
