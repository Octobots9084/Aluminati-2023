package frc.robot.subsystems;

import com.ctre.phoenix.CANifier.LEDChannel;
import com.ctre.phoenix.led.Animation;
import com.ctre.phoenix.led.CANdle;
import com.ctre.phoenix.led.RainbowAnimation;
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
    private RainbowAnimation rainbowAnimation = new RainbowAnimation(0.9 , 0.99, 272);
    private StrobeAnimation strobeAnimation = new StrobeAnimation(0, 0, 0, 0, 0.9, 272);
    
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
        animation = (strobeAnimation);
    }
    public void setAnimationRainbow     () {
        animation = (rainbowAnimation);
    }

    public void setStrobeAnimationYellow() {
        strobeAnimation.setR(255);
        strobeAnimation.setG(255);
        strobeAnimation.setB(0);
        animation = (strobeAnimation);
    }

    public void setStrobeAnimationRed() {
       strobeAnimation.setR(255);
        strobeAnimation.setG(0);
        strobeAnimation.setB(0);
        animation = (strobeAnimation);
    }
    private Animation animation = strobeAnimation;
    @Override
    public void periodic() {
        candle.animate(animation);
        
    }

    
}
