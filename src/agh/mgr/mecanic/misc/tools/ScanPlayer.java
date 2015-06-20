package agh.mgr.mecanic.misc.tools;

import agh.mgr.mecanic.OMGNoEdgesDetectedException;
import agh.mgr.mecanic.Positioner;
import org.apache.commons.math3.stat.regression.SimpleRegression;
import org.jfree.ui.RefineryUtilities;
import pl.edu.agh.amber.hokuyo.MapPoint;

import java.util.List;

public class ScanPlayer {


    public static void main(String[] args) {

        List<List<MapPoint>> history = SerializableScanHistory.loadScans("185151911");
        List<MapPoint> mapPoints = history.get(0);

        List<List<Integer>> edges = Positioner.findEdges(mapPoints);
        try {
            List<List<MapPoint>> walls = Positioner.extractWalls(mapPoints, edges);
            int i = 0;
            for (List<MapPoint> wall : walls) {

                SimpleRegression wallRegression = getWallRegression(wall);

                double a = wallRegression.getSlope();
                double b = wallRegression.getIntercept();

                double A = -a;
                double B = 1;
                double C = -b;

                double distance = Math.abs(C)/Math.sqrt(A*A+B*B);
                System.out.println("DISTANCE " + distance);

            }


        } catch (OMGNoEdgesDetectedException e) {
            e.printStackTrace();
        }

        final Visualizer demo = new Visualizer("XY Series Demo", mapPoints);
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }

    public static SimpleRegression getWallRegression(List<MapPoint> wall) {
        SimpleRegression simpleRegression = new SimpleRegression(true);

        for (MapPoint mapPoint : wall) {
            double x = mapPoint.getDistance() * Math.sin(Math.toRadians(mapPoint.getAngle()));
            double y = mapPoint.getDistance() * Math.cos(Math.toRadians(mapPoint.getAngle()));

            simpleRegression.addData(x, y);
        }

        return simpleRegression;
//        double a = simpleRegression.getSlope();
//        double b = simpleRegression.getIntercept();
    }
}