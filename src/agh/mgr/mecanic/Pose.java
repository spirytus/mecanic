package agh.mgr.mecanic;

public class Pose {
    private Double x;
    private Double y;
    private Double angle;

    public Pose(Double x, Double y) {
        this.x = x;
        this.y = y;
        this.angle=90.0;
    }



    public Pose(Double x, Double y, Double angle) {
        this.x = x;
        this.y = y;
        this.angle = angle;
    }



    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public Double getAngle() {
        return angle;
    }

    public void setAngle(Double angle) {
        this.angle = angle;
    }


}
