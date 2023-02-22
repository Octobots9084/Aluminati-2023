package frc.robot.subsystems.arm;

public enum ArmPositions {
    CONE_PLACE_HIGH(.77,124.77,.69),// feb 20 alex
    CONE_PLACE_MID(.75,24.24,.69),// feb 20 alex
    CONE_PLACE_LOW(.598,0,.693), //unknown
    
    CONE_INTAKE_GROUND(.573,70.67,.485),//feb 20 adr, dash was questionable 
    INTAKE_SUBSTATION(.75,0,.6639),// placeholder

    CUBE_PLACE_HIGH(.75,83.0,.664),// placeholder
    CUBE_PLACE_MID(.699,0,.695),// placeholder
    CUBE_PLACE_LOW(.573,70.67,.485),// placeholder

    CUBE_INTAKE_FLOOR(.539,85.408,.577),//feb 20 adr, dash was questionable
    CUBE_INTAKE_SUBSTATION(.59,0,.365),// placeholder

    START_POSITION(.806,0.55,.365),// placeholder
    DRIVE_WITH_PIECE(.59,0,.365),// placeholder
    DRIVE_WITHOUT_PIECE(.261,1.47,.487);//feb 20 adr, dash was less questionable





    
    
    public double armAngle,extension,wrist;
    ArmPositions(double angle, double extension, double wrist) {
        this.armAngle = angle;
        this.extension = extension;
        this.wrist = wrist;
    }
}