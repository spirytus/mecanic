package agh.mgr.mecanic;

/**
 * Created with IntelliJ IDEA.
 * User: maciek
 * Date: 17.09.2014
 * Time: 13:05
 * To change this template use File | Settings | File Templates.
 */
public class RotationalTrack {

    private int interval;
    private Motion[] track;

    public RotationalTrack(){
        this.interval = 10000;
        this.track = new Motion[]{
               // new Motion(Properties.BASE_VX, 0, 0),
              //  new Motion(-Properties.BASE_VX, 0, 0),
              //  new Motion(0, Properties.BASE_VY, 0),
              //  new Motion(0, -Properties.BASE_VY, 0),
                new Motion(0, 0, Properties.BASE_WZ),
                new Motion(0, 0, -Properties.BASE_WZ)
        };
    }

    public Motion[] getTrack() {
        return track;
    }

    public int getInterval() {
        return interval;
    }
}
