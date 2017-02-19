public class Climb {

	// run one instance of steepest-ascent hill climb
	public int[][] trueClimb(int[][] board) {
		Heuristic heur = new Heuristic();
		int[][] holdBoard = null;
		int heuristic = heur.getHeuristic(board);

		// iterate over array to find successor with lower heuristic
		// failing that to return null
		for (int i = 0; i < board.length; i++) {
			int initialPos = 0; 

			// removes queen from row saving initial position
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] == 1) initialPos = j;
				board[i][j] = 0;
			}

			// iterates over all positions to find queen position with
			// lower heuristic
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = 1;
				if (heuristic > heur.getHeuristic(board)) {
					heuristic = heur.getHeuristic(board);
					holdBoard = copyBoard(board);
				}
				board[i][j] = 0;
			}
			board[i][initialPos] = 1;
		}

		return holdBoard;
	}

	// run hill climb until no successor has a lower heuristic
  public int[][] getBestSol(int[][] board) {
    boolean finished = false;
    int[][] inBoard = board;

		// runs trueClimb() until it returns null signifying lowest
		// heuristic found
    while (!finished) {
      int[][] outBoard = trueClimb(inBoard);
      if (outBoard == null) {
        finished = true;
      } else {
        inBoard = outBoard;
      }
    }
    return inBoard;
  }

	// copies 2-d array
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
