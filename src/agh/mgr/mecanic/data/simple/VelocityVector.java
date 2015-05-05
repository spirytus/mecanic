package agh.mgr.mecanic.data.simple;

import Jama.Matrix;
import org.jblas.DoubleMatrix;
import pl.edu.agh.amber.roboclaw.RoboclawProxy;

import java.io.IOException;

import static agh.mgr.mecanic.data.Properties.HALF_OF_HEIGHT;
import static agh.mgr.mecanic.data.Properties.HALF_OF_WIDTH;
import static agh.mgr.mecanic.data.Properties.WHEEL_RADIUS;

public class VelocityVector implements  IVector{

    private double vx;
    private double vy;
    private double wt;

    public VelocityVector(double Vx, double Vy, double Wt){
        vx = Vx;
        vy = Vy;
        wt = Wt;
    }

    public double getVx() {
        return vx;
    }

    public double getVy() {
        return vy;
    }

    public double getWt() {
        return wt;
    }

    public WheelsVelocity toWheelsVelocity(){
        Matrix matrix4x3 = new Matrix(new double[][]{
                {1.0, 1.0, -(HALF_OF_HEIGHT + HALF_OF_WIDTH)},
                {1.0 , -1.0, HALF_OF_HEIGHT + HALF_OF_WIDTH},
                {1.0, -1.0, -(HALF_OF_HEIGHT + HALF_OF_WIDTH)},
                {1.0, 1.0, HALF_OF_HEIGHT + HALF_OF_WIDTH}
        });
        Matrix matrix3x1 = new Matrix(new double[][]{
                {this.getVx()},
                {this.getVy()},
                {this.getWt()}
        });
        Matrix result = matrix4x3.times(matrix3x1).times(1 / WHEEL_RADIUS);
        return new WheelsVelocity(result.get(0,0), result.get(1,0), result.get(2,0), result.get(3,0));
    }

    public void applyOnRobot(RoboclawProxy roboclawProxy){
        WheelsVelocity wheelsVelocity = this.toWheelsVelocity();
        Utils.applyOnRobot(roboclawProxy, wheelsVelocity);


    }

}
