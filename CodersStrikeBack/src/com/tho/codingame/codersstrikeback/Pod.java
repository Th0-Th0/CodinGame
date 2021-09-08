package com.tho.codingame.codersstrikeback;

import com.tho.geometry.Angle;
import com.tho.geometry.Cartesian2DCoordinate;
import com.tho.geometry.PolarCoordinate;

public class Pod {

    private final Cartesian2DCoordinate position = new Cartesian2DCoordinate();
    private final PolarCoordinate speedVector = new PolarCoordinate();
    private final PolarCoordinate orientedSpeedVector = new PolarCoordinate();
    private final Angle orientation = new Angle();
    private final PolarCoordinate checkPointFromPosition = new PolarCoordinate();
    private final PolarCoordinate checkPointFromOrientedPos = new PolarCoordinate();
    private final Thrust thrust = new Thrust();

    public Cartesian2DCoordinate getPosition() {
	return this.position;
    }

    public PolarCoordinate getCheckpoint() {
	return this.checkPointFromPosition;
    }

    public PolarCoordinate getCheckpointFromOrientation() {
	return this.checkPointFromOrientedPos;
    }

    public Angle getOrientation() {
	return this.orientation;
    }

    public PolarCoordinate getOrientedSpeedVector() {
	return this.orientedSpeedVector;
    }

    public Thrust getThrust() {
	return this.thrust;
    }

    public void update(final Cartesian2DCoordinate podPosition, final Cartesian2DCoordinate checkPoint) {
	update(podPosition, checkPoint, (int) Math.round(Angle.angle(podPosition, checkPoint)));
    }

    public void update(final Cartesian2DCoordinate podPosition, final Cartesian2DCoordinate checkPoint, final int checkPointAngleFromPodOrientation) {
	this.speedVector.set(this.position.distance(podPosition), Angle.angle(this.position, podPosition));
	this.position.copy(podPosition);

	final double podCheckPointDistance = this.position.distance(checkPoint);
	this.checkPointFromPosition.set(podCheckPointDistance, Angle.angle(this.position, checkPoint));
	this.checkPointFromOrientedPos.set(podCheckPointDistance, checkPointAngleFromPodOrientation);
	this.orientation.set(Angle.diff(this.checkPointFromOrientedPos.getTheta(), this.checkPointFromPosition.getTheta()));

	if (this.speedVector.getR() == 0) {
	    this.speedVector.set(this.orientedSpeedVector.getR(), this.orientation.value());
	}

	this.orientedSpeedVector.set(this.speedVector.getR(), Angle.diff(this.orientation, this.speedVector.getTheta()));
    }

    public void computeCheckPoint(final Cartesian2DCoordinate checkPoint) {
	this.checkPointFromPosition.set(this.position.distance(checkPoint), Angle.angle(this.position, checkPoint));
    }

    @Override
    public String toString() {
	return "\n\t position = " + this.position
		+ "\n\t orientation = " + this.orientation
		+ "\n\t checkPointFromPos = " + this.checkPointFromPosition
		+ "\n\t checkPointFromOrientedPos = " + this.checkPointFromOrientedPos;
    }
}
