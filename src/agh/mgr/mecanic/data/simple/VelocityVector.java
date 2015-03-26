package agh.mgr.mecanic.data.simple;

import org.jblas.DoubleMatrix;

import static agh.mgr.mecanic.data.Properties.HALF_OF_HEIGHT;
import static agh.mgr.mecanic.data.Properties.HALF_OF_WIDTH;
import static agh.mgr.mecanic.data.Properties.WHEEL_RADIUS;

public class VelocityVector {

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
        DoubleMatrix matrix4x3 = new DoubleMatrix(new double[][]{
                {1.0, 1.0, -(HALF_OF_HEIGHT + HALF_OF_WIDTH)},
                {1.0 , -1.0, HALF_OF_HEIGHT + HALF_OF_WIDTH},
                {1.0, -1.0, -(HALF_OF_HEIGHT + HALF_OF_WIDTH)},
                {1.0, 1.0, HALF_OF_HEIGHT + HALF_OF_WIDTH}
        });
        DoubleMatrix matrix3x1 = new DoubleMatrix(new double[][]{
                {this.getVx()},
                {this.getVy()},
                {this.getWt()}
        });
        DoubleMatrix result = matrix4x3.mmul(matrix3x1).mmul(1/ WHEEL_RADIUS);
        return new WheelsVelocity(result.get(0), result.get(1), result.get(2), result.get(3));
    }

}
