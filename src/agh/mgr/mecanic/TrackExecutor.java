package agh.mgr.mecanic;
import org.apache.log4j.Logger;
import pl.edu.agh.amber.common.AmberClient;
import pl.edu.agh.amber.roboclaw.RoboclawProxy;

import java.io.IOException;

// 1. Jakies sensowne demo, trasy przod, tyl, bok, skos, wirowanie
// obrot przodem, obrot bokiem, obrot 45 stopni, obrot pod pewnym nachyleniem

// 1.5 demo ośmiokąt -> może z tego można wykmninić jak zbliżyć się do takiej jazdy w okręgu

// 2. Jas konwersja 60 stopni -> wektor X,Y i może np niech ten wektor rotuje w czasie ?! Oł yea !!!



public class TrackExecutor {
    static Logger log = Logger.getLogger(
            TrackExecutor.class.getName());
    public static void main(String args[]){
        (new TrackExecutor()).startDemo();

    }

    public void startDemo() {

        AmberClient client;
        try {
            client = new AmberClient("192.168.2.207", 26233);

        } catch (IOException e) {
            System.out.println("Unable to connect to robot: " + e);
            return;
        }
        RoboclawProxy roboclawProxy = new RoboclawProxy(client, 0);

        // 1. Simple <- -> ^ v @
        SimpleTrack track = new SimpleTrack();
        Transformer transformer = new Transformer();

        // 2. Rotational track
        //RotationalTrack track = new RotationalTrack();
        //RotationalTransformer transformer = new RotationalTransformer();


        Motion[] tracks = track.getTrack();
        int interval = track.getInterval();

        try {
            for(Motion motion: tracks){
                VehicleMotion vehicleMotion = transformer.transform(motion);
                System.out.println(vehicleMotion);
                for (int i = 0; i < 5; i++) {
                    System.out.println((int) vehicleMotion.getLeftFront()+" "+(int) vehicleMotion.getRightFront()+" "+(int) vehicleMotion.getLeftBack() +" "+ (int) vehicleMotion.getRightBack());
                    roboclawProxy.sendMotorsCommand((int) vehicleMotion.getLeftFront(), (int) vehicleMotion.getRightFront(), (int) vehicleMotion.getLeftBack(), (int) vehicleMotion.getRightBack());
                    //roboclawProxy.sendMotorsCommand(-200,100,100,-100);   to
                    //roboclawProxy.sendMotorsCommand(-100,200,100,-100);   i to w jedna strone kreci
                    //roboclawProxy.sendMotorsCommand(-100,100,200,-100);  to
                    //roboclawProxy.sendMotorsCommand(-100,100,100,-200);      i to w druga
                    //roboclawProxy.sendMotorsCommand(-150,100,100,-100);
                   // roboclawProxy.sendMotorsCommand(0,0,0,0);
                    Thread.sleep(400);

                }
            }

        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } finally {
            client.terminate();
        }
    }
}

