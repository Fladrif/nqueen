public class Main {
  public static void main(String[] args) {
		testClimb(1000, 8);
		testGenes(10, 50);
  }

  private static void printBoard(int[][] board) {
    Heuristic heur = new Heuristic();
    for (int i = board.length - 1; i >= 0; i--) {
      for (int j = 0; j < board[i].length; j++) {
        System.out.print(board[j][i] + " ");
      }
      System.out.println();
    }
    System.out.println("Heuristic: " + heur.getHeuristic(board));
  }

	private static void testClimb(int INIT_COUNT, int BOARD_SIZE) {
		// int INIT_COUNT = 10000;
		// int BOARD_SIZE = 8;
		int count = INIT_COUNT;
		int complete = 0;
    Heuristic heur = new Heuristic();

		// declare time counts
		long totalTime = 0;
		long totalCompletedTime = 0;

		// Iterates 'count' number of times and outputs average
		while (count > 0) {
    	Board mainBoard = new Board(BOARD_SIZE);
    	Climb climber = new Climb();
    	int[][] board = mainBoard.getBoard();

			// start timer, run algorithm, then stop time
			long startTime = System.nanoTime();
    	int[][] solBoard = climber.getBestSol(board);
			long endTime = System.nanoTime();

			// count number of completed runs
			if (heur.getHeuristic(solBoard) == 0) {
				complete++;
				totalCompletedTime += (endTime - startTime) / 1000000;
			}
			totalTime += (endTime - startTime) / 1000000;
			count--;
		}

		System.out.println(complete + "/" + INIT_COUNT + " = " + ((float) complete / (float) INIT_COUNT));
		System.out.printf("Avg total time: %f in ms\n", (float) totalTime / (float) INIT_COUNT);
		System.out.printf("Avg total complete time: %f in ms\n", (float) totalCompletedTime / (float) complete);
	}

	private static void testGenes(int POP_SIZE, int BOARD_SIZE) {
		// int POP_SIZE = 12;
		// int BOARD_SIZE = 50;
		int[][][] population = new int[POP_SIZE][BOARD_SIZE][BOARD_SIZE];

		// Generate new population
		for (int i = 0; i < POP_SIZE; i++) {
			Board tempBoard	= new Board(BOARD_SIZE);
			printBoard(tempBoard.getBoard());
			population[i] = tempBoard.getBoard();
		}
		Genetic gene = new Genetic();
		
		// run genetic algorithm outputting successful individual
		int[][] theOne = gene.evolution(population);
		printBoard(theOne);
	}
}
