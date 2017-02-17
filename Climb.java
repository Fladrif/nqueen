public class Climb {
  public int[][] climbBabyClimb(int[][] board) {
    Heuristic heur = new Heuristic();
    int[][] holdBoard = null;
    int heuristic = heur.getHeuristic(board);

    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board.length; j++) {
        if (board[i][j] == 1) {
          if ((j + 1) < (board.length -1)) {
            board[i][j] = 0;
            board[i][j + 1] = 1;
            if (heur.getHeuristic(board) < heuristic) {
              heuristic = heur.getHeuristic(board);
              holdBoard = new int[board.length][board.length];
              for (int first = 0; first < board.length; first++) {
                for (int second = 0; second < board[first].length; second++) {
                  holdBoard[first][second] = board[first][second];
                }
              }
              System.out.println(heuristic);
            }
            board[i][j] = 1;
            board[i][j + 1] = 0;
          }
          if ((j - 1) >= 0) {
            board[i][j] = 0;
            board[i][j - 1] = 1;
            if (heur.getHeuristic(board) < heuristic) {
              heuristic = heur.getHeuristic(board);
              holdBoard = new int[board.length][board.length];
              for (int first = 0; first < board.length; first++) {
                for (int second = 0; second < board[first].length; second++) {
                  holdBoard[first][second] = board[first][second];
                }
              }
              System.out.println(heuristic);
            }
            board[i][j] = 1;
            board[i][j - 1] = 0;
          }
        }
      }
    }

    return holdBoard;
  }

  public int[][] getBestSol(int[][] board) {
    boolean finished = false;
    int[][] inBoard = board;
    while (!finished) {
      int[][] outBoard = climbBabyClimb(inBoard);
      if (outBoard == null) {
        finished = true;
      } else {
        inBoard = outBoard;
      }
    }
    return inBoard;
  }
}
