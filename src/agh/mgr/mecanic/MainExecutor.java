package agh.mgr.mecanic;

import agh.mgr.mecanic.data.simple.IVector;
import agh.mgr.mecanic.data.tracks.PaperTrack;
import pl.edu.agh.amber.common.AmberClient;
import pl.edu.agh.amber.hokuyo.HokuyoProxy;
import pl.edu.agh.amber.hokuyo.MapPoint;
import pl.edu.agh.amber.roboclaw.RoboclawProxy;

import java.io.IOException;
import java.util.List;

public class MainExecutor {
    public static void main(String args[]) {
        AmberClient client = null;
        try {
            // Configurble parameters
            int SCAN_INTERVAL_IN_MS = 200;
            int WHEELS_EXECUTION_IN_MS = 100;
            String IP_OF_ROBOT = "192.168.2.209";
            int PORT_OF_ROBOT = 26233;
            // EOF configurable parameters


            client = new AmberClient(IP_OF_ROBOT, PORT_OF_ROBOT);

            final RoboclawProxy roboclawProxy = new RoboclawProxy(client, 0);
            final HokuyoProxy hokuyoProxy = new HokuyoProxy(client, 0);

            Thread hokuyoThread = new Thread(new HokuyoScannerRunnable(hokuyoProxy, SCAN_INTERVAL_IN_MS));
            Thread wheelsSpeedExecutorThread = new Thread(new WheelsSpeedRunnable(roboclawProxy, WHEELS_EXECUTION_IN_MS));
            PoseTrackIterator poseTrackIterator = initPoseTrack();
            hokuyoThread.start();

            wheelsSpeedExecutorThread.start();

            //execution BEGINS =================
            List<MapPoint> mapPoints = null;

            mapPoints = SharedState.lastScan.getPoints();

            List<List<Integer>> edges = Positioner.findEdges(mapPoints);
            Positioner.printDistancesToWall(mapPoints, edges);
// kolejne leftoversys    public static void execute(BaseTrack track, int commandResolution) {


            //IVector[] velocityVectors = track.getTrack();
            //int interval = track.getInterval();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            client.terminate();
        }
    }

    private static PoseTrackIterator initPoseTrack() {

        PoseTrack poseTrack = new PoseTrack();
        poseTrack.addPose(new Pose());

        return null;
    }
}