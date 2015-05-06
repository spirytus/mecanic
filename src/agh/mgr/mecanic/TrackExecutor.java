package agh.mgr.mecanic;

import agh.mgr.mecanic.data.simple.IVector;
import agh.mgr.mecanic.data.tracks.BaseTrack;
import agh.mgr.mecanic.data.tracks.SimpleTrack;
import pl.edu.agh.amber.common.AmberClient;
import pl.edu.agh.amber.roboclaw.RoboclawProxy;

import java.io.IOException;

public class TrackExecutor {
    public static void execute(BaseTrack track, int commandResolution) {

        final AmberClient client;
        try {
            client = new AmberClient("192.168.2.209", 26233);
        } catch (IOException e) {
            System.out.println("Unable to connect to robot: " + e);
            return;
        }
        final RoboclawProxy roboclawProxy = new RoboclawProxy(client, 0);

        IVector[] velocityVectors = track.getTrack();
        int interval = track.getInterval();

        try {
            int noOfCommandsToSend = interval / commandResolution;
            for(IVector iVector: velocityVectors){
                for(int i=0;i<noOfCommandsToSend; i++){
                    iVector.applyOnRobot(roboclawProxy);
                    Thread.sleep(commandResolution);
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
