public class Main {
  public static void main(String[] args) {
		testGenes();
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

	private static void testClimb() {
		int INIT_COUNT = 10000;
		int count = INIT_COUNT;
		int complete = 0;
    Heuristic heur = new Heuristic();
		while (count > 0) {
    	Board mainBoard = new Board(8);
    	Climb climber = new Climb();
    	int[][] board = mainBoard.getBoard();
    	int[][] solBoard = climber.getBestSol(board);
			if (heur.getHeuristic(solBoard) == 0) complete++;
			count--;
		}
		System.out.println(complete + "/" + INIT_COUNT + " = " + ((float) complete / (float) INIT_COUNT));
	}

	private static void testGenes() {
		int POP_SIZE = 12;
		int BOARD_SIZE = 50;
		int[][][] population = new int[POP_SIZE][BOARD_SIZE][BOARD_SIZE];
		for (int i = 0; i < POP_SIZE; i++) {
			Board tempBoard	= new Board(BOARD_SIZE);
			printBoard(tempBoard.getBoard());
			population[i] = tempBoard.getBoard();
		}
		Genetic gene = new Genetic();
		int[][] theOne = gene.evolution(population);
		printBoard(theOne);
	}
}
