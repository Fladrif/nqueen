import java.util.*;

public class Board {
  Random rand = new Random();
  int[][] board;

	// Generate new 2-d int array of input size
  public Board(int size) {
    board = new int[size][size];
    for (int i = 0; i < board.length; i++) {
      int placement = rand.nextInt(size);
      board[i][placement] = 1;
    }
  }

	// return 2-d int array
  public int[][] getBoard() {
    return board;
  }
}
