package agh.mgr.mecanic.data.tracks;

import agh.mgr.mecanic.data.Properties;
import agh.mgr.mecanic.data.simple.IVector;
import agh.mgr.mecanic.data.simple.VelocityVector;
import agh.mgr.mecanic.data.simple.WheelsVelocity;

public class PaperTrack extends BaseTrack {
    public PaperTrack(){
        this.interval = 1500;

        double BASE_VY=5000.0;
        double BASE_WZ=7.0;
        // public WheelsVelocity(double leftFront, double rightFront, double leftBack, double rightBack){
//        IVector[] a = new WheelsVelocity[]{
//                new WheelsVelocity(BASE_V, -BASE_V, BASE_V, -BASE_V),
//                new WheelsVelocity(-BASE_V, BASE_V, -BASE_V, BASE_V),
//                new WheelsVelocity(2*BASE_V, 2*BASE_V, BASE_V, BASE_V),
//                new WheelsVelocity(BASE_V, BASE_V, 2*BASE_V, 2*BASE_V),
//                new WheelsVelocity(2*BASE_V, -0.5*BASE_V, 0, 0),
//                new WheelsVelocity(-2*BASE_V, 0.5*BASE_V, 0, 0)
//        };
//        IVector[] a = new VelocityVector[]{
//                new VelocityVector(0, -BASE_VY, BASE_WZ),
//                new VelocityVector(0, -BASE_VY, -BASE_WZ),
//                new VelocityVector(0, -BASE_VY, BASE_WZ),
//                new VelocityVector(0, -BASE_VY, -BASE_WZ),
//                new VelocityVector(0, -BASE_VY, BASE_WZ),
//                new VelocityVector(0, -BASE_VY, -BASE_WZ),
//        };
        IVector[] a = new VelocityVector[]{
                new VelocityVector(0, -BASE_VY,  -BASE_WZ),
                new VelocityVector(0, -BASE_VY, BASE_WZ),
                new VelocityVector(0, -BASE_VY,  -BASE_WZ),
                new VelocityVector(0, -BASE_VY, BASE_WZ),
                new VelocityVector(0, -BASE_VY,  -BASE_WZ),
                new VelocityVector(0, -BASE_VY, BASE_WZ),
                new VelocityVector(0, -BASE_VY,  -BASE_WZ),
                new VelocityVector(0, -BASE_VY, BASE_WZ),
                new VelocityVector(0, -BASE_VY,  -BASE_WZ),
                new VelocityVector(0, -BASE_VY, BASE_WZ),
        };

        this.track = a;
    }
}
