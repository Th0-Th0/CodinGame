package com.tho.codingame.codersstrikeback;

public class Thrust {

    private boolean boostAvailable = true;
    private boolean boost;
    private boolean shieldAvailable = true;
    private boolean shield;
    private int thrust; // 0 to 100;

    public void boost() {
	if (this.boostAvailable) {
	    this.boost = true;
	    this.boostAvailable = false;
	}
    }

    public boolean boostAvailable() {
	return this.boostAvailable;
    }

    public void shield() {
	if (this.shieldAvailable) {
	    this.shield = true;
	    this.shieldAvailable = false;
	}
    }

    public boolean shieldAvailable() {
	return this.shieldAvailable;
    }

    public void set(final int thrust) {
	this.thrust = Math.min(Math.max(0, thrust), 100);
    }

    public String getOutput() {
	if (this.boost) {
	    this.boost = false;
	    return "BOOST";
	} else if (this.shield) {
	    this.shield = false;
	    return "SHIELD";
	}
	return String.valueOf(this.thrust);
    }
}
