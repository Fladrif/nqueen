public class Main {
  public static void main(String[] args) {
    Board mainBoard = new Board(8);
    Heuristic heur = new Heuristic();
    int[][] board = mainBoard.getBoard();
    for (int i = board.length - 1; i >= 0; i--) {
      for (int j = 0; j < board[i].length; j++) {
        System.out.print(board[j][i] + " ");
      }
      System.out.println();
    }
    System.out.println("Heuristic: " + heur.getHeuristic(board));
  }
}
