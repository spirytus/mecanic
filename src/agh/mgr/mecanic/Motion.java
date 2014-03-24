package agh.mgr.mecanic;

import pl.edu.agh.amber.common.AmberClient;
import pl.edu.agh.amber.roboclaw.MotorsCurrentSpeed;
import pl.edu.agh.amber.roboclaw.RoboclawProxy;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: maciejmarczynski
 * Date: 17.03.2014
 * Time: 10:21
 * To change this template use File | Settings | File Templates.
 */
public class Motion {
    private double vx;
    private double vy;
    private double wt;

    public Motion(double Vx, double Vy, double Wt){

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
}
