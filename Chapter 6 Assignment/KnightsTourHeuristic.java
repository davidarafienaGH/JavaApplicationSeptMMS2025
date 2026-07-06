public class KnightsTourHeuristic {
    private static final int[][] accessibility = {
        {2, 3, 4, 4, 4, 4, 3, 2},
        {3, 4, 6, 6, 6, 6, 4, 3},
        {4, 6, 8, 8, 8, 8, 6, 4},
        {4, 6, 8, 8, 8, 8, 6, 4},
        {4, 6, 8, 8, 8, 8, 6, 4},
        {4, 6, 8, 8, 8, 8, 6, 4},
        {3, 4, 6, 6, 6, 6, 4, 3},
        {2, 3, 4, 4, 4, 4, 3, 2}
    };

    private static final int[] horizontal = {2, 1, -1, -2, -2, -1, 1, 2};
    private static final int[] vertical = {-1, -2, -2, -1, 1, 2, 2, 1};

    public static void main(String[] args) {
        int[][] board = new int[8][8];
        int currentRow = 0; // Starting Position
        int currentColumn = 0;
        board[currentRow][currentColumn] = 1;

        for (int moveCount = 2; moveCount <= 64; moveCount++) {
            int minAccess = 9;
            int bestMove = -1;

            for (int move = 0; move < 8; move++) {
                int nextRow = currentRow + vertical[move];
                int nextCol = currentColumn + horizontal[move];

                if (isValidMove(nextRow, nextCol, board)) {
                    if (accessibility[nextRow][nextCol] < minAccess) {
                        minAccess = accessibility[nextRow][nextCol];
                        bestMove = move;
                    }
                }
            }

            if (bestMove == -1) {
                System.out.printf("Tour ended early at %d moves.%n", moveCount - 1);
                return;
            }

            // Decrement access of all spots reachable from the historical current position
            reduceAccessibility(currentRow, currentColumn);

            currentRow += vertical[bestMove];
            currentColumn += horizontal[bestMove];
            board[currentRow][currentColumn] = moveCount;
        }

        printBoard(board);
    }

    private static boolean isValidMove(int row, int col, int[][] board) {
        return (row >= 0 && row < 8 && col >= 0 && col < 8 && board[row][col] == 0);
    }

    private static void reduceAccessibility(int row, int col) {
        for (int m = 0; m < 8; m++) {
            int nextRow = row + vertical[m];
            int nextCol = col + horizontal[m];
            if (nextRow >= 0 && nextRow < 8 && nextCol >= 0 && nextCol < 8) {
                accessibility[nextRow][nextCol]--;
            }
        }
    }

    private static void printBoard(int[][] board) {
        for (int[] row : board) {
            for (int cell : row) {
                System.out.printf("%3d", cell);
            }
            System.out.println();
        }
    }
}