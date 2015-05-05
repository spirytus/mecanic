package agh.mgr.mecanic.misc.tools;

import pl.edu.agh.amber.hokuyo.MapPoint;

import java.util.List;

public class Sandbox {
    public static void main(String []args){
        List<List<MapPoint>> history = SerializableScanHistory.loadScans("examples/skan001.out");
    }
}
