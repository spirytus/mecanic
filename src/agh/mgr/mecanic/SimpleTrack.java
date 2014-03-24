package agh.mgr.mecanic;

/**
 * Created with IntelliJ IDEA.
 * User: maciejmarczynski
 * Date: 17.03.2014
 * Time: 10:17
 * To change this template use File | Settings | File Templates.
 */
public class SimpleTrack {

    private int interval;
    private Motion[] track;

    public SimpleTrack(){
        this.interval = 10000;
        this.track = new Motion[]{
                new Motion(Properties.BASE_VX, Properties.BASE_VY, Properties.BASE_WZ),
                //new Motion(Properties.BASE_VX*0.5, Properties.BASE_VY*0.5, Properties.BASE_WZ*2),
                //new Motion(Properties.BASE_VX, Properties.BASE_VY, Properties.BASE_WZ),
                //new Motion(Properties.BASE_VX*0.5, Properties.BASE_VY*0.5, Properties.BASE_WZ*2),
                //new Motion(Properties.BASE_VX, 0.0, Properties.BASE_WZ*2)
        };
    }

    public Motion[] getTrack() {
        return track;
    }

    public int getInterval() {
        return interval;
    }
}
