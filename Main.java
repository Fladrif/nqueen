public class Main {
  public static void main(String[] args) {
    Board mainBoard = new Board(5);
    Climb climber = new Climb();
    int[][] board = mainBoard.getBoard();
    printBoard(board);
    int[][] solBoard = climber.getBestSol(board);
    printBoard(solBoard);
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
}
