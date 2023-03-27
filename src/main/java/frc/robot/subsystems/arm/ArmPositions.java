package frc.robot.subsystems.arm;

public enum ArmPositions {
    AUTO_ALIGN(0.71, 0.06, 0, 0), //wrong
    STOW(.5, 0, 0, .242), //meh 3/27
    PRE_CONE_PLACE_HIGH(.782, 0.09, 66.63, .647), //correct 3/27
    PRE_CONE_PLACE_MID(.72, 0.06, 20, .542), //old
    INTAKE_GROUND(.55, 0.03, 36.3, 0.5), //correct 3/27
    INTAKE_SUBSTATION(.746, 0, 0, .69), //old
    CUBE_PLACE_HIGH(.763, 0.09, 19.9, .69), //old
    CUBE_PLACE_MID(0.71, 0.06, 0, .69), //old
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