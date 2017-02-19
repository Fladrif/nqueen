import java.util.*;

public class Genetic {
	Heuristic heur = new Heuristic();

	public int[][][] evolve(int[][][] population) {
		int[][][] newParents = getNewGeneration(population);
		int[][][] newBreed = breedNewGeneration(newParents);
		int[][][] mutGen = mutate(newBreed);

		return mutGen;
	}

	public int[][][] mutate(int[][][] generation) {
		for (int i = 0; i < generation.length; i++) {
			Random rand = new Random();
			int random = rand.nextInt(9);
			if (random <= 2) {
				int col = rand.nextInt(generation[i].length);
				int pos = rand.nextInt(generation[i].length);
				for (int j = 0; j < generation[i][col].length; j++) {
					generation[i][col][j] = 0;
				}
				generation[i][col][pos] = 1;
			}
		}
		return generation;
	}

	private int[][][] breedNewGeneration(int[][][] newParents) {
		Random rand = new Random();
		int popSize = newParents.length;
		int boardSize = newParents[0].length;
		int[][][] newBreed = new int[popSize * 2][boardSize][boardSize];
		int breedNum = 0;
		for (int i = 0; i < newParents.length; i++) {
			if (i + 1 == newParents.length) {
				int cross = rand.nextInt(newParents[i].length - 2) + 1;
				newBreed[breedNum++] = crossover(newParents[i], newParents[0], cross);
				newBreed[breedNum++] = crossover(newParents[0], newParents[i], cross);
			} else {
				int cross = rand.nextInt(newParents[i].length - 2) + 1;
				newBreed[breedNum++] = crossover(newParents[i], newParents[i + 1], cross);
				newBreed[breedNum++] = crossover(newParents[i + 1], newParents[i], cross);
			}
		}
		return newBreed;
	}

	private int[][][] getNewGeneration(int[][][] population) {
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
		int realizedIndividual = -1;
		while (realizedIndividual == -1) {
			population = evolve(population);
			for (int i = 0; i < population.length; i++) {
				if (heur.getHeuristic(population[i]) == 0) {
					realizedIndividual = i;
					break;
				}
			}
		}
		return population[realizedIndividual];
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

	private int[][] crossover(int[][] firstParent, int[][] secondParent, int crossPoint) {
		int[][] child = new int[firstParent.length][firstParent.length];
		for (int i = 0; i < crossPoint; i++) {
			for (int j = 0; j < child.length; j++) {
				child[i][j] = firstParent[i][j];
			}
		}
		for (int i = crossPoint; i < child.length; i++) {
			for (int j = 0; j < child.length; j++) {
				child[i][j] = secondParent[i][j];
			}
		}

		return child;
	}
}
