package agh.mgr.mecanic.data;

import pl.edu.agh.amber.hokuyo.MapPoint;

import java.io.*;
import java.util.LinkedList;
import java.util.List;


/*
    Trasforms MapPoints to Serializable scan history
 */
public class SerializableScanHistory implements Serializable {

    public List<List<SerializableMapPoint>> scans;

    public SerializableScanHistory(){
        this.scans = new LinkedList<List<SerializableMapPoint>>();
    }

    public void add(List<MapPoint> points){
        List<SerializableMapPoint> l = new LinkedList<SerializableMapPoint>();


        for(MapPoint p : points){
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

    public static List<List<MapPoint>> loadScans(String filepath){
        List<List<MapPoint>> scans = new LinkedList<List<MapPoint>>();
        try
        {
            FileInputStream fileIn = new FileInputStream(filepath);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            SerializableScanHistory e = (SerializableScanHistory) in.readObject();

            for(List<SerializableScanHistory.SerializableMapPoint> l : e.scans){
                List<MapPoint> mapPoints = new LinkedList<MapPoint>();
                for(SerializableScanHistory.SerializableMapPoint m : l){
                    mapPoints.add(new MapPoint(m.distance, m.angle, 0));
                }
                scans.add(mapPoints);
            }
            in.close();
            fileIn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return scans;
    }

    public static void dumpScans(SerializableScanHistory scans, String filepath){
        FileOutputStream fileOut;
        try {
            fileOut = new FileOutputStream(filepath);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(scans);
            out.close();
            fileOut.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}


