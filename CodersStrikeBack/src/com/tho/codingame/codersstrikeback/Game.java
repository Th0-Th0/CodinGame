package com.tho.codingame.codersstrikeback;

import com.tho.geometry.Angle;
import com.tho.geometry.Cartesian2DCoordinate;
import com.tho.geometry.PolarCoordinate;

public class Game {

    private static final int CHECKPOINT_RADIUS = 600;
    private static final int POD_SHIELD_RADIUS = 400;

    private final CheckPoints checkPoints = new CheckPoints();
    private final Pod myPod = new Pod();
    private final Pod opponent = new Pod();

    private final Cartesian2DCoordinate targetedPoint = new Cartesian2DCoordinate();

    public void process(final Cartesian2DCoordinate myPodPosition, final Cartesian2DCoordinate checkPoint,
	    final int checkPointDistanceFromMyPod, final int checkPointAngleFromMyPod, final Cartesian2DCoordinate opponentPodPosition) {

	this.myPod.update(myPodPosition, checkPoint, checkPointAngleFromMyPod);
	this.opponent.update(opponentPodPosition, checkPoint);
	this.checkPoints.update(checkPoint);

	final Cartesian2DCoordinate nextCheckPoint = this.checkPoints.next();
	this.targetedPoint.copy(Game.getOptimizedTargetPoint(checkPoint, nextCheckPoint, this.myPod.getCheckpoint().getR()));

	final PolarCoordinate orientedTargetFromPod = PolarCoordinate.fromCartesian(this.myPod.getPosition(), this.myPod.getOrientation(), this.targetedPoint);
	final double targetFromSpeedVectorAngle = Angle.diff(this.myPod.getOrientedSpeedVector().getTheta(), orientedTargetFromPod.getTheta());

	// Control speed direction of the pod
	if (Math.abs(targetFromSpeedVectorAngle) < 80 && Math.abs(targetFromSpeedVectorAngle) > 5) {
	    // Turn more to align speed vector with targeted point
	    this.targetedPoint.setFromPolar(this.myPod.getPosition(), this.myPod.getOrientation(),
		    new PolarCoordinate(orientedTargetFromPod.getR(), orientedTargetFromPod.getTheta().value() + targetFromSpeedVectorAngle));
	}

	// Control thrust of the pod
	final PolarCoordinate checkPointFromSpeedVector = new PolarCoordinate(this.myPod.getCheckpoint().getR(),
		Angle.diff(this.myPod.getOrientedSpeedVector().getTheta(), this.myPod.getCheckpointFromOrientation().getTheta()));
	if (this.myPod.getCheckpoint().getR() <= 3 * Game.CHECKPOINT_RADIUS && Math.abs(checkPointFromSpeedVector.getChordLength()) > Game.CHECKPOINT_RADIUS) {
	    // Near checkpoint and speed vector is not aligned with ckeckPoint zone: lower thrust
	    // this.myPod.getThrust().set((int) (100 * (1.5 - this.myPod.getCheckpointFromOrientation().getChordLength() / Game.CHECKPOINT_RADIUS)));
	    this.myPod.getThrust().set((int) (100.0 * ((5.0 / 4.0) - this.myPod.getCheckpointFromOrientation().getChordLength() / Game.CHECKPOINT_RADIUS)));

	} else if (Math.abs(orientedTargetFromPod.getTheta().value()) > 90) {
	    this.myPod.getThrust().set((int) (100 * ((120 - Math.abs(this.myPod.getCheckpointFromOrientation().getTheta().value())) / (120 - 90))));

	} else {
	    this.myPod.getThrust().set(100);
	}

	// Try to push the opponent
	final double myPodOpponentDistance = this.myPod.getPosition().distance(this.opponent.getPosition());
	if (this.myPod.getCheckpoint().getR() < 5 * Game.CHECKPOINT_RADIUS // Near a checkpoint (= near a turn)
		&& myPodOpponentDistance < 4 * Game.POD_SHIELD_RADIUS) {
	    final PolarCoordinate opponentFromMyPod = new PolarCoordinate(myPodOpponentDistance, Angle.angle(this.myPod.getPosition(), this.opponent.getPosition()) - this.myPod.getOrientation().value());
	    if (this.myPod.getThrust().boostAvailable() && Math.abs(opponentFromMyPod.getChordLength()) < 2 * Game.POD_SHIELD_RADIUS) {
		this.myPod.getThrust().boost();
		this.myPod.getThrust().shield();
	    }
	}
    }

    private static Cartesian2DCoordinate getOptimizedTargetPoint(final Cartesian2DCoordinate checkPoint, final Cartesian2DCoordinate nextCheckPoint, final double podCheckPointDistance) {
	final Cartesian2DCoordinate optiTarget = new Cartesian2DCoordinate();
	// Find the optimized point to reach the checkPoint
	if (nextCheckPoint == null) {
	    // Center of the checkpoint
	    optiTarget.copy(checkPoint);
	} else {
	    // Anticipate turn to next check point
	    if (podCheckPointDistance <= 3 * Game.CHECKPOINT_RADIUS) {
		final PolarCoordinate targetedPointFromCheckPoint = new PolarCoordinate(Game.CHECKPOINT_RADIUS, Angle.angle(checkPoint, nextCheckPoint));
		optiTarget.setFromPolar(checkPoint, new Angle(), targetedPointFromCheckPoint);
	    } else {
		optiTarget.copy(checkPoint);
	    }
	}

	return optiTarget;
    }

    public String getOutput() {
	return this.targetedPoint.getX() + " " + this.targetedPoint.getY() + " " + this.myPod.getThrust().getOutput();
    }
}