package agh.mgr.mecanic;

import pl.edu.agh.amber.hokuyo.MapPoint;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class GatheredScans implements Serializable {
    public List<List<SerializableMapPoint>> scans;
    public GatheredScans(){
        scans = new LinkedList<List<SerializableMapPoint>>();
    }
    public void add(List<MapPoint> punkty){
        List<SerializableMapPoint> l = new LinkedList<SerializableMapPoint>();


        for(MapPoint p : punkty){
            l.add(new SerializableMapPoint(p.getAngle(),p.getDistance()));
        }
        scans.add(l);
    }
    public class SerializableMapPoint implements Serializable{
        public double angle;
        public double distance;

        public SerializableMapPoint(double angle, double distance){
            this.angle = angle;
            this.distance = distance;
        }

    }
}


