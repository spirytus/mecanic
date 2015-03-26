package agh.mgr.mecanic.data.modifiers;

import agh.mgr.mecanic.data.simple.VehicleOverallSpeed;
import agh.mgr.mecanic.data.simple.VehicleWheelsSpeed;
import org.jblas.DoubleMatrix;

import static agh.mgr.mecanic.data.Properties.*;

public class Transformer {
        public VehicleWheelsSpeed transform(VehicleOverallSpeed motion){
        DoubleMatrix matrix4x3 = new DoubleMatrix(new double[][]{
                {1.0, 1.0, -(HALF_OF_HEIGHT + HALF_OF_WIDTH)},
                {1.0 , -1.0, HALF_OF_HEIGHT + HALF_OF_WIDTH},
                {1.0, -1.0, -(HALF_OF_HEIGHT + HALF_OF_WIDTH)},
                {1.0, 1.0, HALF_OF_HEIGHT + HALF_OF_WIDTH}
        });
        DoubleMatrix matrix3x1 = new DoubleMatrix(new double[][]{
                {motion.getVx()},
                {motion.getVy()},
                {motion.getWt()}
        });
        DoubleMatrix result = matrix4x3.mmul(matrix3x1).mmul(1/ WHEEL_RADIUS);
        System.out.println(result);
        return new VehicleWheelsSpeed(result.get(0), result.get(1), result.get(2), result.get(3));
        }

    }
