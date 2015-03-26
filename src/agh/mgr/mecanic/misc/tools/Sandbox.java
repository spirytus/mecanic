package agh.mgr.mecanic.misc.tools;

import agh.mgr.mecanic.data.SerializableScanHistory;
import org.jblas.DoubleMatrix;

public class Sandbox {
    public static void main(String []args){

        DoubleMatrix matrix4x3 = new DoubleMatrix(new double[][]{
                {1.0, 2.0, 3.0},
                {4.0 ,5.0, 6.0},
                {7.0, 8.0, 9.0},
                {1.0, 5.0, 9.0}
        });
        DoubleMatrix matrix3x1 = new DoubleMatrix(new double[][]{
                {2.0},
                {4.0},
                {8.0}
        });

        DoubleMatrix result = matrix4x3.mmul(matrix3x1).mmul(1/2.0);
        System.out.println("Wynik");
        System.out.println(result);
        //System.out.println(SerializableScanHistory.loadScans("/Users/maciejmarczynski/SKAAN.out"));
    }
}
