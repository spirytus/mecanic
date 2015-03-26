import pl.edu.agh.amber.common.AmberClient;
import pl.edu.agh.amber.common.CyclicDataListener;
import pl.edu.agh.amber.hokuyo.HokuyoProxy;
import pl.edu.agh.amber.hokuyo.Scan;

import java.io.IOException;
import java.util.Scanner;


public class HokuyoExample {

    public static void main(String[] args) {
        (new HokuyoExample()).runDemo();
    }

    public void runDemo() {
        Scanner keyboard = new Scanner(System.in);
        System.out.print("IP (default: 192.168.2.209): ");
        String hostname = keyboard.nextLine();

        if ("".equals(hostname)) {
            hostname = "192.168.2.209";
        }

        AmberClient client;
        try {
            client = new AmberClient(hostname, 26233);

        } catch (IOException e) {
            System.out.println("Unable to connect to robot: " + e);
            return;
        }

        HokuyoProxy hokuyoProxy = new HokuyoProxy(client, 0);

        try {

            // Synchronous receiving
            Scan singleScan = hokuyoProxy.getSingleScan();
            System.out.println(singleScan.getPoints());
            System.out.println("Now registering cyclic data listener...");
            Thread.sleep(1000);

            // Asynchronous receiving (with listener)
            hokuyoProxy.registerMultiScanListener(new CyclicDataListener<Scan>() {
                @Override
                public void handle(Scan data) {
                    try {
                        System.out.println(data.getPoints());
                    } catch (Exception e) {
                        System.err.println("Exception occurred: " + e);
                    }
                }
            });

        } catch (IOException e) {
            System.out.println("Error in sending a command: " + e);
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            client.terminate();
        }
    }
}