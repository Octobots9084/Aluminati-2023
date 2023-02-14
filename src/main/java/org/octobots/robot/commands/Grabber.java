package org.octobots.robot.commands;

import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj2.command.CommandBase;


public class Grabber extends CommandBase {
    private CANSparkMax motorLeft;
    private CANSparkMax motorRight;

    public void grabGamePiece(){
        motorLeft.set(.5);
        motorRight.set(-.5);
    }
    public void releaseGamePiece(){
        motorLeft.set(-.5);
        motorRight.set(.5);
    }

}