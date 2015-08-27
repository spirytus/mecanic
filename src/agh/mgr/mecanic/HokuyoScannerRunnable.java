package agh.mgr.mecanic;

import pl.edu.agh.amber.hokuyo.HokuyoProxy;
import pl.edu.agh.amber.hokuyo.Scan;

import java.io.IOException;

public class HokuyoScannerRunnable implements Runnable {

    private int scanInterval;
    private HokuyoProxy hokuyoProxy;

    public HokuyoScannerRunnable(HokuyoProxy hokuyoProxy, int scanInterval){
        this.hokuyoProxy = hokuyoProxy;
        this.scanInterval = scanInterval;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Scan singleScan = hokuyoProxy.getSingleScan();
                // Let's tag it at some point sSystem.currentTimeMillis();
                SharedState.setLastScan(singleScan);

                Thread.sleep(this.scanInterval);

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
