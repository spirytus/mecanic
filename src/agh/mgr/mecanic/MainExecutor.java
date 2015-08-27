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

    // Configurble parameters
    public static double X_SCALE = 1.0;
    public static double Y_SCALE = 1.0;
    public static int SCAN_INTERVAL_IN_MS = 200;
    public static int WHEELS_EXECUTION_IN_MS = 100;
    public static int DECISION_MAKING_INTERVAL = 200;
    public static String IP_OF_ROBOT = "192.168.2.209";
    public static int PORT_OF_ROBOT = 26233;
    public static double ENV_WIDTH_IN_MM = 2500;
    public static double ENV_HEIGHT_IN_MM = 200;
    // EOF configurable parameters

    public static void main(String args[]) {
        AmberClient client = null;
        try {
            client = new AmberClient(IP_OF_ROBOT, PORT_OF_ROBOT);

            final RoboclawProxy roboclawProxy = new RoboclawProxy(client, 0);
            final HokuyoProxy hokuyoProxy = new HokuyoProxy(client, 0);

            Thread hokuyoThread = new Thread(new HokuyoScannerRunnable(hokuyoProxy, SCAN_INTERVAL_IN_MS));
            Thread wheelsSpeedExecutorThread = new Thread(new WheelsSpeedRunnable(roboclawProxy, WHEELS_EXECUTION_IN_MS));
            PoseTrackIterator poseTrackIterator = initPoseTrack();

            hokuyoThread.start();
            wheelsSpeedExecutorThread.start();
            Environment environment = new Environment(ENV_WIDTH_IN_MM, ENV_HEIGHT_IN_MM, 1);

            Thread.sleep(1000); // Niech się inne watki rozbujajo

            //execution BEGINS =================

            boolean achieved = false;

            while(true){
                List<MapPoint> mapPoints = SharedState.getLastScan().getPoints();
                List<List<Integer>> edges = Positioner.findEdges(mapPoints);
                Positioner.printDistancesToWall(mapPoints, edges);

                System.out.println("ODCZYTANO ZE SCIAN, ZASYPIAMY");
                // TODO : 1 Get exact position
                Pose current = poseTrackIterator.getCurrent();
                // TODO : 2 Comparator Do pozycji
                Thread.sleep(DECISION_MAKING_INTERVAL);

            }
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
        poseTrack.addPose(new Pose(2.0*X_SCALE, 5.0*Y_SCALE));
        poseTrack.addPose(new Pose(2.5*X_SCALE, 6.0*Y_SCALE));
        poseTrack.addPose(new Pose(3.0*X_SCALE, 6.6*Y_SCALE));
        poseTrack.addPose(new Pose(3.5*X_SCALE, 7.0*Y_SCALE));
        poseTrack.addPose(new Pose(4.0*X_SCALE, 7.3*Y_SCALE));
        poseTrack.addPose(new Pose(4.5*X_SCALE, 7.0*Y_SCALE));
        poseTrack.addPose(new Pose(7.0*X_SCALE, 5.0*Y_SCALE));
        poseTrack.addPose(new Pose(7.5*X_SCALE, 4.0*Y_SCALE));
        poseTrack.addPose(new Pose(8.0*X_SCALE, 3.4*Y_SCALE));
        poseTrack.addPose(new Pose(8.5*X_SCALE, 3.0*Y_SCALE));
        poseTrack.addPose(new Pose(9.0*X_SCALE, 2.7*Y_SCALE));
        poseTrack.addPose(new Pose(9.5*X_SCALE, 3.0*Y_SCALE));
        poseTrack.addPose(new Pose(7.0*X_SCALE, 5.0*Y_SCALE));
        poseTrack.addPose(new Pose(7.5*X_SCALE, 6.0*Y_SCALE));
        poseTrack.addPose(new Pose(8.0*X_SCALE, 6.6*Y_SCALE));
        poseTrack.addPose(new Pose(8.5*X_SCALE, 7.0*Y_SCALE));
        poseTrack.addPose(new Pose(9.0*X_SCALE, 7.3*Y_SCALE));
        poseTrack.addPose(new Pose(9.5*X_SCALE, 7.0*Y_SCALE));
        poseTrack.addPose(new Pose(2.0*X_SCALE, 5.0*Y_SCALE));
        poseTrack.addPose(new Pose(2.5*X_SCALE, 4.0*Y_SCALE));
        poseTrack.addPose(new Pose(3.0*X_SCALE, 3.4*Y_SCALE));
        poseTrack.addPose(new Pose(3.5*X_SCALE, 3.0*Y_SCALE));
        poseTrack.addPose(new Pose(4.0*X_SCALE, 2.7*Y_SCALE));
        poseTrack.addPose(new Pose(4.5*X_SCALE, 3.0*Y_SCALE));

        return new PoseTrackIterator(poseTrack);
    }
}