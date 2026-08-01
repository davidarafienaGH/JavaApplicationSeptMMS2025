public class TicTacToe {
    public enum Cell { X, O, EMPTY }
    private final Cell[][] board = new Cell[3][3];

    public TicTacToe() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = Cell.EMPTY;
            }
        }
    }

    public boolean makeMove(int row, int col, Cell mark) {
        if (row < 0 || row >= 3 || col < 0 || col >= 3 || board[row][col] != Cell.EMPTY) {
            return false;
        }
        board[row][col] = mark;
        return true;
    }

    public boolean isWon(Cell mark) {
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == mark && board[i][1] == mark && board[i][2] == mark) ||
                (board[0][i] == mark && board[1][i] == mark && board[2][i] == mark)) {
                return true;
            }
        }
        return (board[0][0] == mark && board[1][1] == mark && board[2][2] == mark) ||
               (board[0][2] == mark && board[1][1] == mark && board[2][0] == mark);
    }

    public boolean isDraw() {
        if (isWon(Cell.X) || isWon(Cell.O)) return false;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == Cell.EMPTY) return false;
            }
        }
        return true;
    }
}
