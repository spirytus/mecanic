package agh.mgr.mecanic.data.simple;

import pl.edu.agh.amber.roboclaw.RoboclawProxy;

import java.io.IOException;

public class Utils {

    public static double normalizeAndToDegrees(double angleInRad){
        return Math.toDegrees(normalizeAngle(angleInRad));
    }

    public static double normalizeAngle(double angleInRad) {
        return angleInRad - (2 * Math.PI) * Math.floor((angleInRad + Math.PI) / (2 * Math.PI));
    }

    public static void applyOnRobot(RoboclawProxy roboclawProxy, WheelsVelocity wheelsVelocity){
        try {
            roboclawProxy.sendMotorsCommand(
                    (int) wheelsVelocity.getLeftFront(),
                    (int) wheelsVelocity.getRightFront(),
                    (int) wheelsVelocity.getLeftBack(),
                    (int) wheelsVelocity.getRightBack());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
