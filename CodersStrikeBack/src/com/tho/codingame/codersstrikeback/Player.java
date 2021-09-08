package com.tho.codingame.codersstrikeback;

import java.util.Scanner;

import com.tho.geometry.Cartesian2DCoordinate;

public class Player {

    public static void main(final String args[]) {
	final Scanner in = new Scanner(System.in);
	final Game game = new Game();

	// game loop
	while (true) {
	    final int myPodX = in.nextInt();
	    final int myPodY = in.nextInt();
	    final int checkPointX = in.nextInt();
	    final int checkPointY = in.nextInt();
	    final int checkPointDistanceFromMyPod = in.nextInt();
	    final int checkPointAngleFromMyPod = in.nextInt(); // [-180;180], left is negative
	    final int opponentPodX = in.nextInt();
	    final int opponentPodY = in.nextInt();

//	    System.err.println("Inputs:"
//		    + "\n\t me.x=" + myPodX + " me.y=" + myPodY
//		    + "\n\t cp.x=" + checkPointX + " cp.y=" + checkPointY
//		    + "\n\t distMeCp=" + checkPointDistanceFromMyPod + " cpFromPodAngle=" + checkPointAngleFromMyPod);

	    game.process(new Cartesian2DCoordinate(myPodX, myPodY), new Cartesian2DCoordinate(checkPointX, checkPointY), checkPointDistanceFromMyPod, checkPointAngleFromMyPod, new Cartesian2DCoordinate(opponentPodX, opponentPodY));

	    System.out.println(game.getOutput());
	}
    }
}
