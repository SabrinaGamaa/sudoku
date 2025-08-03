import model.SudokuBoard;
import model.SudokuGame;

public class Main {
    public static void main(String[] args) {
        SudokuBoard board = new SudokuBoard(args);
        SudokuGame game = new SudokuGame(board);
        game.menu();
    }
}
