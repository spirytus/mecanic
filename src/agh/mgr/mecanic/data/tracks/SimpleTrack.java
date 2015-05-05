package agh.mgr.mecanic.data.tracks;

import agh.mgr.mecanic.data.Properties;
import agh.mgr.mecanic.data.simple.IVector;
import agh.mgr.mecanic.data.simple.VelocityVector;

public class SimpleTrack {

    private int interval;
    private IVector[] track;

    public SimpleTrack(){
        this.interval = 10000;

        IVector[] a = new VelocityVector[]{
                new VelocityVector(Properties.BASE_VX, Properties.BASE_VY, Properties.BASE_WZ),
                new VelocityVector(Properties.BASE_VX, -Properties.BASE_VY, -Properties.BASE_WZ),
                new VelocityVector(Properties.BASE_VX, Properties.BASE_VY, Properties.BASE_WZ),
                new VelocityVector(Properties.BASE_VX, -Properties.BASE_VY, -Properties.BASE_WZ),
                new VelocityVector(Properties.BASE_VX, Properties.BASE_VY, Properties.BASE_WZ)
        };
        this.track = a;
    }

    public IVector[] getTrack() {
        return track;
    }

    public int getInterval() {
        return interval;
    }
}
