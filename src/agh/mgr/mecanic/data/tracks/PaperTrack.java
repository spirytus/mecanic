package agh.mgr.mecanic.data.tracks;

import agh.mgr.mecanic.data.simple.IVector;
import agh.mgr.mecanic.data.simple.WheelsVelocity;

public class PaperTrack extends BaseTrack {
    public PaperTrack(){
        this.interval = 4000;

        double BASE_V=300.0;
        // public WheelsVelocity(double leftFront, double rightFront, double leftBack, double rightBack){
        IVector[] a = new WheelsVelocity[]{
                //new WheelsVelocity(BASE_V, -BASE_V, BASE_V, -BASE_V),
                //new WheelsVelocity(-BASE_V, BASE_V, -BASE_V, BASE_V),
                //new WheelsVelocity(2*BASE_V, 2*BASE_V, BASE_V, BASE_V),
                //new WheelsVelocity(BASE_V, BASE_V, 2*BASE_V, 2*BASE_V),
                new WheelsVelocity(2*BASE_V, -0.5*BASE_V, 0, 0),
                new WheelsVelocity(-2*BASE_V, 0.5*BASE_V, 0, 0)
        };
        this.track = a;
    }
}
