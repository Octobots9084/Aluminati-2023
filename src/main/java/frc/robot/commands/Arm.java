
package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.arm.CaliGirls;

public class Arm extends InstantCommand{
    //private double output; // This is used when using initialize() to control movement
    private CaliGirls caliGirls;


        /** The initialize function was used for the child snatcher.
         * I have copied this method incase there is reson to use it
         * The Arm currently uses moveToAngle(radians) and moveToAngleWitPID(radians)
    */
    /*@Override
    public void initialize(){
        caliGirls.setOutput(output);
    }*/

    // This method should move to target angle, but will likely overshoot slightly, and will pause the rest of the robot while running.
    public void moveToAngle(double angle){
        while(caliGirls.getRotation()<angle){
            caliGirls.setOutput(.2);
        } 
        caliGirls.setOutput(0);
    }

    // This method should move to target angle better, and without pausing the robot.
    // The PID coefficients are untuned.
    // This Method will replace moveToAngle when it is funcitonal.
    @Override
    public void initialize() {
        caliGirls = CaliGirls.getInstance();
        SmartDashboard.putNumber("Init", 1);
        double angle = 0;
        caliGirls.caliPIDControllerTop.setReference(angle, com.revrobotics.CANSparkMax.ControlType.kPosition);
        caliGirls.caliPIDControllerBottom.setReference(-angle, com.revrobotics.CANSparkMax.ControlType.kPosition);
    }
}
 