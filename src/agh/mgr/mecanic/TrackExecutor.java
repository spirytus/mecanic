package agh.mgr.mecanic;

import pl.edu.agh.amber.common.AmberClient;
import pl.edu.agh.amber.roboclaw.MotorsCurrentSpeed;
import pl.edu.agh.amber.roboclaw.RoboclawProxy;

import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: maciejmarczynski
 * Date: 17.03.2014
 * Time: 10:26
 * To change this template use File | Settings | File Templates.
 */
public class TrackExecutor {
    public static void main(String args[]){
        (new TrackExecutor()).startDemo();

    }

    public void startDemo() {
        AmberClient client;
        try {
            client = new AmberClient("192.168.2.201", 26233);

        } catch (IOException e) {
            System.out.println("Unable to connect to robot: " + e);
            return;
        }
        RoboclawProxy roboclawProxy = new RoboclawProxy(client, 0);


        SimpleTrack track = new SimpleTrack();
        Transformer transformer = new Transformer();

        Motion[] tracks = track.getTrack();
        int interval = track.getInterval();

        try {
            VehicleMotion motion = transformer.transform(tracks[0]);
            for (int i = 0; i < 100; i++) {

                roboclawProxy.sendMotorsCommand((int) motion.getLeftFront(), (int) motion.getRightFront(), (int) motion.getLeftBack(), (int) motion.getRightBack());
                Thread.sleep(100);

            }
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } finally {
            client.terminate();
        }
    }
}

