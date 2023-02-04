
package frc.robot.commands;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.SparkMaxAbsoluteEncoder.Type;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.swerve.CaliGirls;

public class Arm extends InstantCommand{
    private double output;
    private CaliGirls caliGirls;
    public Arm(double output){
        this.output = output;
        this.caliGirls = CaliGirls.getInstance();
        //addRequirements(caliGirls);
    }
    @Override
    public void initialize(){
        caliGirls.setOutput(output);
    }
    public void moveToAngle(double angle){
        while(caliGirls.getRotation()<angle){
            caliGirls.setOutput(.2);
        } 
        caliGirls.setOutput(0);
    }

    
}
