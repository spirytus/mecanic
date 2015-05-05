import agh.mgr.mecanic.data.simple.Utils;

import static org.junit.Assert.*;
import static java.lang.Math.PI;

public class UtilsTest {

    @org.junit.Test
    public void testNormalizeAngles() throws Exception {
        double angleInRad = 2*PI;
        assertEquals(Utils.normalizeAngle(angleInRad), 0.0, 0.00001);

        angleInRad = -2*PI;
        assertEquals(Utils.normalizeAngle(angleInRad), 0.0, 0.00001);

        angleInRad = 4*PI;
        assertEquals(Utils.normalizeAngle(angleInRad), 0.0, 0.00001);

        angleInRad = -4*PI;
        assertEquals(Utils.normalizeAngle(angleInRad), 0.0, 0.00001);
    }

    @org.junit.Test
    public void testNormalizeAndToDegrees(){
        double angleInRad = 2*PI+1;
        assertEquals(Utils.normalizeAndToDegrees(angleInRad), 57.29577951308232, 0.0001);

        angleInRad = -2*PI+1;
        assertEquals(Utils.normalizeAndToDegrees(angleInRad), 57.29577951308232, 0.0001);
    }
}