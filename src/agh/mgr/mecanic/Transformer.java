package agh.mgr.mecanic;

import static agh.mgr.mecanic.Properties.*;
import org.jblas.DoubleMatrix;

/**
 * Created with IntelliJ IDEA.
 * User: maciejmarczynski
 * Date: 17.03.2014
 * Time: 10:44
 * To change this template use File | Settings | File Templates.
 */
public class Transformer {
        public VehicleMotion transform(Motion motion){
        DoubleMatrix matrix4x3 = new DoubleMatrix(new double[][]{
                {1.0, 1.0, -(L1 + L2)},
                {1.0 , -1.0, L1 + L2 },
                {1.0, -1.0, -(L1 + L2)},
                {1.0, 1.0, L1 + L1}
        });
        DoubleMatrix matrix3x1 = new DoubleMatrix(new double[][]{
                {motion.getVx()},
                {motion.getVy()},
                {motion.getWt()}
        });
        DoubleMatrix result = matrix4x3.mmul(matrix3x1).mmul(1/R);
        System.out.println(result);
        return new VehicleMotion(result.get(0), result.get(1), result.get(2), result.get(3));
        }

    }
