package agh.mgr.mecanic.misc.examples;

import agh.mgr.mecanic.data.SerializableScanHistory;
import pl.edu.agh.amber.common.AmberClient;
import pl.edu.agh.amber.hokuyo.HokuyoProxy;
import pl.edu.agh.amber.hokuyo.MapPoint;
import pl.edu.agh.amber.hokuyo.Scan;

import java.io.IOException;
import java.util.List;


public class HohujoSkany implements Runnable {
    public AmberClient client;
    public String dumpfile;
    public int resolution;
    public int repetitions;

    public HohujoSkany(AmberClient client, String dumpfile, int repetitions, int resolution){

        this.client=client;
        this.dumpfile=dumpfile;
        this.repetitions=repetitions;
        this.resolution=resolution;

    }

    @Override
    public void run() {
        HokuyoProxy hokuyoProxy = new HokuyoProxy(client, 0);
//        try {
//            hokuyoProxy.registerMultiScanListener(new CyclicDataListener<Scan>() {
//                @Override
//                public void handle(Scan data) {
//                    try {
//                        System.out.println(data.getPoints());
//                    } catch (Exception e) {
//                        System.err.println("Exception occurred: " + e);
//                    }
//                }
//            });
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        SerializableScanHistory skany = new SerializableScanHistory();
        for(int i=0; i<this.repetitions-10; i++){
            try {
                System.out.println("DODAJE PKTY");

                Scan singleScan = hokuyoProxy.getSingleScan();
                System.out.println("DODALEM PKTY");
                List<MapPoint> points = singleScan.getPoints();
                System.out.println("DODAJE PKTY");
                skany.add(points);
                Thread.sleep(this.resolution);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                System.out.println("ZRZUCAM PKTY");
                SerializableScanHistory.dumpScans(skany, this.dumpfile);
            }
        }


    }
}
