package com.tho.codingame.customgameoflife;

import java.util.ArrayList;
import java.util.List;

public class Cell {
	private boolean alive;
	private final List<Cell> neighbours;

	public Cell() {
		this.alive = false;
		this.neighbours = new ArrayList<Cell>();
	}

	public void addNeighbour(final Cell cell) {
		this.neighbours.add(cell);
	}

	public void setAlive() {
		this.alive = true;
	}

	public boolean isAlive() {
		return this.alive;
	}

	public int getNbNeighboursAlive() {
		int nb = 0;
		for (final Cell cell : this.neighbours) {
			if (cell.isAlive()) {
				nb++;
			}
		}
		return nb;
	}

	@Override
	public String toString() {
		return this.alive ? "O" : ".";
	}
}