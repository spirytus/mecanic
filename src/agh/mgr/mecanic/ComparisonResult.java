package agh.mgr.mecanic;

public class ComparisonResult {
    private boolean xCoordOk;
    private boolean yCoordOk;
    private boolean angleOk;
    private boolean incorrect;

    public ComparisonResult(){
        this.xCoordOk = false;
        this.yCoordOk = false;
        this.angleOk = false;
        this.incorrect = true;
    }

    public boolean isxCoordOk() {
        return xCoordOk;
    }

    public void setxCoordOk() {
        this.xCoordOk = true;
        this.incorrect = false;
    }

    public boolean isyCoordOk() {
        return yCoordOk;
    }

    public void setyCoordOk() {
        this.yCoordOk = true;
        this.incorrect = false;
    }

    public boolean isAngleOk() {
        return angleOk;
    }

    public void setAngleOk() {
        this.angleOk = true;
        this.incorrect = false;
    }

    public boolean isIncorrect() {
        return incorrect;
    }

    public void setIncorrect(boolean incorrect) {
        this.incorrect = incorrect;
    }
}
