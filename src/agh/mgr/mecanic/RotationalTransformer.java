package agh.mgr.mecanic;

/**
 * Created with IntelliJ IDEA.
 * User: maciek
 * Date: 17.09.2014
 * Time: 13:06
 * To change this template use File | Settings | File Templates.
 */

import org.jblas.DoubleMatrix;

import static agh.mgr.mecanic.Properties.*;


public class RotationalTransformer {
    public VehicleMotion transform(Motion motion){
//        DoubleMatrix matrix4x3 = new DoubleMatrix(new double[][]{
//                {1.0, 1.0, -(FRONT + HALF_OF_WIDTH)},
//                {1.0 , -1.0, FRONT + HALF_OF_WIDTH},
//                {1.0, -1.0, -(FRONT + 2*HALF_OF_HEIGHT + HALF_OF_WIDTH)},
//                {1.0, 1.0, FRONT + 2*HALF_OF_HEIGHT + HALF_OF_WIDTH}
//        });
        DoubleMatrix matrix4x3 = new DoubleMatrix(new double[][]{
                {1.0, 1.0, -(FRONT + HALF_OF_WIDTH)},
                {1.0 , -1.0, FRONT + HALF_OF_WIDTH},
                {1.0, -1.0, -(FRONT + 2*HALF_OF_HEIGHT + HALF_OF_WIDTH)},
                {1.0, 1.0, FRONT + 2*HALF_OF_HEIGHT + HALF_OF_WIDTH}
        });
        DoubleMatrix matrix3x1 = new DoubleMatrix(new double[][]{
                {motion.getVy()},
                {motion.getVx()},
                {motion.getWt()}
        });
        DoubleMatrix result = matrix4x3.mmul(matrix3x1).mmul(1/ WHEEL_RADIUS);
        //System.out.println(result);
        return new VehicleMotion(result.get(0), result.get(1), result.get(2), result.get(3));
    }
}
