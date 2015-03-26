package agh.mgr.mecanic;
import org.apache.log4j.Logger;
import pl.edu.agh.amber.common.AmberClient;
import pl.edu.agh.amber.common.CyclicDataListener;
import pl.edu.agh.amber.hokuyo.HokuyoProxy;
import pl.edu.agh.amber.hokuyo.MapPoint;
import pl.edu.agh.amber.hokuyo.Scan;
import pl.edu.agh.amber.roboclaw.MotorsCurrentSpeed;
import pl.edu.agh.amber.roboclaw.RoboclawProxy;

import java.io.*;
import java.util.LinkedList;
import java.util.List;



public class TrackExecutor {
    static Logger log = Logger.getLogger(
            TrackExecutor.class.getName());
    public static void main(String args[]){
        (new TrackExecutor()).startDemo("SAMPLE.txt", 100, 7500);
    }

    public void startDemo(String file, int resolution, int duration_of_show) {

        final AmberClient client;
        try {
            client = new AmberClient("192.168.2.209", 26233);

        } catch (IOException e) {
            System.out.println("Unable to connect to robot: " + e);
            return;
        }
        final RoboclawProxy roboclawProxy = new RoboclawProxy(client, 0);

        SimpleTrack track = new SimpleTrack();
        Transformer transformer = new Transformer();
        //RotationalTrack track = new RotationalTrack();
        //RotationalTransformer transformer = new RotationalTransformer();

        Motion[] tracks = track.getTrack();
        int interval = track.getInterval();

        final String filename =  file;
        final int res = resolution;
        final int duration = duration_of_show/res;

        SpeedLogger speedLogger = new SpeedLogger(res, roboclawProxy, duration, filename);
        Thread speedLoggerThread = new Thread(speedLogger);

        HohujoSkany hohujoskany = new HohujoSkany(client, "/Users/maciejmarczynski/SKAAN.out", 75, 100);
        Thread hohujo = new Thread(hohujoskany);


        // JAZDA


        try {
            //speedLoggerThread.start();
            Thread.sleep(20);
            hohujo.start();
            Thread.sleep(20);

            for(Motion motion: tracks){
                VehicleMotion vehicleMotion = transformer.transform(motion);

                for (int i = 0; i <1; i++) {
                    roboclawProxy.sendMotorsCommand(
                            (int) vehicleMotion.getLeftFront(),
                            (int) vehicleMotion.getRightFront(),
                            (int) vehicleMotion.getLeftBack(),
                            (int) vehicleMotion.getRightBack());
                    Thread.sleep(1500);

                }
            }
            roboclawProxy.sendMotorsCommand(0,0,0,0);
            Thread.sleep(5000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            client.terminate();
        }
    }
}