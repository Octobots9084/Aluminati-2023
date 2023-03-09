package frc.robot.subsystems.arm;

public enum ArmPositions {
    // All values updated Feb 26th - Aayush and Annie
    PRE_CONE_PLACE_HIGH(.763, 0.09, 76.55, .542),
    PRE_CONE_PLACE_MID(.732, 0.06, 18, .542),

    CONE_PLACE_HIGH(.74, 0.09, 75.55, .542),
    CONE_PLACE_MID(.7, 0.06, 24, .655),
    CONE_PLACE_LOW(.514, 0.03, 0, .4746),

    CONE_INTAKE_GROUND(.578, 0.03, 36, 0.527),
    INTAKE_SUBSTATION(.756, 0, 0, .69),

    CUBE_PLACE_HIGH(.763, 0.09, 19.9, .69),
    CUBE_PLACE_MID(0.71, 0.06, 0, .69),
    CUBE_PLACE_LOW(.514, 0.03, 0, .4746),

    CUBE_INTAKE_FLOOR(.58, 0.03, 38.5, 0.522),
    CUBE_INTAKE_SUBSTATION(.756, 0, 0, .69),

    START_POSITION(.5, 0, 0, .242),
    DRIVE_WITH_PIECE(.581, 0.03, 7.35, .324),
    DRIVE_WITHOUT_PIECE(.5, 0, 0, .242);

    public double armAngle, angleHold, extension, wrist;

    ArmPositions(double angle, double angleHold, double extension, double wrist) {
        this.armAngle = angle;
        this.extension = extension;
        this.wrist = wrist;
        this.angleHold = angleHold;
    }
}