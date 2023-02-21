package frc.robot.subsystems.arm;

public enum ArmPositions {
    //TOP_CUBE(0,0,0),
    //MIDDLE_CUBE(0,0,0),
    //TOP_CONE(0.783,3400/25,0.315),
    //MIDDLE_CONE(0.758,1091/25,0.315),
    //LOW(0.586,183/25,0.457),
    //STORE(0,0,0),
    //STATION(0,0,0),
    //TIPPED_CONE(0,0,0),
    //TIPPED_CONE_SETUP(0,0,0),
    FLOOR_INTAKE_CONE(.57,50.7,.7),
    CONE_PLACE_HIGH(.77,124.77,.89),
    CONE_PLACE_MID(.75,24.24,.89),
    // Cone place low values are placeholder
    CONE_PLACE_LOW(.51,0,.64),

    // Cube values are placeholder
    CUBE_PLACE_HIGH(.51,0,.64),
    CUBE_PLACE_MID(.51,0,.64),
    CUBE_PLACE_LOW(.51,0,.64),

    START_POSITION(.806,0.55,.6),
    DRIVE_WITH_PIECE(.51,0,.64);
    
    
    public double angle,extension,wrist;
    ArmPositions(double angle, double extension, double wrist) {
        this.angle = angle;
        this.extension = extension;
        this.wrist = wrist;
    }
}
