package frc.robot.subsystems;

import com.ctre.phoenix.led.CANdle;
import com.ctre.phoenix.led.RainbowAnimation;

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
    private RainbowAnimation rainbowAnimation = new RainbowAnimation(1, 0.1, 8);
    
    public Light() {
        this.candle = new CANdle(13);
    }

    public void test(int a, int b, int c) {
        candle.setLEDs(a,b,c);
    }

}
