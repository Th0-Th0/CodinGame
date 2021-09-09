
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.tho.codingame.customgameoflife.CellularAutomaton;
import com.tho.codingame.customgameoflife.GameOfLife;

public class Solution {

	public static void main(final String[] args) {
		final Scanner in = new Scanner(System.in);
		final int height = in.nextInt();
		final int width = in.nextInt();
		final int nbTurns = in.nextInt();

		final CellularAutomaton initState = new CellularAutomaton(height, width);

		if (in.hasNextLine()) {
			in.nextLine();
		}

		final List<Integer> survivingConditions = new ArrayList<Integer>();
		final String alive = in.nextLine();
		for (int charIndex = 0; charIndex < alive.length(); charIndex++) {
			final Character c = alive.charAt(charIndex);
			if (c.equals('1')) {
				survivingConditions.add(charIndex);
			}
		}

		final List<Integer> birthConditions = new ArrayList<Integer>();
		final String dead = in.nextLine();
		for (int charIndex = 0; charIndex < dead.length(); charIndex++) {
			final Character c = dead.charAt(charIndex);
			if (c.equals('1')) {
				birthConditions.add(charIndex);
			}
		}

		for (int rowIndex = 0; rowIndex < height; rowIndex++) {
			final String line = in.nextLine();
			for (int charIndex = 0; charIndex < line.length(); charIndex++) {
				final Character c = line.charAt(charIndex);
				if (c.equals('O')) {
					initState.getCell(rowIndex, charIndex).setAlive();
				}
			}
		}

		final GameOfLife gol = new GameOfLife(survivingConditions, birthConditions);
		gol.run(initState, nbTurns);

		System.err.println(gol);
		System.out.println(gol.getLastState());
	}
}