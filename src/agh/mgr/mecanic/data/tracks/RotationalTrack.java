package agh.mgr.mecanic.data.tracks;

import agh.mgr.mecanic.data.Properties;
import agh.mgr.mecanic.data.simple.VelocityVector;


//TODO: Load it from app.properties ?

// Eg. track.1.interval=100
//     track.1.speed.1=10,15,25
//     track.1.speed.2.=12,13,18
//     .... and select track on beginning of TrackExecutor.

public class RotationalTrack extends BaseTrack{

    public RotationalTrack(){
        this.interval = 10000;
        this.track = new VelocityVector[]{
               // new Motion(Properties.BASE_VX, 0, 0),
              //  new Motion(-Properties.BASE_VX, 0, 0),
              //  new Motion(0, Properties.BASE_VY, 0),
              //  new Motion(0, -Properties.BASE_VY, 0),
                new VelocityVector(0, 0, Properties.BASE_WZ),
                new VelocityVector(0, 0, -Properties.BASE_WZ)
        };
    }
}
