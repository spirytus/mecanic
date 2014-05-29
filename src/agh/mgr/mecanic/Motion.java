package agh.mgr.mecanic;

public class Motion {

    private double vx;
    private double vy;
    private double wt;

    public Motion(double Vx, double Vy, double Wt){
        vx = Vx;
        vy = Vy;
        wt = Wt;
    }

    public double getVx() {
        return vx;
    }

    public double getVy() {
        return vy;
    }

    public double getWt() {
        return wt;
    }
}
