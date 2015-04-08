package agh.mgr.mecanic;

import agh.mgr.mecanic.data.SerializableScanHistory;
import pl.edu.agh.amber.hokuyo.MapPoint;

import java.util.List;

public class Player {

    public static void main (String []args){
        List<List<MapPoint>> history = SerializableScanHistory.loadScans("/Users/maciejmarczynski/SKAAN.out");
        System.out.println(history);
    }
}
