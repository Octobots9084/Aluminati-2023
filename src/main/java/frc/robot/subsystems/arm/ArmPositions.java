package frc.robot.subsystems.arm;

public enum ArmPositions {
    AUTO_ALIGN(0.785, 0.06, 0, 0.35), //good 3/28

    STOW(.48, 0, 0, .242), //good 3/28
    PRE_CONE_PLACE_HIGH(.785, 0.09, 71, .567), //correct 3/27
    PRE_CONE_PLACE_MID(.77, 0.06, 23.5, .619), //good 3/28
    INTAKE_GROUND(.585, 0.03, 31.4, 0.47), //correctish 3/27
    INTAKE_SUBSTATION(.78, 0, 0, .69), //good 3/28
    CUBE_PLACE_HIGH(.77, 0.09, 47, .65), //good 3/28
    CUBE_PLACE_MID(.642, 0.06, 0, .493),
    DRIVE_POSITION(.5, 0, 0, .31), //good 3/28
    PRE_DRIVE_POSITION(.5, 0, 0, .45),
    // CUBE_INTAKE_SUBSTATION(.736, 0, 0, .69), //old

    DEPRECIATED_CONE_PLACE_HIGH(.782, 0.09, 66.63, .647), //wrong
    DEPRECIATED_CONE_PLACE_MID(.642, 0.06, 0, .493), //wrong
    DEPRECIATED_CONE_PLACE_LOW(.514, 0.03, 0, .4746), //wron
    DEPRECIATED_CUBE_PLACE_LOW(.514, 0.03, 0, .4746), //wrong
    DEPRECIATED_CUBE_INTAKE_FLOOR(.58, 0.03, 38.5, 0.522); //wrong
    // DEPRECIATED_START_POSITION(.5, 0, 0, .242), //wrong
    // DEPRECIATED_DRIVE_WITH_PIECE(.581, 0.03, 7.35, .324); //wrong

    public double armAngle, angleHold, extension, wrist;

    ArmPositions(double angle, double angleHold, double extension, double wrist) {
        this.armAngle = angle;
        this.extension = extension;
        this.wrist = wrist;
        this.angleHold = angleHold;
    }
}