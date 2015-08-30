package agh.mgr.mecanic;

import agh.mgr.mecanic.data.simple.Utils;
import agh.mgr.mecanic.data.simple.WheelsVelocity;
import pl.edu.agh.amber.roboclaw.RoboclawProxy;

public class WheelsSpeedRunnable implements Runnable {

    private RoboclawProxy roboclawProxy;
    private int intervalInMs;

    public WheelsSpeedRunnable(RoboclawProxy roboclawProxy, int intervalInMs) {
        this.roboclawProxy = roboclawProxy;
        this.intervalInMs = intervalInMs;
    }

    @Override
    public void run() {
        try {
                while(true) {
                    WheelsVelocity currentWheelsVelocity = SharedState.getCurrentWheelsVelocity();
                    if(currentWheelsVelocity != null){
                        System.out.println("USTAWIAM SPIDA" + currentWheelsVelocity);
                        Utils.applyOnRobot(this.roboclawProxy, currentWheelsVelocity);
                    }
                    else {
                        System.out.println("COS SIE WYSRALO, NIE MA JAKIEJ PREDKOSCI USTAWIC");
                    }
                    Thread.sleep(this.intervalInMs);

                }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
