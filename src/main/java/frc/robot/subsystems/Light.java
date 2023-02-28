package frc.robot.subsystems;

import com.ctre.phoenix.led.CANdle;
import com.ctre.phoenix.led.StrobeAnimation;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Light extends SubsystemBase{
    private CANdle candle;
    private static Light light;
    public static Light GetInstance() {
        if (light == null) {
            light = new Light();
        }
        return light;
    }
    private StrobeAnimation strobeAnimation = new StrobeAnimation(0, 0, 0);
    
    public Light() {
        this.candle = new CANdle(13);
    }

    public void test(int a, int b, int c) {
        candle.setLEDs(a,b,c);
    } 

    public void setStrobeAnimationPurple() {
        strobeAnimation.setR(200);
        strobeAnimation.setG(0);
        strobeAnimation.setB(255);
    }

    public void setStrobeAnimationYellow() {
        strobeAnimation.setR(200);
        strobeAnimation.setG(255);
        strobeAnimation.setB(0);
    }

}
