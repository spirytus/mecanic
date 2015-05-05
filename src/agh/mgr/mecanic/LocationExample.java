package agh.mgr.mecanic;

import agh.mgr.mecanic.data.simple.PositionContext;
import agh.mgr.mecanic.data.simple.Utils;
import pl.edu.agh.amber.common.AmberClient;
import pl.edu.agh.amber.hokuyo.HokuyoProxy;
import pl.edu.agh.amber.location.LocationProxy;
import pl.edu.agh.amber.location.LocationCurrent;
import pl.edu.agh.amber.roboclaw.RoboclawProxy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Location proxy example.
 *
 * @author szsz <szsz@agh.edu.pl>
 */
public class LocationExample {

    public static void main(String[] args) {
        (new LocationExample()).runDemo();
    }
    public static double normalizeAngle(double angleInRad){
        return angleInRad - (2 * Math.PI) * Math.floor((angleInRad +
                Math.PI) / (2 * Math.PI));
    }
    public void runDemo() {
        Scanner keyboard = new Scanner(System.in);
        System.out.print("IP (default: 192.168.2.209): ");
        String hostname = keyboard.nextLine();

        if ("".equals(hostname)) {
            hostname = "192.168.2.209";
        }

        AmberClient client;
        RoboclawProxy roboclawProxy;
        HokuyoProxy hokuyoProxy;
        try {
            client = new AmberClient(hostname, 26233);
            roboclawProxy = new RoboclawProxy(client, 0);
            hokuyoProxy = new HokuyoProxy(client, 0);

        } catch (IOException e) {
            System.out.println("Unable to connect to robot: " + e);
            return;
        }

        LocationProxy locationProxy = new LocationProxy(client, 0);
        try {

            LocationCurrent lok = locationProxy.getCurrentLocation();
            lok.waitAvailable();

            System.out.println(String.format("Current location: X: %s, Y: %s, Alfa: %s, In degree %s P: %s, TimeStamp: %s",
                    lok.getX(), lok.getY(), lok.getAngle(), Utils.normalizeAndToDegrees(lok.getAngle()),
                    lok.getP(), lok.getTimeStamp()));

            PositionContext current = new PositionContext();
            PositionContext destination = new PositionContext(0.48, 3.0, 40.0);

            ToPoint.rotateFirst(current, destination, roboclawProxy, locationProxy, hokuyoProxy);
        } catch (IOException e) {
            System.out.println("Error in sending a command: " + e);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            client.terminate();
        }
    }

    /* Example upload JSON map to robot
    String tempMapJSON = readMapFromFile("<FileName.roson>");
    locationProxy.UploadMap(tempMapJSON);
     */
    private String readMapFromFile(String sPath) throws IOException
    {
        byte[] encoded = Files.readAllBytes(Paths.get(sPath));
        return new String(encoded);
    }
}
