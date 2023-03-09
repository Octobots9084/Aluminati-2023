package frc.robot.subsystems.swerve;

public enum FieldPositions {
    RED_1_CONE(14.129, 2.339),
    RED_2_CUBE(14.129, 2.339),
    RED_3_CONE(14.129, 2.339),
    RED_4_CONE(14.129, 2.339),
    RED_5_CUBE(14.129, 2.339),
    RED_6_CONE(14.129, 2.339),
    RED_7_CONE(14.129, 2.339),
    RED_8_CUBE(14.129, 2.339),
    RED_9_CONE(14.129, 2.339),
    
    BLUE_1_CONE(14.129, 2.339),
    BLUE_2_CUBE(14.129, 2.339),
    BLUE_3_CONE(14.129, 2.339),
    BLUE_4_CONE(14.129, 2.339),
    BLUE_5_CUBE(14.129, 2.339),
    BLUE_6_CONE(14.129, 2.339),
    BLUE_7_CONE(14.129, 2.339),
    BLUE_8_CUBE(14.129, 2.339),
    BLUE_9_CONE(14.129, 2.339);

    public double xPos;
    public double yPos;
    
    FieldPositions(double xPos, double yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }
}
