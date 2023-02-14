package frc.robot.subsystems.arm;

public enum ArmPositions {
    TOP(0,0,0),
    MIDDLE(0,0,0),
    LOW(0,0,0),
    STORE(0,0,0),
    STATION(0,0,0),
    FLOOR(0,0,0);
    
    
    public double angle,extension,wrist;
    ArmPositions(double angle, double extension, double wrist) {
        this.angle = angle;
        this.extension = extension;
    }
}
