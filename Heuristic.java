public class Heuristic {
  public int getHeuristic(int[][] board) {
    int count = 0;
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        if (board[i][j] == 1) {
          for (int k = i + 1; k < board.length; k++) {
            if (board[k][j] == 1) {
              count++;
            }
          }
          if (i > j) {
            for (int k = 1; k < board.length - i; k++) {
              if (board[i + k][j + k] == 1) {
                count++;
              }
            }
          } else {
            for (int k = 1; k < board.length - j; k++) {
              if (board[i + k][j + k] == 1) {
                count++;
              }
            }
          }
          if (j < ( board.length - 1 - i)) {
            for (int k = 1; k <= j; k++) {
              if (board[i + k][j - k] == 1) {
                count++;
              }
            }
          } else {
            for (int k = 1; k < board.length - i; k++) {
              if (board[i + k][j - k] == 1) {
                count++;
              }
            }
          }
        }
      }
    }
    return count;
  }
}
