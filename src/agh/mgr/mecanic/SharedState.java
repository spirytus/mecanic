package agh.mgr.mecanic;

import agh.mgr.mecanic.data.simple.WheelsVelocity;
import pl.edu.agh.amber.hokuyo.Scan;

public class SharedState {
    public static synchronized Scan getLastScan() {
        return lastScan;
    }

    public static synchronized void setLastScan(Scan lastScan) {
        SharedState.lastScan = lastScan;
    }

    public static synchronized WheelsVelocity getCurrentWheelsVelocity() {
        return currentWheelsVelocity;
    }

    public static synchronized void setCurrentWheelsVelocity(WheelsVelocity currentWheelsVelocity) {
        SharedState.currentWheelsVelocity = currentWheelsVelocity;
    }

    public static Scan lastScan;
    public static WheelsVelocity currentWheelsVelocity;

}
