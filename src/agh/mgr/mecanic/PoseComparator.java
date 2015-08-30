package agh.mgr.mecanic;

import org.apache.log4j.Logger;

public class PoseComparator {
    final static Logger logger = Logger.getLogger(PoseComparator.class);
    private double delta;

    public PoseComparator(double delta) {
        this.delta = delta;
    }
// TODO : Taka wersje trzeba zrobic !! to powinno npdst historii zwracac jeszcze jakies -> oddalamy sie, albo reconfigure (gdy zblizamy sie zbyt wolno)s
//    public compare(Pose current, Pose expected, Pose previous){
//
//        return true;
//
//        return false;
//    }

    public boolean compare(Pose current, Pose expected){
        double absX = Math.abs(current.getX() - expected.getX());
        double absY = Math.abs(current.getY() - expected.getY());
        logger.debug("delta x " + absX + " delta Y " + absY);
        return absX < delta &&  absY < delta;
    }
}
