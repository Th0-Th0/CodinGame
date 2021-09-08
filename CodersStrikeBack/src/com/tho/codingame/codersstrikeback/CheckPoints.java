package com.tho.codingame.codersstrikeback;

import java.util.ArrayList;
import java.util.List;

import com.tho.geometry.Cartesian2DCoordinate;

public class CheckPoints {

    private final List<Cartesian2DCoordinate> checkPoints = new ArrayList<Cartesian2DCoordinate>();
    private Cartesian2DCoordinate previousCheckPoint;
    private final Cartesian2DCoordinate currentCheckPoint = new Cartesian2DCoordinate();
    private Cartesian2DCoordinate nextCheckPoint;
    private int lap = 1;

    public void update(final Cartesian2DCoordinate checkPoint) {
	if (!this.checkPoints.contains(checkPoint)) {
	    this.checkPoints.add(checkPoint);
	}

	if (this.previousCheckPoint == null) {
	    this.previousCheckPoint = new Cartesian2DCoordinate();
	} else if (!this.currentCheckPoint.equals(this.previousCheckPoint) && this.checkPoints.size() > 1
		&& this.currentCheckPoint.equals(this.checkPoints.get(0))) {
	    this.lap++;
	}

	this.previousCheckPoint.copy(this.currentCheckPoint);
	this.currentCheckPoint.copy(checkPoint);
	if (this.lap > 1) {
	    if (this.nextCheckPoint == null) {
		this.nextCheckPoint = new Cartesian2DCoordinate();
	    }
	    this.nextCheckPoint.copy(this.checkPoints.get((this.checkPoints.indexOf(this.currentCheckPoint) + 1) % this.checkPoints.size()));
	}
    }

    public Cartesian2DCoordinate next() {
	return this.nextCheckPoint;
    }
}
