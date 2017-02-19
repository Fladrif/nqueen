public class Genetic {
	public int[][][] evolve(int[][][] population) {
		int[][][] newParents = getNewGeneration(population);
		int[][][] newBreed = breedNewGeneration(newParents);
		int[][][] mutGen = mutate(newBreed);

		return mutGen;
	}

	private int[][][] mutate(int[][][] generation) {
		int[][][] mutGeneration = null;
		return mutGeneration;
	}

	private int[][][] breedNewGeneration(int[][][] newParents) {
		int popSize = newParents.length;
		int boardSize = newParents[0].length;
		int[][][] newBreed = new int[popSize * 2][boardSize][boardSize];
		for (int i = 0; i < newParents.length; i++) {
		}
		return null;
	}

	public int[][][] getNewGeneration(int[][][] population) {
		Heuristic heur = new Heuristic();
		int[] heuristics = new int[population.length];
		int[][][] newGeneration = new int[population.length / 2][population[0].length][population[0].length];

		// test fitness of population
		for (int i = 0; i < population.length; i++) {
			heuristics[i] = heur.getHeuristic(population[i]);
		}

		// create new generation
		int[] pos = new int[newGeneration.length];

		for (int h = 0; h < newGeneration.length; h++) {
			// larger than any possible heuristic
			int lowestHeur = 999999999;

			for (int i = 0; i < heuristics.length; i++) {
				if (heuristics[i] < lowestHeur) {
					boolean exists = false;

					// check if current individual is included already
					for (int j = 0; j < h; j++) {
						if (pos[j] == i) exists = true;
						break;
					}
					if (!exists) {
						lowestHeur = heuristics[i];
						pos[h] = i;
					}
				}
			}
			newGeneration[h] = copyBoard(population[pos[h]]);
		}

		return newGeneration;
	}

	public int[][] evolution(int[][][] population) {
		return null;
	}

	private int[][] copyBoard(int[][] board) {
		int[][] newBoard = new int[board.length][board.length];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				newBoard[i][j] = board[i][j];
			}
		}

		return newBoard;
	}
}
