package frc.robot.subsystems;

import com.ctre.phoenix.led.CANdle;
import com.ctre.phoenix.led.StrobeAnimation;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.robot.MotorIDs;

public class Light extends SubsystemBase{
    private CANdle candle;
    private static Light light;
    public static Light getInstance() {
        if (light == null) {
            light = new Light();
        }
        return light;
    }
    private StrobeAnimation strobeAnimation = new StrobeAnimation(0, 0, 0);
    
    public Light() {
        this.candle = new CANdle(MotorIDs.CANDLE_ID);
    }

    public void test(int a, int b, int c) {
        candle.setLEDs(a,b,c);
    } 

    public void setStrobeAnimationPurple() {
        strobeAnimation.setR(255);
        strobeAnimation.setG(0);
        strobeAnimation.setB(255);
        candle.animate(strobeAnimation);
    }

    public void setStrobeAnimationYellow() {
        strobeAnimation.setR(255);
        strobeAnimation.setG(255);
        strobeAnimation.setB(0);
        candle.animate(strobeAnimation);
    }

    public void setStrobeAnimationRed() {
        strobeAnimation.setR(255);
        strobeAnimation.setG(0);
        strobeAnimation.setB(0);
        candle.animate(strobeAnimation);
    }
    @Override
    public void periodic() {
        candle.animate(strobeAnimation);
        
    }

}
