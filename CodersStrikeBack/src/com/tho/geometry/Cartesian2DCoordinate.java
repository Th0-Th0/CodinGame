package com.tho.geometry;

/**
 * Coordonnees 2D dans un repere cartesien.
 */
public class Cartesian2DCoordinate {

    private int x, y;

    public Cartesian2DCoordinate() {
	this(0, 0);
    }

    public Cartesian2DCoordinate(final int initX, final int initY) {
	set(initX, initY);
    }

    public int getX() {
	return this.x;
    }

    public int getY() {
	return this.y;
    }

    public void set(final int newX, final int newY) {
	this.x = newX;
	this.y = newY;
    }

    public void copy(final Cartesian2DCoordinate other) {
	set(other.x, other.y);
    }

    public void setFromPolar(final Cartesian2DCoordinate originOfPolarSystemInCartSystem, final Angle angleOfPolarSystemInCartSystem, final PolarCoordinate polarPoint) {
	final PolarCoordinate polarPointWithOffset = new PolarCoordinate(polarPoint.getR(), polarPoint.getTheta().value() + angleOfPolarSystemInCartSystem.value());
	set(originOfPolarSystemInCartSystem.x + (int) Math.round(Cartesian2DCoordinate.getXDistance(polarPointWithOffset)),
		originOfPolarSystemInCartSystem.y + (int) Math.round(Cartesian2DCoordinate.getYDistance(polarPointWithOffset)));
    }

    private static double getXDistance(final PolarCoordinate polarPoint) {
	return Math.cos(Math.toRadians(polarPoint.getTheta().value())) * polarPoint.r;
    }

    private static double getYDistance(final PolarCoordinate polarPoint) {
	return Math.sin(Math.toRadians(polarPoint.getTheta().value())) * polarPoint.r;
    }

    public double distance(final Cartesian2DCoordinate other) {
	return Cartesian2DCoordinate.distance(this.x, this.y, other.x, other.y);
    }

    private static double distance(final int point1X, final int point1Y, final int point2X, final int point2Y) {
	return Math.sqrt((point1X - point2X) * (point1X - point2X) + (point1Y - point2Y) * (point1Y - point2Y));
    }

    @Override
    public String toString() {
	return "(x=" + this.x + ";y=" + this.y + ")";
    }

    @Override
    public boolean equals(final Object obj) {
	return obj instanceof Cartesian2DCoordinate && ((Cartesian2DCoordinate) obj).x == this.x && ((Cartesian2DCoordinate) obj).y == this.y;
    }
}
