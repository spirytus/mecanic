package agh.mgr.mecanic;

public class SimpleTrack {

    private int interval;
    private Motion[] track;

    public SimpleTrack(){
        this.interval = 10000;

        Motion[] a = new Motion[]{
                new Motion(Properties.BASE_VX, Properties.BASE_VY, Properties.BASE_WZ),
                new Motion(Properties.BASE_VX, -Properties.BASE_VY, -Properties.BASE_WZ),
                new Motion(Properties.BASE_VX, Properties.BASE_VY, Properties.BASE_WZ),
                new Motion(Properties.BASE_VX, -Properties.BASE_VY, -Properties.BASE_WZ),
                new Motion(Properties.BASE_VX, Properties.BASE_VY, Properties.BASE_WZ)
        };
        this.track = a;
    }

    public Motion[] getTrack() {
        return track;
    }

    public int getInterval() {
        return interval;
    }
}
