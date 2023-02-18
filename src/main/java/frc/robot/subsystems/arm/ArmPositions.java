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
    FLOOR_INTAKE_CONE(.559,39.7,.71),
    CONE_PLACE_MID(.75,24.24,.89),
    CONE_PLACE_HIGH(.77,124.77,.89),
    START_POSITION(.806,0.55,.6);
    
    
    public double angle,extension,wrist;
    ArmPositions(double angle, double extension, double wrist) {
        this.angle = angle;
        this.extension = extension;
        this.wrist = wrist;
    }
}
