package frc.robot.subsystems.swerve;

public enum FieldPositions {
    RED_CONE_RIGHT(14.129, 2.339);

    public double xPos;
    public double yPos;
    
    FieldPositions(double xPos, double yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }
}
