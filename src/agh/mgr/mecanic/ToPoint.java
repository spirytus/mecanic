package agh.mgr.mecanic;

import agh.mgr.mecanic.data.simple.RobotPosition;
import agh.mgr.mecanic.data.simple.Utils;
import agh.mgr.mecanic.data.simple.VelocityVector;
import pl.edu.agh.amber.hokuyo.HokuyoProxy;
import pl.edu.agh.amber.location.LocationCurrent;
import pl.edu.agh.amber.location.LocationProxy;
import pl.edu.agh.amber.roboclaw.RoboclawProxy;

import java.io.IOException;
import java.util.Scanner;

public class ToPoint {

    public static void main(String[] args){
        double angleInRad = 3.236455969027091;
        double angleInRad2 = 3.433466869881223;

        System.out.println(Math.toDegrees(Utils.normalizeAndToDegrees(angleInRad)));
        System.out.println(Math.toDegrees(Utils.normalizeAndToDegrees(angleInRad2)));


    }
    public static void rotateFirst(RobotPosition locationCurrent, RobotPosition locationDesired, RoboclawProxy roboclawProxy, LocationProxy locationProxy, HokuyoProxy hokuyoProxy){
        //RoboclawProxy speed
        //LocationProxy
        //1. sprawdz roznice w stopniach i niech się zacznie kręcić
        VelocityVector velVector = null;
        try {
            double angleCurrent = locationCurrent.getLastAngle();
            double angleDesired = locationDesired.getLastAngle(); // TODO: ASAP To chyba nie tak miało wyglądać
            double angleDifference = angleDesired - angleCurrent;

            double direction = angleDifference > 0 ? -1.0 : 1.0;

            double  rotationSpeed = 10 * direction;
            velVector = new VelocityVector(0, 0, rotationSpeed);
            // velVector.applyOnRobot(roboclawProxy);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //2. Obserwuj, aż różnica będzie mniejsza od jakiejś delty
        // TODO: ASAP Wczytywanie propertiesów z pliku
        // TODO: Wniosek - częste resety silników przy gęstych odczytów z location
        double delta = 7;
        long napTime = 100;



        try {
            RobotPosition currentLocation = new RobotPosition(locationProxy.getCurrentLocation());
            System.out.println("Desired angle :" + locationDesired.getLastAngle());
            LocationCurrent locationCurrent1 = null;
            while(Math.abs(currentLocation.getLastAngle() - locationDesired.getLastAngle()) > delta){
                System.out.println("x aby isc dalej, a zeby zeskanowac pozycjes");

                Scanner keyboard = new Scanner(System.in);

                String input = keyboard.nextLine();
                while(!"x".equals(input)){
                    if("a".equals(input)){
                        locationCurrent1 = locationProxy.getCurrentLocation();
                        locationCurrent1.waitAvailable();
                        currentLocation = new RobotPosition(locationCurrent1);

                        System.out.println(String.format("Current location: X: %s, Y: %s, Alfa: %s, Degree: %s, P: %s, TimeStamp: %s",
                                locationCurrent1.getX(), locationCurrent1.getY(), locationCurrent1.getAngle(), Utils.normalizeAndToDegrees(locationCurrent1.getAngle()),
                                locationCurrent1.getP(), locationCurrent1.getTimeStamp()));
                        //System.out.println(hokuyoProxy.getSingleScan().getPoints());

                    }
                    input = keyboard.nextLine();
                }
               // System.out.println("Current angle: " + currentLocation.getLastAngle() + " Delta: " + delta);
                for(int i=0; i<20; i++){
                    velVector.applyOnRobot(roboclawProxy);
                    Thread.sleep(napTime);
                }
                VelocityVector velocityVector = new VelocityVector(0,0,0);
                velocityVector.applyOnRobot(roboclawProxy);
                Thread.sleep(5*napTime);

                locationCurrent1 = locationProxy.getCurrentLocation();
                locationCurrent1.waitAvailable();
                currentLocation = new RobotPosition(locationCurrent1);

                System.out.println(String.format("Current location: X: %s, Y: %s, Alfa: %s, Degree: %s, P: %s, TimeStamp: %s",
                        locationCurrent1.getX(), locationCurrent1.getY(), locationCurrent1.getAngle(), Utils.normalizeAndToDegrees(locationCurrent1.getAngle()),
                        locationCurrent1.getP(), locationCurrent1.getTimeStamp()));

                //System.out.println("CURRENT Location" + locationCurrent1.toString());



            }
//WNIOSEK - samo obracanie nie jest zbyt spoko

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //3. Wylicz wektor. Zaaplikuj.
        // TODO: Tu nie zawsze musi byc sukces !! Moze sie zdarzyc, ze znowu bedzie go trzeba obrócic

        //4. Zatrzymaj, jak będzie blisko tamtej pozycji
    }

    // TODO: Czy w metodzie 2 nie pomoże lepsze samplowanie ?? !! Takie gdzie pozycja nie bedzie sie specjalnie roznić ?
    public static void oneVector(){}
}
