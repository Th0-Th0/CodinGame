package com.tho.codingame.customgameoflife;

public class CellularAutomaton {
	private final Cell[][] cells;

	public CellularAutomaton(final int height, final int width) {
		this.cells = new Cell[height][width];
		for (int rowIndex = 0; rowIndex < height; rowIndex++) {
			for (int columnIndex = 0; columnIndex < width; columnIndex++) {
				this.cells[rowIndex][columnIndex] = new Cell();
			}
		}

		for (int rowIndex = 0; rowIndex < height; rowIndex++) {
			for (int columnIndex = 0; columnIndex < width; columnIndex++) {
				addNeighbours(rowIndex, columnIndex);
			}
		}
	}

	private void addNeighbours(final int rowIndex, final int columnIndex) {
		for (int deltaRow = -1; deltaRow <= 1; deltaRow++) {
			for (int deltaColumn = -1; deltaColumn <= 1; deltaColumn++) {
				if (deltaRow != 0 || deltaColumn != 0) {
					final Cell cell = getCell(rowIndex + deltaRow, columnIndex + deltaColumn);
					if (cell != null) {
						this.cells[rowIndex][columnIndex].addNeighbour(cell);
					}
				}
			}
		}
	}

	public int getHeight() {
		return this.cells.length;
	}

	public int getWidth() {
		return this.cells[0].length;
	}

	public Cell getCell(final int rowIndex, final int columnIndex) {
		return rowIndex >= 0 && rowIndex < this.cells.length && columnIndex >= 0
				&& columnIndex < this.cells[rowIndex].length ? this.cells[rowIndex][columnIndex] : null;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		for (int rowIndex = 0; rowIndex < this.cells.length; rowIndex++) {
			for (int columnIndex = 0; columnIndex < this.cells[rowIndex].length; columnIndex++) {
				sb.append(this.cells[rowIndex][columnIndex]);
			}
			if (rowIndex < this.cells.length - 1) {
				sb.append("\n");
			}
		}
		return sb.toString();
	}
}