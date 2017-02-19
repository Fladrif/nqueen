public class Climb {
	public int[][] trueClimb(int[][] board) {
		Heuristic heur = new Heuristic();
		int[][] holdBoard = null;
		int heuristic = heur.getHeuristic(board);

		for (int i = 0; i < board.length; i++) {
			int initialPos = 0; 
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] == 1) initialPos = j;
				board[i][j] = 0;
			}
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

  public int[][] getBestSol(int[][] board) {
    boolean finished = false;
    int[][] inBoard = board;
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
