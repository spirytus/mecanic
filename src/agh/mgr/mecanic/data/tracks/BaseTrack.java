package agh.mgr.mecanic.data.tracks;

import agh.mgr.mecanic.data.simple.IVector;

public abstract class BaseTrack {

    protected int interval;
    protected IVector[] track;


    public IVector[] getTrack() {
        return track;
    }

    public int getInterval() {
        return interval;
    }
}
