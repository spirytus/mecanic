package agh.mgr.mecanic.data.tracks;

import agh.mgr.mecanic.data.Properties;
import agh.mgr.mecanic.data.simple.VehicleOverallSpeed;

public class SimpleTrack {

    private int interval;
    private VehicleOverallSpeed[] track;

    public SimpleTrack(){
        this.interval = 10000;

        VehicleOverallSpeed[] a = new VehicleOverallSpeed[]{
                new VehicleOverallSpeed(Properties.BASE_VX, Properties.BASE_VY, Properties.BASE_WZ),
                new VehicleOverallSpeed(Properties.BASE_VX, -Properties.BASE_VY, -Properties.BASE_WZ),
                new VehicleOverallSpeed(Properties.BASE_VX, Properties.BASE_VY, Properties.BASE_WZ),
                new VehicleOverallSpeed(Properties.BASE_VX, -Properties.BASE_VY, -Properties.BASE_WZ),
                new VehicleOverallSpeed(Properties.BASE_VX, Properties.BASE_VY, Properties.BASE_WZ)
        };
        this.track = a;
    }

    public VehicleOverallSpeed[] getTrack() {
        return track;
    }

    public int getInterval() {
        return interval;
    }
}
