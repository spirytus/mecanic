package agh.mgr.mecanic;

import pl.edu.agh.amber.roboclaw.MotorsCurrentSpeed;
import pl.edu.agh.amber.roboclaw.RoboclawProxy;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;

public class SpeedLoggerAThread implements Runnable {
    public int resolution;
    public int duration;
    public RoboclawProxy roboclawProxy;
    public String filename;

    public SpeedLoggerAThread(int resolution, RoboclawProxy roboclawProxy, int duration, String filename){
        this.resolution=resolution;
        this.duration=duration;
        this.roboclawProxy=roboclawProxy;
        this.filename=filename;
    }
    @Override
    public void run() {
        //Przechwytywanie !
        LinkedList<MotorsCurrentSpeed> odczyty = new LinkedList<MotorsCurrentSpeed>();
        for(int i = 0; i<duration; i++){
            try {
                Thread.sleep(this.resolution);
                odczyty.add(roboclawProxy.getCurrentMotorsSpeed());
            } catch (IOException e) {
                e.printStackTrace();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            Thread.sleep(2000);
            PrintWriter pw = new PrintWriter(filename, "UTF-8");
            pw.println("Front left, front right, rear right, rear left");
            for(MotorsCurrentSpeed o: odczyty){
                pw.println(o.getFrontLeftSpeed()+ " " + o.getFrontRightSpeed() + " " + o.getRearRightSpeed() + " " + o.getRearLeftSpeed());
            }
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
