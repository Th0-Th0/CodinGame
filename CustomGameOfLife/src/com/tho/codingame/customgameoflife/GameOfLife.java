package com.tho.codingame.customgameoflife;

import java.util.ArrayList;
import java.util.List;

public class GameOfLife {
	private final List<Integer> survivingConditions;
	private final List<Integer> birthConditions;
	private final List<CellularAutomaton> states;

	public GameOfLife(final List<Integer> survivingConditions, final List<Integer> birthConditions) {
		this.survivingConditions = survivingConditions;
		this.birthConditions = birthConditions;
		this.states = new ArrayList<>();
	}

	public CellularAutomaton getLastState() {
		return this.states.get(this.states.size() - 1);
	}

	public void run(final CellularAutomaton initState, final int nbTurns) {
		this.states.clear();
		this.states.add(initState);
		for (int i = 0; i < nbTurns; i++) {
			this.states.add(computeNextState());
		}
	}

	private CellularAutomaton computeNextState() {
		final CellularAutomaton currentState = getLastState();
		final CellularAutomaton nextState = new CellularAutomaton(currentState.getHeight(), currentState.getWidth());

		for (int rowIndex = 0; rowIndex < currentState.getHeight(); rowIndex++) {
			for (int columnIndex = 0; columnIndex < currentState.getWidth(); columnIndex++) {
				if (currentState.getCell(rowIndex, columnIndex).isAlive()
						&& this.survivingConditions
								.contains(currentState.getCell(rowIndex, columnIndex).getNbNeighboursAlive())
						|| !currentState.getCell(rowIndex, columnIndex).isAlive() && this.birthConditions
								.contains(currentState.getCell(rowIndex, columnIndex).getNbNeighboursAlive())) {
					nextState.getCell(rowIndex, columnIndex).setAlive();
				}

				nextState.getCell(rowIndex, columnIndex);
			}
		}

		return nextState;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("Nb turns: ").append(this.states.size()).append("\n");
		sb.append("Surviving conditions: ").append(this.survivingConditions).append("\n");
		sb.append("Birth conditions: ").append(this.birthConditions).append("\n\n");
		for (int turn = 0; turn < this.states.size(); turn++) {
			sb.append("Turn ").append(turn + 1).append(":\n");
			sb.append(this.states.get(turn));
			sb.append("\n\n");
		}
		return sb.toString();
	}
}