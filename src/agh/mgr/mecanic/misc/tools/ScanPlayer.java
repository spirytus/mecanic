package agh.mgr.mecanic.misc.tools;

import agh.mgr.mecanic.Positioner;
import org.jfree.ui.RefineryUtilities;
import pl.edu.agh.amber.hokuyo.MapPoint;

import java.util.List;

public class ScanPlayer {


    public static void main(String[] args) {

        List<List<MapPoint>> history = SerializableScanHistory.loadScans("216151328");
        for(int i=0; i<14;i++){
            List<MapPoint> mapPoints = history.get(i);

            //List<List<Integer>> edges = Positioner.findEdges(mapPoints);
            //Positioner.printDistancesToWall(mapPoints, edges);

            final Visualizer demo = new Visualizer("XY Series Demo", mapPoints);
            demo.pack();
            RefineryUtilities.centerFrameOnScreen(demo);
            demo.setVisible(true);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }


}