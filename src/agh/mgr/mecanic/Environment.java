package agh.mgr.mecanic;

public class Environment {

    private double width;
    private double height;
    private int relativeWall;


    public Environment(double width, double height, int relativeWall) {
        this.width = width;
        this.height = height;
        this.relativeWall = relativeWall;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public int getRelativeWall() {
        return relativeWall;
    }
}
