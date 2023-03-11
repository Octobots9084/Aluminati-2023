package frc.robot.subsystems.swerve;

public enum FieldPositions {
    RED_1_CONE(13.8, 0.59),
    RED_2_CUBE(13.8, 1.04),
    RED_3_CONE(13.8, 1.56),
    RED_4_CONE(13.8, 2.33),
    RED_5_CUBE(13.8, 2.71),
    RED_6_CONE(13.8, 3.22),
    RED_7_CONE(13.8, 3.96),
    RED_8_CUBE(13.8, 4.4),
    RED_9_CONE(13.8, 2.85),
    


    // 1 is away from substations
    BLUE_1_CONE(2.7, 0.58),
    BLUE_2_CUBE(2.7, 1.14),
    BLUE_3_CONE(2.7, 1.52),
    BLUE_4_CONE(2.7, 2.24),
    BLUE_5_CUBE(2.7, 2.79),
    BLUE_6_CONE(2.7, 3.2),
    BLUE_7_CONE(2.7, 3.9),
    BLUE_8_CUBE(2.7, 4.45),
    BLUE_9_CONE(2.7, 4.8);



    public double xPos;
    public double yPos;
    
    FieldPositions(double xPos, double yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }
}
