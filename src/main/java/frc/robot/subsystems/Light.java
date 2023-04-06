package frc.robot.subsystems;

import com.ctre.phoenix.led.CANdle;
import com.ctre.phoenix.led.StrobeAnimation;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.robot.MotorIDs;

public class Light extends SubsystemBase{
    private CANdle candle;
    private static Light light;
    private int strobeSpeed = 1; // must be between 0 and 1
    private double lastStrobeSpeedThatWasSet; // we can't get speed from BaseTwoSizeAnimation, so we have to store it.
    private double lastRedThatWasSet;
    private double lastGreenThatWasSet;
    private double lastBlueThatWasSet;
    private final boolean printCANdleDataToSmartDashboard = true;
    private final double STROBE_SCALER = 10;// high strobe speeds look solid, so we need to divide

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


    public void Linus_Indicator_recieve_HorDist(double degrees){
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
    }
    // ^ Linus 1.0 system: none, red flash, green steady

    public void fourStateProximity_recieve_HorDist(double degrees){
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
    }

    public void Side_Dependant_StrobeColor_recieve_HorDist(double degrees){
        if(degrees<0){
            degrees*=-1;// distance needs to be positive
            updateSideDependantStrobe(degrees, 255, 0);
        } else {
            updateSideDependantStrobe(degrees, 0, 255);
        }
    }
    private void updateSideDependantStrobe(double degrees, int red, int blue){
        if(degrees<2){
            AdrUpdateStrobe(0, 255, 0, 1);// set strobe to solid green
        } else if(degrees<30){ //30 degrees in an approximate xone, drivers may decide a different one
            AdrUpdateStrobe(red, 0, blue, 0.075);// set flashing red, value need to be tuned
        } else {
            AdrUpdateStrobe(0, 0, 0, 0);
        }
    }
    // ^ Adr 1-1.0 side dependant, as Linus, but blue on one side, red on other side

    public void SpeedInverseOfDistance_recieve_HorDist(double degrees){
        if(degrees<0){
            degrees*=-1;// strobe speed needs to be positive
        }
        degrees = 1-degrees/180*STROBE_SCALER; // strobe scaler needs to be tuned.
        strobeAnimation.setSpeed(degrees);
        lastStrobeSpeedThatWasSet=degrees;
        candle.animate(strobeAnimation);
    }
    // ^ Annie 1.0 System

    /* 
     * Runs of black HDPE cable protectors extend from the guardrail
     * on the scoring table side of the FIELD to the center of each CHARGE STATION.
     * A cable protector run is made up of multiple floor segments and an exit segment.
     * The total length of the cable protector run is 5 ft 6 in.
     * The floor segments are 3⁄4 in tall, 7 in wide,
     * with ~45° lead in ramps on each leading edge and secured to the carpet using hook fastener
     * which increases the height to approximately 7⁄8 in.
     * Exit segments mount over the guardrail and are 1 ft 8 3⁄4 in tall, 6 in wide
     * and extend into the field 1 3⁄4 in.
     */
    
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

    @Override
    public void periodic() {
        candle.animate(strobeAnimation);
    }
}