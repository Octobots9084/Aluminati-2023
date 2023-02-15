package frc.robot.subsystems.arm;

public enum ArmPositions {
    TOP_CUBE(0,0,0),
    MIDDLE_CUBE(0,0,0),
    TOP_CONE(0,0,0),
    MIDDLE_CONE(0,0,0),
    LOW(0,0,0),
    STORE(0,0,0),
    STATION(0,0,0),
    TIPPED_CONE(0,0,0),
    TIPPED_CONE_SETUP(0,0,0),
    FLOOR(0,0,0);
    
    
    public double angle,extension,wrist;
    ArmPositions(double angle, double extension, double wrist) {
        this.angle = angle;
        this.extension = extension;
    }
}
