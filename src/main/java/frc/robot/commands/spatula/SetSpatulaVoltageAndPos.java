package frc.robot.commands.spatula;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.spatula.RollingPins;
import frc.robot.subsystems.spatula.SpatulaFlip;

public class SetSpatulaVoltageAndPos extends InstantCommand{ 
    private SpatulaFlip spatulaFlip;
    private RollingPins rollingPins;
    private double voltage, pos;
    public SetSpatulaVoltageAndPos(double voltage, double pos) {
        this.spatulaFlip = SpatulaFlip.getInstance();
        this.voltage = -voltage;
        this.rollingPins = RollingPins.getInstance();
        this.pos = pos;
    }    

    @Override
    public void initialize() {
        spatulaFlip.setSpatulaPos(pos);
        rollingPins.setRollingPinVoltage(voltage);
    }
}
