package agh.mgr.mecanic.misc.tools;

import agh.mgr.mecanic.OMGNoEdgesDetectedException;
import agh.mgr.mecanic.Positioner;
import javafx.geometry.Pos;
import org.jfree.ui.RefineryUtilities;
import pl.edu.agh.amber.hokuyo.MapPoint;

import java.util.List;

public class ScanPlayer {


    public static void main(String[] args) {

        List<List<MapPoint>> history = SerializableScanHistory.loadScans("185151911");
        List<MapPoint> mapPoints = history.get(6);

        List<List<Integer>> edges = Positioner.findEdges(mapPoints);
        try {
            List<List<MapPoint>> lists = Positioner.extractWalls(mapPoints, edges);
            int i = 0;
            for (List<MapPoint> list : lists) {
                System.out.println("Wall: " + i++ + list);
            }

        } catch (OMGNoEdgesDetectedException e) {
            e.printStackTrace();
        }

        final Visualizer demo = new Visualizer("XY Series Demo", mapPoints);
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }
}