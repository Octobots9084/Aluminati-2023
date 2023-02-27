package frc.robot.util;

public class ArmTrig {
    private double horizDistB;
    private double vertDistB;
    private double distE;
    private double distC;
    private double angleP;
    private double angleW;

    public ArmTrig (double horizDistB, double vertDistB, double distE, double distC, double angleP, double angleW) {
        this.horizDistB = horizDistB;
        this.vertDistB = vertDistB;
        this.distE = distE;
        this.distC = distC;
        this.angleP = angleP;
        this.angleW = angleW;
    }

    public double kfCalc() {
        return distE * (1/Math.cos(angleP - 0.0)) / 4000;
    }
    public double[] armTrig() {
        horizDistB = distE*Math.cos(angleP) + distC*Math.cos(angleP + angleW);
        vertDistB = distE*Math.sin(angleP) + distC*Math.sin(angleP + angleW);
        double[] arr = {horizDistB, vertDistB};
        return arr;
    }
}
