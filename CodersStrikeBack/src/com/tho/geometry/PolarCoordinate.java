package com.tho.geometry;

public class PolarCoordinate {

    public double r;
    public Angle theta = new Angle();

    public PolarCoordinate() {
	this(0.0, 0.0);
    }

    public PolarCoordinate(final double initR, final double initTheta) {
	set(initR, initTheta);
    }

    public static PolarCoordinate fromCartesian(final Cartesian2DCoordinate originOfPolarSystemInCartSystem, final Angle angleOfPolarSystemInCartSystem, final Cartesian2DCoordinate point) {
	return new PolarCoordinate(originOfPolarSystemInCartSystem.distance(point), Angle.diff(angleOfPolarSystemInCartSystem.value(), Angle.angle(originOfPolarSystemInCartSystem, point)));
    }

    public void set(final double newR, final double newTheta) {
	this.theta.set(newTheta);
	if (newR < 0) {
	    this.theta.add(180.0);
	    this.r = -newR;
	} else {
	    this.r = newR;
	}
    }

    public void shiftThetaFromChordLength(final int chordLength) {
	this.theta.add(-Math.toDegrees(2 * Math.asin(chordLength / (2 * this.r))));
    }

    public double getChordLength() {
	return 2 * this.r * Math.sin(Math.toRadians(this.theta.value()) / 2);
    }

    public double getR() {
	return this.r;
    }

    public Angle getTheta() {
	return this.theta;
    }

    public void copy(final PolarCoordinate otherPoint) {
	this.r = otherPoint.r;
	this.theta = otherPoint.theta;
    }

    @Override
    public String toString() {
	return "(r=" + this.r + ";theta=" + this.theta + ")";
    }
}
