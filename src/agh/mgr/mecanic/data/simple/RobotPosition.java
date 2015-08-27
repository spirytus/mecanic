package agh.mgr.mecanic.data.simple;

import pl.edu.agh.amber.location.LocationCurrent;

public class RobotPosition {
    private double lastXPosition;
    private double lastYPosition;
    private double lastAngle;

    private double mapXinMm;
    private double mapYinMm;

    private double lastLFspeed;
    private double lastRFspeed;
    private double lastRBspeed;
    private double lastLBspeed;

    public RobotPosition(){}

    public RobotPosition(LocationCurrent locationCurrent){
        try {
            this.lastXPosition = locationCurrent.getX();
            this.lastYPosition = locationCurrent.getY();
            this.lastAngle = Utils.normalizeAndToDegrees(locationCurrent.getAngle());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public RobotPosition(double lastXPosition, double lastYPosition, double lastAngleInDegrees){
        this.lastXPosition = lastXPosition;
        this.lastYPosition = lastYPosition;
        this.lastAngle = lastAngleInDegrees;
    }
    public RobotPosition(double lastXPosition, double lastYPosition, double lastAngle, double mapXinMm, double mapYinMm, double lastLFspeed, double lastRFspeed, double lastRBspeed, double lastLBspeed) {
        this.lastXPosition = lastXPosition;
        this.lastYPosition = lastYPosition;
        this.lastAngle = lastAngle;
        this.mapXinMm = mapXinMm;
        this.mapYinMm = mapYinMm;
        this.lastLFspeed = lastLFspeed;
        this.lastRFspeed = lastRFspeed;
        this.lastRBspeed = lastRBspeed;
        this.lastLBspeed = lastLBspeed;
    }
    public double getLastXPosition() {
        return lastXPosition;
    }

    public void setLastXPosition(double lastXPosition) {
        this.lastXPosition = lastXPosition;
    }

    public double getLastYPosition() {
        return lastYPosition;
    }

    public void setLastYPosition(double lastYPosition) {
        this.lastYPosition = lastYPosition;
    }

    public double getLastAngle() {
        return lastAngle;
    }

    public void setLastAngle(double lastAngle) {
        this.lastAngle = lastAngle;
    }

    public double getMapXinMm() {
        return mapXinMm;
    }

    public void setMapXinMm(double mapXinMm) {
        this.mapXinMm = mapXinMm;
    }

    public double getMapYinMm() {
        return mapYinMm;
    }

    public void setMapYinMm(double mapYinMm) {
        this.mapYinMm = mapYinMm;
    }

    public double getLastLFspeed() {
        return lastLFspeed;
    }

    public void setLastLFspeed(double lastLFspeed) {
        this.lastLFspeed = lastLFspeed;
    }

    public double getLastRFspeed() {
        return lastRFspeed;
    }

    public void setLastRFspeed(double lastRFspeed) {
        this.lastRFspeed = lastRFspeed;
    }

    public double getLastRBspeed() {
        return lastRBspeed;
    }

    public void setLastRBspeed(double lastRBspeed) {
        this.lastRBspeed = lastRBspeed;
    }

    public double getLastLBspeed() {
        return lastLBspeed;
    }

    public void setLastLBspeed(double lastLBspeed) {
        this.lastLBspeed = lastLBspeed;
    }
}
