public class Main {
  public static void main(String[] args) {
		test();
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

	private static void test() {
		int INIT_COUNT = 100;
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
		System.out.println(complete + "/" + INIT_COUNT + " = " + complete / INIT_COUNT);
	}
}
