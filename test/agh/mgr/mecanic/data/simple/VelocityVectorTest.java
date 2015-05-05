package agh.mgr.mecanic.data.simple;

import org.junit.Test;

import static org.junit.Assert.*;

public class VelocityVectorTest {

    @Test
    public void testToWheelsVelocity() throws Exception {
        VelocityVector velocityVector = new VelocityVector(4.0, 5.0, 6.0);
        WheelsVelocity wheelsVelocity = velocityVector.toWheelsVelocity();
        System.out.println(wheelsVelocity);

        assertEquals(wheelsVelocity.getLeftFront(), -34.26, 0.01);
        assertEquals(wheelsVelocity.getRightFront(), 34.42, 0.01);
        assertEquals(wheelsVelocity.getRightBack(), 34.62, 0.01);
        assertEquals(wheelsVelocity.getLeftBack(), -34.46, 0.01);
    }
}