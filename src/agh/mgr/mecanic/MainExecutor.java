package agh.mgr.mecanic;

import agh.mgr.mecanic.data.simple.IVector;
import agh.mgr.mecanic.data.simple.VelocityVector;
import agh.mgr.mecanic.data.simple.WheelsVelocity;
import agh.mgr.mecanic.data.tracks.SimpleTrack;
import agh.mgr.mecanic.misc.examples.PositionResolver;
import org.apache.log4j.Logger;
import pl.edu.agh.amber.common.AmberClient;
import pl.edu.agh.amber.roboclaw.RoboclawProxy;

import java.io.IOException;

public class MainExecutor {
    static Logger log = Logger.getLogger(MainExecutor.class.getName());
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

        IVector[] velocityVectors = track.getTrack();
        int interval = track.getInterval();
        int commandIntervalInMs= 100;

        try {
            int noOfCommandsToSend = interval / commandIntervalInMs;
            for(IVector iVector: velocityVectors){
                for(int i=0;i<noOfCommandsToSend; i++){
                    iVector.applyOnRobot(roboclawProxy);
                    Thread.sleep(commandIntervalInMs);
                }
                roboclawProxy.sendMotorsCommand(0,0,0,0);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            client.terminate();
        }
    }
}