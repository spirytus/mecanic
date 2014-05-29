package agh.mgr.mecanic;

public class VehicleMotion {
    private final double leftFront;
    private final double rightFront;
    private final double leftBack;
    private final double rightBack;

    public VehicleMotion(double leftFront, double rightFront, double leftBack, double rightBack){

        this.leftFront = leftFront;
        this.rightFront = rightFront;
        this.leftBack = leftBack;
        this.rightBack = rightBack;
    }

    public double getLeftFront() {
        return leftFront;
    }

    public double getRightFront() {
        return rightFront;
    }

    public double getLeftBack() {
        return leftBack;
    }

    public double getRightBack() {
        return rightBack;
    }
}
