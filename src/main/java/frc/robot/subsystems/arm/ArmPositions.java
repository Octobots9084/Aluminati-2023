package frc.robot.subsystems.arm;

public enum ArmPositions {
    CONE_PLACE_HIGH_SETUP(.77,124.77,.69),
    CONE_PLACE_HIGH(.59,0,.365),
    CONE_PLACE_MID_SETUP(.75,24.24,.69),
    CONE_PLACE_MID(.59,0,.365),
    // Cone place low values are placeholder
    CONE_PLACE_LOW(.51,0,.64),
    CONE_INTAKE_GROUND(.59,0,.365),
    CONE_INTAKE_SUBSTATION(.59,0,.365),
    TIPPED_CONE_SETUP(.59,0,.365),
    TIPPED_CONE_INTAKE(.59,0,.365),

    // Cube values are placeholder
    CUBE_PLACE_HIGH(.59,0,.365),
    CUBE_PLACE_MID(.59,0,.365),
    CUBE_PLACE_LOW(.59,0,.365),
    CUBE_INTAKE_FLOOR(.59,0,.365),
    CUBE_INTAKE_SUBSTATION(.59,0,.365),

    START_POSITION(.806,0.55,.365),
    DRIVE_WITH_PIECE(.59,0,.365),
    DRIVE_WITHOUT_PIECE(.261,1.47,.487);





    
    
    public double angle,extension,wrist;
    ArmPositions(double angle, double extension, double wrist) {
        this.angle = angle;
        this.extension = extension;
        this.wrist = wrist;
    }
}

