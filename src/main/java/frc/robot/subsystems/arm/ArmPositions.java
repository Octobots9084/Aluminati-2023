package frc.robot.subsystems.arm;

public enum ArmPositions {
    PRE_CONE_PLACE_HIGH(.806,0.09,0.55,.365),// placeholder
    CONE_PLACE_HIGH(.7835,0.09,123.8,.69),// feb 20 alex
    PRE_CONE_PLACE_MID(.806,0.06,0.55,.365),// placeholder
    CONE_PLACE_MID(.75,0.06,24.24,.69),// feb 20 alex
    CONE_PLACE_LOW(.598,0.03,0,.693), //unknown
    
    CONE_INTAKE_GROUND(.573,0.03,70.67,.485),//feb 20 adr, dash was questionable 
    INTAKE_SUBSTATION(.75,0,0,.6639),// placeholder, 

    CUBE_PLACE_HIGH(.75,0.09,83.0,.664),// placeholder
    CUBE_PLACE_MID(.699,0.06,0,.695),// placeholder
    CUBE_PLACE_LOW(.573,0.03,70.67,.485),// placeholder

    CUBE_INTAKE_FLOOR(.539,0.03,85.408,.577),//feb 20 adr, dash was questionable
    CUBE_INTAKE_SUBSTATION(.59,0,0,.365),// placeholder

    START_POSITION(.806,0,0.55,.365),// placeholder
    DRIVE_WITH_PIECE(.59,0.03,0,.365),// placeholder
    DRIVE_WITHOUT_PIECE(.261,0.02,1.47,.487);//feb 20 adr, dash was less questionable





    
    
    public double armAngle,angleHold,extension,wrist;
    ArmPositions(double angle, double angleHold, double extension, double wrist) {
        this.armAngle = angle;
        this.extension = extension;
        this.wrist = wrist;
        this.angleHold = angleHold;
    }
}