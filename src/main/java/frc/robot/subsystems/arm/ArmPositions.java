package frc.robot.subsystems.arm;

public enum ArmPositions {
    // All values updated Feb 26th - Aayush and Annie
    AUTO_ALIGN(0.71, 0.06, 0, 0),

    PRE_CONE_PLACE_HIGH(.782, 0.09, 66.63, .647),
    PRE_CONE_PLACE_MID(.72, 0.06, 20, .542),

    CONE_PLACE_HIGH(.782, 0.09, 66.63, .647),
    CONE_PLACE_MID(.642, 0.06, 0, .493),
    CONE_PLACE_LOW(.514, 0.03, 0, .4746),

    CONE_INTAKE_GROUND(.55, 0.03, 36.3, 0.5),
    INTAKE_SUBSTATION(.746, 0, 0, .69),

    CUBE_PLACE_HIGH(.763, 0.09, 19.9, .69),
    CUBE_PLACE_MID(0.71, 0.06, 0, .69),
    CUBE_PLACE_LOW(.514, 0.03, 0, .4746),

    CUBE_INTAKE_FLOOR(.58, 0.03, 38.5, 0.522),
    CUBE_INTAKE_SUBSTATION(.736, 0, 0, .69),

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