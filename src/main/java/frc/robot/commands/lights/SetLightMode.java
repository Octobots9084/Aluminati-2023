package frc.robot.commands.lights;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.Light;

public class SetLightMode extends InstantCommand{
        private boolean mode;
    public SetLightMode(boolean mode){
            this.mode = mode;
        }
        @Override
        public void initialize(){
            if (mode){
                Light.getInstance().setStrobeAnimationPurple();
            }else{
                Light.getInstance().setStrobeAnimationYellow();
            }
        }
        
}
