package agh.mgr.mecanic.misc.tools;

import agh.mgr.mecanic.Positioner;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import pl.edu.agh.amber.hokuyo.MapPoint;

import java.util.LinkedList;
import java.util.List;

public class Visualizer extends ApplicationFrame {
    public Visualizer(String title, List<MapPoint> mapPointList){
        super(title);
        final XYSeries series = new XYSeries("Random Data");


        List<MapPoint> mapPoints = mapPointList;
        for (int i=1; i<mapPoints.size()-1; i++) {
            MapPoint mapPoint = mapPoints.get(i);
            series.add(mapPoint.getAngle(), mapPoint.getDistance());
         }

        final XYSeriesCollection data = new XYSeriesCollection(series);
        final JFreeChart chart = ChartFactory.createXYLineChart(
                "XY Series Demo",
                "X",
                "Y",
                data,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);
    }
}
