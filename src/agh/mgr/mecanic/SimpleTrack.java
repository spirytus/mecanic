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
        Motion[] a = new Motion[10];
        for(int i = 0; i<10; i++){
            a[i] = new Motion(Properties.BASE_VX*(1-i*0.1), Properties.BASE_VY*(i*0.1), Properties.BASE_WZ);
        }

        for(int i = 0; i<10; i++){
            a[i] = new Motion(Properties.BASE_VX, Properties.BASE_VY*0.6, Properties.BASE_WZ);
        }

        this.track = a;
    }

    public Motion[] getTrack() {
        return track;
    }

    public int getInterval() {
        return interval;
    }
}
