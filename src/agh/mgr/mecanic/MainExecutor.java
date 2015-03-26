package agh.mgr.mecanic;

import agh.mgr.mecanic.data.simple.VelocityVector;
import agh.mgr.mecanic.data.simple.WheelsVelocity;
import agh.mgr.mecanic.data.tracks.SimpleTrack;
import agh.mgr.mecanic.misc.examples.HohujoSkany;
import org.apache.log4j.Logger;
import pl.edu.agh.amber.common.AmberClient;
import pl.edu.agh.amber.roboclaw.RoboclawProxy;

import java.io.IOException;


public class MainExecutor {
    static Logger log = Logger.getLogger(
            MainExecutor.class.getName());
    public static void main(String args[]){
        (new MainExecutor()).startDemo("SAMPLE.txt", 100, 7500);
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

        VelocityVector[] tracks = track.getTrack();
        int interval = track.getInterval();

        final String filename =  file;
        final int res = resolution;
        final int duration = duration_of_show/res;

        SpeedLoggerAThread speedLogger = new SpeedLoggerAThread(res, roboclawProxy, duration, filename);
        Thread speedLoggerThread = new Thread(speedLogger);

        HohujoSkany hohujoskany = new HohujoSkany(client, "/Users/maciejmarczynski/SKAAN.out", 75, 100);
        Thread hohujo = new Thread(hohujoskany);


        // JAZDA


        try {
            //speedLoggerThread.start();
            Thread.sleep(20);
            hohujo.start();
            Thread.sleep(20);

            for(VelocityVector velocityVector: tracks){
                WheelsVelocity wheelsVelocity = velocityVector.toWheelsVelocity();

                for (int i = 0; i <1; i++) {
                    roboclawProxy.sendMotorsCommand(
                            (int) wheelsVelocity.getLeftFront(),
                            (int) wheelsVelocity.getRightFront(),
                            (int) wheelsVelocity.getLeftBack(),
                            (int) wheelsVelocity.getRightBack());
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