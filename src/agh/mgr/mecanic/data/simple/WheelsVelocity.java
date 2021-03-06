package agh.mgr.mecanic.data.simple;

import pl.edu.agh.amber.roboclaw.RoboclawProxy;

public class WheelsVelocity implements IVector{

    private final double leftFront;
    private final double rightFront;
    private final double leftBack;
    private final double rightBack;

    public WheelsVelocity(double leftFront, double rightFront, double leftBack, double rightBack){
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

    public String toString(){
        return "LF:" + leftFront + " RF:" + rightFront + " RB:" + rightBack + "LB:" + leftBack;
    }

    public void applyOnRobot(RoboclawProxy roboclawProxy){
        Utils.applyOnRobot(roboclawProxy, this);
    }
}
