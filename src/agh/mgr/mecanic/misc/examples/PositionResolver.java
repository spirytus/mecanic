package agh.mgr.mecanic.misc.examples;

import agh.mgr.mecanic.misc.tools.SerializableScanHistory;
import pl.edu.agh.amber.common.AmberClient;
import pl.edu.agh.amber.hokuyo.HokuyoProxy;
import pl.edu.agh.amber.hokuyo.MapPoint;
import pl.edu.agh.amber.hokuyo.Scan;

import java.io.IOException;
import java.util.List;


public class PositionResolver implements Runnable {
    public AmberClient client;
    public String dumpfile;
    public int resolution;
    public int repetitions;

    public PositionResolver(AmberClient client, String dumpfile, int repetitions, int resolution){

        this.client=client;
        this.dumpfile=dumpfile;
        this.repetitions=repetitions;
        this.resolution=resolution;

    }

    @Override
    public void run() {
        HokuyoProxy hokuyoProxy = new HokuyoProxy(client, 0);

        SerializableScanHistory skany = new SerializableScanHistory();

        for(int i=0; i<this.repetitions-10; i++){
            try {
                Scan singleScan = hokuyoProxy.getSingleScan();
                List<MapPoint> points = singleScan.getPoints();
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
                SerializableScanHistory.dumpScans(skany, this.dumpfile);
            }
        }


    }
}
