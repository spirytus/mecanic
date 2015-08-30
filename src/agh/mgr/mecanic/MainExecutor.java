package agh.mgr.mecanic;

import agh.mgr.mecanic.data.simple.VelocityVector;
import agh.mgr.mecanic.data.simple.WheelsVelocity;
import org.apache.log4j.Logger;
import pl.edu.agh.amber.common.AmberClient;
import pl.edu.agh.amber.hokuyo.HokuyoProxy;
import pl.edu.agh.amber.hokuyo.MapPoint;
import pl.edu.agh.amber.roboclaw.RoboclawProxy;

import java.io.IOException;
import java.util.List;

public class MainExecutor {

    final static Logger logger = Logger.getLogger(MainExecutor.class);

    // Configurble parameters
    public static double X_SCALE = 158.6;
    public static double Y_SCALE = 228;
    public static int SCAN_INTERVAL_IN_MS = 500;
    public static int WHEELS_EXECUTION_IN_MS = 1000;
    public static int DECISION_MAKING_INTERVAL = 400;
    public static String IP_OF_ROBOT = "192.168.2.209";
    public static int PORT_OF_ROBOT = 26233;
    public static double ENV_WIDTH_IN_MM = 2360;
    public static double ENV_HEIGHT_IN_MM = 2260;
    public static double POSE_COMPARATOR_ERROR_ACCEPTED = 30.0;

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

            Thread.sleep(2000); // Niech się inne watki rozbujajo

            //execution BEGINS =================

            boolean achieved = false;

            PoseComparator poseComparator = new PoseComparator(POSE_COMPARATOR_ERROR_ACCEPTED);

            List<MapPoint> scannedMapPoints = null;
            List<List<Integer>> edges = null;
            Pose currentRobotPose = null;
            Pose expectedTrackPose = poseTrackIterator.getCurrent();

            while(true){
                scannedMapPoints = SharedState.getLastScan();
                edges = Positioner.findEdges(scannedMapPoints);
                currentRobotPose =  Positioner.getCurrentPose(scannedMapPoints, edges, environment);
                logger.debug("Current pose" + currentRobotPose);
                logger.debug("Expected pose" + expectedTrackPose);
                if(!poseComparator.compare(currentRobotPose, expectedTrackPose)){
                    reconfigureSpeed(currentRobotPose, expectedTrackPose);
                    Thread.sleep(DECISION_MAKING_INTERVAL);
                }
                else {
                    expectedTrackPose = poseTrackIterator.getNext(); // tu nie ma na co czekac, trzeba od razu kierowac sie na kolejnego pose'a
                }
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
    private static void reconfigureSpeed(Pose currentRobotPose, Pose expectedTrackPose){

        double dx = expectedTrackPose.getX() - currentRobotPose.getX();
        double dy = expectedTrackPose.getY() - currentRobotPose.getY();
        double dWt = expectedTrackPose.getAngle() - currentRobotPose.getAngle();

        double ratio = dx/dy;

        double BASE_SPEED = 650.0;

        double Vx = BASE_SPEED * ratio;
        double Vy = BASE_SPEED * 1/ratio;

        double BASIC_ROTATE = 10.0;
        //double ROTATE_MAGIC = 1.0;

        double distanceToDestination = Math.sqrt(dx*dx+dy*dy);

        double Wt = BASIC_ROTATE/distanceToDestination*dWt;

        System.out.printf("Setting vx %f vy %f dt %f", dx, dy, dWt);
        VelocityVector velocityVector = new VelocityVector(Vx, Vy, Wt);
        SharedState.setCurrentWheelsVelocity(velocityVector.toWheelsVelocity());
        
    }
    private static PoseTrackIterator initPoseTrack() {

        double gradient = 90.0;
        PoseTrack poseTrack = new PoseTrack(gradient);
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