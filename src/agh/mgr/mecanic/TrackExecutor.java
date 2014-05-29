package agh.mgr.mecanic;
import org.apache.log4j.Logger;
import pl.edu.agh.amber.common.AmberClient;
import pl.edu.agh.amber.roboclaw.RoboclawProxy;

import java.io.IOException;


public class TrackExecutor {
    static Logger log = Logger.getLogger(
            TrackExecutor.class.getName());
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

            for (int i = 0; i < 30; i++) {
                Motion motion = tracks[0];
//                Motion newMotion = new Motion(motion.getVy()* Math.sin(Math.toRadians(Properties.ANGLE*i)),
//                        motion.getVy()* Math.cos(Math.toRadians(Properties.ANGLE*i)),
//                        motion.getWt());
                System.out.println(Properties.ANGLE*i);
                //VehicleMotion vehicleMotion = transformer.transform(newMotion);
                VehicleMotion vehicleMotion = transformer.transform(motion);
                roboclawProxy.sendMotorsCommand((int) vehicleMotion.getLeftFront(), (int) vehicleMotion.getRightFront(), (int) vehicleMotion.getLeftBack(), (int) vehicleMotion.getRightBack());
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

