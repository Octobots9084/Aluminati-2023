package frc.robot.subsystems;

import com.ctre.phoenix.led.CANdle;
import com.ctre.phoenix.led.StrobeAnimation;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.robot.MotorIDs;

public class Light extends SubsystemBase {
    private CANdle candle;
    private static Light light;
    private int strobeSpeed = 1; // must be between 0 and 1
    private double lastStrobeSpeedThatWasSet; // we can't get speed from BaseTwoSizeAnimation, so we have to store it.
    private double lastRedThatWasSet;
    private double lastGreenThatWasSet;
    private double lastBlueThatWasSet;
    private final boolean printCANdleDataToSmartDashboard = true;
    private final double STROBE_SCALER = .1;// high strobe speeds look solid, so we need to divide

    private final int DEFAULT_MODE = 1;

    public final int LEDmodeNumber_noDetection = 0;
    public final int LEDmodeNumber_ramseyThreeColor = 1;
    public final int LEDmodeNumber_Linus = 2;
    public final int LEDmodeNumber_fourState = 3;
    public final int LEDmodeNumber_sideDependant = 4;
    public final int LEDmodeNumber_InverseOfDistance = 5;

    public int mode = 1;
    public void setMode(int modeID){
        mode = modeID;
    }
    public int getMode(){
        return mode;
    }
    public void cycleMode(){
        mode+=1;
    }

    private boolean hasTarget;
    public boolean getHasTarget(){
        return hasTarget;
    }
    public void setHasTarget(boolean HasTarget){
        hasTarget = HasTarget;
    }

    private double degrees;// from auto align
    public void setDegrees(double degreesRecieved){
        this.degrees=degreesRecieved;
    }
    public double getDegrees(){
        return this.degrees;
    }
    
    public static Light getInstance() {
        if (light == null) {
            light = new Light();
        }
        return light;
    }
    private StrobeAnimation strobeAnimation = new StrobeAnimation(0, 100, 0, 0, strobeSpeed, 8);// default light settings
    public Light() {
        this.candle = new CANdle(MotorIDs.CANDLE_ID);
    }

    public void lightAnimation_noDetection(){

    }
    public void lightAnimation_ramseyTricolor(){
        if(hasTarget){
            if(Math.abs(degrees)<=2){
                AdrUpdateStrobe(0, 255, 0, 1);
            } else {
                AdrUpdateStrobe(255, 127, 0, 1);
            }
        } else {
            AdrUpdateStrobe(255, 0, 0, 1);
        }
        mode = LEDmodeNumber_ramseyThreeColor;
    }
    public void lightAnimation_Linus(){
        if(degrees<0){// distance needs to be positive
            degrees*=-1;
        }
        if(degrees<2){
            AdrUpdateStrobe(0, 255, 0, 1);// set strobe to solid green
        } else if(degrees<30){ //30 degrees in an approximate zone, drivers will likely decide a different one
            AdrUpdateStrobe(255, 0, 0, 0.075);// set flashing red, value need to be tuned
        } else {
            AdrUpdateStrobe(0, 0, 0, 0);
        }
        mode = LEDmodeNumber_Linus;
    }
    public void lightAnimation_fourState(){
        if(degrees<0){degrees*=-1;}
        if(degrees<2){
            AdrUpdateStrobe(0, 255, 0, 1);
        } else if(degrees<10){
            AdrUpdateStrobe(255, 0, 0, 0.1);
        } else if(degrees<30){
            AdrUpdateStrobe(255, 95, 0, 0.05);
        } else {
            AdrUpdateStrobe(0, 0, 0, 0);
        }
        mode = LEDmodeNumber_fourState;
    }
    public void lightAnimation_sideDependant(){
        if(Math.abs(degrees)<=2){
            AdrUpdateStrobe(0, 255, 0, 1);      // green if on target
        } else if(Math.abs(degrees)<=30){
            if(degrees<0){
                AdrUpdateStrobe(255, 0, 0, 1);  // red if to the left of target
            } else {
                AdrUpdateStrobe(0, 0, 255, 1);  // blue if to the right of target
            }
        } else {
            AdrUpdateStrobe(0, 0, 0, 0);        // off if more than 30 degrees from target
        }
        mode = LEDmodeNumber_sideDependant;
    }
    public void lightAnimation_InverseOfDistance(){
        if(degrees<0){
            degrees*=-1;// strobe speed needs to be positive
        }
        double speed = (1-degrees/180)*STROBE_SCALER; //calculates speed // strobe scaler needs to be tuned.
        strobeAnimation.setSpeed(speed);
        lastStrobeSpeedThatWasSet=speed;
        candle.animate(strobeAnimation);
        mode = LEDmodeNumber_InverseOfDistance;
    }


    public void AdrUpdateStrobe(int Red, int Green, int Blue, double Speed){
        if(printCANdleDataToSmartDashboard){
            SmartDashboard.putNumber("LastAssigned_Red", Red);
            SmartDashboard.putNumber("LastAssigned_Green", Green);
            SmartDashboard.putNumber("LastAssigned_Blue", Blue);
            SmartDashboard.putNumber("LastAssigned_Speed", Speed);
        }
        if(lastRedThatWasSet!=Red){ // if red is unchanged since last sycle, it doesn't need to be updated
            strobeAnimation.setR(Red);
            lastRedThatWasSet = Red;
            if(printCANdleDataToSmartDashboard){
                SmartDashboard.putNumber("CurrentRed", Red);
            }
        }
        if(lastGreenThatWasSet!=Green){
            strobeAnimation.setG(Green);
            lastGreenThatWasSet = Green;
            if(printCANdleDataToSmartDashboard){
                SmartDashboard.putNumber("CurrentGreen", Green);
            }
        }
        if(lastBlueThatWasSet!=Blue){
            strobeAnimation.setB(Blue);
            lastBlueThatWasSet = Blue;
            if(printCANdleDataToSmartDashboard){
                SmartDashboard.putNumber("CurrentBlue", Blue);
            }
        }
        if(lastStrobeSpeedThatWasSet!=Speed){
            strobeAnimation.setSpeed(Speed);
            lastStrobeSpeedThatWasSet=Speed;
            if(printCANdleDataToSmartDashboard){
                SmartDashboard.putNumber("CurrentSpeed", Speed);
            }
        }
        candle.animate(strobeAnimation);
    }

    public void lightUpdateControl(boolean hasTarget, int modeID){
        switch(modeID){

            case LEDmodeNumber_noDetection:
                lightAnimation_noDetection();
            break;

            case LEDmodeNumber_ramseyThreeColor:
                lightAnimation_ramseyTricolor();
            break;

            case LEDmodeNumber_Linus:
                lightAnimation_Linus();
            break;
            
            case LEDmodeNumber_fourState: 
                lightAnimation_fourState();
            break;

            case LEDmodeNumber_sideDependant:
                lightAnimation_sideDependant();
            break;

            case LEDmodeNumber_InverseOfDistance:
                lightAnimation_InverseOfDistance();
            break;

            default:
                lightUpdateControl(hasTarget, DEFAULT_MODE);
            break;
        }
    } // overlode constructor pls


    public void sendInfo(int rMode, double rDegrees, boolean rHasTarget){
        // degrees = 420 is ignored incase degrees don't have to be updated
        // mode = -1 is ignored incase mode doesn't have to be updated
        // use false unless hasTarget is true
        if(rDegrees!= 420){
            degrees = rDegrees;
        }
        if(rMode != -1){
            mode = rMode;
        }
        hasTarget = rHasTarget;
    }
    

    @Override
    public void periodic() {
        lightUpdateControl(hasTarget, mode);
    }
}