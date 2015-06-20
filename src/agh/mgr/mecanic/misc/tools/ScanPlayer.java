package agh.mgr.mecanic.misc.tools;

import agh.mgr.mecanic.Positioner;
import org.jfree.ui.RefineryUtilities;
import pl.edu.agh.amber.hokuyo.MapPoint;

import java.util.List;

public class ScanPlayer {


    public static void main(String[] args) {

        List<List<MapPoint>> history = SerializableScanHistory.loadScans("185151911");
        List<MapPoint> mapPoints = history.get(0);

        List<List<Integer>> edges = Positioner.findEdges(mapPoints);
        Positioner.printDistancesToWall(mapPoints, edges);

        final Visualizer demo = new Visualizer("XY Series Demo", mapPoints);
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }


}