package agh.mgr.mecanic.misc.tools;

import pl.edu.agh.amber.hokuyo.MapPoint;

import java.util.List;

public class ScanPlayer {

    public static void main (String []args){
        List<List<MapPoint>> history = SerializableScanHistory.loadScans("examples/skan001.out");
        for (List<MapPoint> mapPoints : history) {
            // Positioner.getPosition(mapPoints);
        }
    }
}