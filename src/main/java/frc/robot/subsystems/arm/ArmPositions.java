package frc.robot.subsystems.arm;

public enum ArmPositions {
    CONE_PLACE_HIGH_SETUP(.77,124.77,.69),// placeholder
    CONE_PLACE_HIGH(.59,0,.365),// placeholder
    CONE_PLACE_MID_SETUP(.75,24.24,.69),// placeholder
    CONE_PLACE_MID(.59,0,.365),// placeholder
    // Cone place low values are placeholder
    CONE_PLACE_LOW(.51,0,.64),
    CONE_INTAKE_GROUND(.539,85.408,.577),//feb 20
    CONE_INTAKE_SUBSTATION(.59,0,.365),// placeholder
    TIPPED_CONE_SETUP(.59,0,.365),// placeholder
    TIPPED_CONE_INTAKE(.59,0,.365),// placeholder

    // Cube values are placeholder
    CUBE_PLACE_HIGH(.59,0,.365),// placeholder
    CUBE_PLACE_MID(.59,0,.365),// placeholder
    CUBE_PLACE_LOW(.59,0,.365),// placeholder
    CUBE_INTAKE_FLOOR(.539,85.408,.577),//feb 20
    CUBE_INTAKE_SUBSTATION(.59,0,.365),// placeholder

    START_POSITION(.806,0.55,.365),// placeholder
    DRIVE_WITH_PIECE(.59,0,.365),// placeholder
    DRIVE_WITHOUT_PIECE(.261,1.47,.487);//feb 20





    
    
    public double angle,extension,wrist;
    ArmPositions(double angle, double extension, double wrist) {
        this.angle = angle;
        this.extension = extension;
        this.wrist = wrist;
    }
}

