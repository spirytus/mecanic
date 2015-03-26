package agh.mgr.mecanic;

import agh.mgr.mecanic.GatheredScans;
import pl.edu.agh.amber.hokuyo.MapPoint;

import java.io.*;
import java.util.LinkedList;
import java.util.List;


public class HistorySerializer {
    public static List<List<MapPoint>> loadScans(String filepath){
        List<List<MapPoint>> scans = new LinkedList<List<MapPoint>>();
        try
        {
            //String filepath = "/Users/maciejmarczynski/SKAAN.out";
            FileInputStream fileIn = new FileInputStream(filepath);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            GatheredScans e = (GatheredScans) in.readObject();

            for(List<GatheredScans.SerializableMapPoint> l : e.scans){
                List<MapPoint> mapPoints = new LinkedList<MapPoint>();
                for(GatheredScans.SerializableMapPoint m : l){
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

    public static void dumpScans(GatheredScans scans, String filepath){
        FileOutputStream fileOut =
                null;
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
