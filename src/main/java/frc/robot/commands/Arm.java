
package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.arm.CaliGirls;

public class Arm extends InstantCommand{
    //private double output; // This is used when using initialize() to control movement
    private CaliGirls caliGirls;
    public Arm() {
        this.caliGirls = CaliGirls.getInstance();
    }

    @Override
    public void initialize() {
        SmartDashboard.putNumber("Init", 1);
        double angle = 0;
        caliGirls.setTopPos(angle);
        caliGirls.setBottomPos(-angle);
    }
}
 