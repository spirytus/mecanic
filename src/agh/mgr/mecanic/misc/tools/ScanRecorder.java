package agh.mgr.mecanic.misc.tools;

import pl.edu.agh.amber.common.AmberClient;
import pl.edu.agh.amber.hokuyo.HokuyoProxy;
import pl.edu.agh.amber.hokuyo.MapPoint;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class ScanRecorder {
    public static void main(String[] args) {
        (new ScanRecorder()).startRecording();
    }

    private void startRecording() {
        Scanner keyboard = new Scanner(System.in);
        System.out.print("IP (default: 192.168.2.209): ");
        String hostname = keyboard.nextLine();

        if ("".equals(hostname)) {
            hostname = "192.168.2.209";
        }

        AmberClient client;
        HokuyoProxy hokuyoProxy;
        try {
            client = new AmberClient(hostname, 26233);
            hokuyoProxy = new HokuyoProxy(client, 0);
            System.out.println("Podaj nazwe pliku wyjsciowego:");
            String filename = keyboard.nextLine();
            System.out.println("ScanRecorder. a - dodaj skan. x. zapisz i wyjdz. Podaj nazwe pliku wyjsciowego:");
            String line = keyboard.nextLine();
            SerializableScanHistory serializableScanHistory = new SerializableScanHistory();

            while(!"x".equals(line)){
                if("a".equals(line)){
                    List<MapPoint> points = hokuyoProxy.getSingleScan().getPoints();
                    System.out.println(points);
                    System.out.println("Were added");
                    serializableScanHistory.add(points);
                }
                line = keyboard.nextLine();
            }
            SerializableScanHistory.dumpScans(serializableScanHistory, filename);

        } catch (IOException e) {
            System.out.println("Unable to connect to robot: " + e);
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
