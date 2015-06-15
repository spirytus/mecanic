package agh.mgr.mecanic.data.tracks;

import agh.mgr.mecanic.data.Properties;
import agh.mgr.mecanic.data.simple.IVector;
import agh.mgr.mecanic.data.simple.VelocityVector;
import pl.edu.agh.amber.location.LocationCurrent;

public class LocationTrack extends BaseTrack {

    public LocationTrack() {
        IVector[] a = new VelocityVector[]{
                new VelocityVector(Properties.BASE_VX, Properties.BASE_VY, Properties.BASE_WZ),
                new VelocityVector(Properties.BASE_VX, -Properties.BASE_VY, -Properties.BASE_WZ),
                new VelocityVector(Properties.BASE_VX, Properties.BASE_VY, Properties.BASE_WZ),
                new VelocityVector(Properties.BASE_VX, -Properties.BASE_VY, -Properties.BASE_WZ),
                new VelocityVector(Properties.BASE_VX, Properties.BASE_VY, Properties.BASE_WZ)
        };
        this.track = a;
    };
}
