package agh.mgr.mecanic;

import agh.mgr.mecanic.data.simple.WheelsVelocity;
import pl.edu.agh.amber.hokuyo.MapPoint;

import java.util.List;

public class SharedState {
    public static synchronized List<MapPoint> getLastScan() {
        return lastScan;
    }

    public static synchronized void setLastScan(List<MapPoint> lastScan) {
        SharedState.lastScan = lastScan;
    }

    public static synchronized WheelsVelocity getCurrentWheelsVelocity() {
        return currentWheelsVelocity;
    }

    public static synchronized void setCurrentWheelsVelocity(WheelsVelocity currentWheelsVelocity) {
        SharedState.currentWheelsVelocity = currentWheelsVelocity;
    }

    public static List<MapPoint> lastScan;
    public static WheelsVelocity currentWheelsVelocity;

}
