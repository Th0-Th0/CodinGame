package com.tho.geometry;

public class Angle {

    public double angle;

    public void set(final double newAngle) {
	this.angle = Angle.fold(newAngle);
    }

    public void add(final double angleToAdd) {
	set(this.angle + angleToAdd);
    }

    public double value() {
	return this.angle;
    }

    public static double diff(final Angle angle1, final Angle angle2) {
	return Angle.diff(angle1.angle, angle2.angle);
    }

    public static double diff(final double angle1, final double angle2) {
	return Angle.fold(angle2 - angle1);
    }

    public static double angle(final Cartesian2DCoordinate point1, final Cartesian2DCoordinate point2) {
	return Angle.angle(point1.getX(), point1.getY(), point2.getX(), point2.getY());
    }

    public static double angle(final Cartesian2DCoordinate point1, final int point2X, final int point2Y) {
	return Angle.angle(point1.getX(), point1.getY(), point2X, point2Y);
    }

    public static double angle(final int point1X, final int point1Y, final Cartesian2DCoordinate point2) {
	return Angle.angle(point1X, point1Y, point2.getX(), point2.getY());
    }

    public static double angle(final int point1X, final int point1Y, final int point2X, final int point2Y) {
	return Angle.fold(Math.toDegrees(Math.atan2(point2Y - point1Y, point2X - point1X)));
    }

    private static double fold(final double angleToFold) {
	double foldedAngle = angleToFold;
	while (foldedAngle <= -180.0) {
	    foldedAngle += 360.0;
	}
	while (foldedAngle > 180.0) {
	    foldedAngle -= 360.0;
	}
	return foldedAngle;
    }

    @Override
    public String toString() {
	return String.valueOf(this.angle);
    }
}
