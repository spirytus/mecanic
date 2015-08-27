package agh.mgr.mecanic;

import agh.mgr.mecanic.data.simple.RobotPosition;


public class RobotPositionComparator {

    double angleDelta;
    double coordinantDelta;

    public RobotPositionComparator(double angleDelta, double coordinantDelta) {
        this.angleDelta = angleDelta;
        this.coordinantDelta = coordinantDelta;
    }

    public ComparisonResult comparePosition(RobotPosition current, RobotPosition expected) {

        ComparisonResult comparisonResult = new ComparisonResult();

        if (Math.abs(current.getLastXPosition() - expected.getLastXPosition()) < this.coordinantDelta) {
            comparisonResult.setxCoordOk();
        }
        if (Math.abs(current.getLastYPosition() - expected.getLastYPosition()) < this.coordinantDelta) {
            comparisonResult.setyCoordOk();
        }
        if (Math.abs(current.getLastAngle() - expected.getLastAngle()) < this.coordinantDelta) {
            comparisonResult.setyCoordOk();
        }
        return comparisonResult;
    }

}
