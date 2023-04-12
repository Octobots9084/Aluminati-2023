package frc.robot.subsystems;
import com.ctre.phoenix.led.CANdle;
import com.ctre.phoenix.led.StrobeAnimation;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.robot.MotorIDs;

public class Light extends SubsystemBase {
    public CANdle candle;
    private static Light light;
    public double FLASH_SPEED = 0.1; // THIS NEEDS TO BE TUNED
    private final double YAW_TOLERENCE = 1; // NEEDS TO BE TUNED
    private double MINIMUM_AREA_FILLED = 1; // NEEDS TO BE TUNED.
    //  I have absolutely no idea what this should be.
    //  print out the getArea() command to Smartdashboard after autoaligning to find it.
    //  A small amount should be subtracted to compensate for tolerance and imperfet testing.

    public Light() {
        this.candle = new CANdle(MotorIDs.CANDLE_ID);
    }
    private StrobeAnimation strobeAnimation = new StrobeAnimation(0, 0, 0, 0, 1, 8);// default light settings
    public static Light getInstance() {
        if (light == null) {
            light = new Light();
        }
        return light;
    }
   
    public void updateStrobeFromAutoAlign(boolean hasTarget, double degreesToTarget, double areaFilledByTarget){
        if(hasTarget==true){
            if(Math.abs(degreesToTarget)<=YAW_TOLERENCE){ // if aligned
                if(areaFilledByTarget>=MINIMUM_AREA_FILLED){// if close enough
                    AdrUpdateStrobe(0, 255, 0, 1); // aligned and close enough; ready to place, green
                } else { // no close enough
                    AdrUpdateStrobe(0, 0, 255, 1); // aligned but too far away, blue
                }
            } else { // not aligned
                AdrUpdateStrobe(255, 0, 0, 1);// not aligned, solid red
            }
            
        } else{
            AdrUpdateStrobe(255, 0, 0, FLASH_SPEED);// no target, red flash
        }
    }

    public void AdrUpdateStrobe(int Red, int Green, int Blue, double Speed){
        strobeAnimation.setR(Red);
        strobeAnimation.setG(Green);
        strobeAnimation.setB(Blue);
        strobeAnimation.setSpeed(Speed);
        candle.animate(strobeAnimation);
    }

    @Override
    public void periodic() {
    }
}