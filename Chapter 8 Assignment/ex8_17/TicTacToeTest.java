public class TicTacToeTest {
    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        game.makeMove(0, 0, TicTacToe.Cell.X);
        game.makeMove(1, 0, TicTacToe.Cell.O);
        game.makeMove(0, 1, TicTacToe.Cell.X);
        game.makeMove(1, 1, TicTacToe.Cell.O);
        game.makeMove(0, 2, TicTacToe.Cell.X);

        System.out.println("Has X won? " + game.isWon(TicTacToe.Cell.X));
        System.out.println("Is draw? " + game.isDraw());
    }
}
