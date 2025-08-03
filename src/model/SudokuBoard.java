package model;

public class SudokuBoard {

    private SudokuCell[][] board = new SudokuCell[9][9];

    public SudokuBoard(String[] args) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board[i][j] = new SudokuCell(false, 0);
            }
        }

        for (String arg : args) {
            String[] partes = arg.split(",");
            int row = Integer.parseInt(partes[0]);
            int col = Integer.parseInt(partes[1]);
            int val = Integer.parseInt(partes[2]);

            board[row][col] = new SudokuCell(true, val);
        }
    }

    public boolean setValue(int row, int col, int value) {
        System.out.printf("Tentando colocar %d na posição (%d,%d)%n", value, row, col);

        if (!isValidPosition(row, col)) {
            System.out.println("Posição inválida.");
            return false;
        }
        if (board[row][col].isFixed()) {
            System.out.println("Não pode alterar número fixo.");
            return false;
        }
        if (value < 1 || value > 9) {
            System.out.println("Número deve ser entre 1 e 9.");
            return false;
        }
        if (!isValidMove(row, col, value)) {
            System.out.println("Movimento inválido: conflito no Sudoku.");
            return false;
        }
        board[row][col].setValue(value);
        System.out.printf("Número %d inserido na posição (%d,%d)%n", value, row, col);
        return true;
    }

    public boolean removeValue(int row, int col) {
        if (isValidPosition(row, col) && !board[row][col].isFixed()) {
            board[row][col].limparValor();
            return true;
        }
        return false;
    }

    public void clearUserInputs() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (!board[i][j].isFixed()) {
                    board[i][j].limparValor();
                }
            }
        }
    }

    public void printBoard() {
        for (int i = 0; i < 9; i++) {
            if (i % 3 == 0 && i != 0) System.out.println("------+-------+------");
            for (int j = 0; j < 9; j++) {
                if (j % 3 == 0 && j != 0) System.out.print("| ");
                int val = board[i][j].getValue();
                System.out.print((val == 0 ? "." : val) + " ");
            }
            System.out.println();
        }
    }

    public boolean isComplete() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j].isEmpty()) {
                    return false;
                }
            }
        }
        return !hasErrors();
    }

    public boolean hasErrors() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int value = board[i][j].getValue();
                if (value != 0 && !isValidMove(i, j, value, true)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isValidPosition(int row, int col) {
        return row >= 0 && row < 9 && col >= 0 && col < 9;
    }

    private boolean isValidMove(int row, int col, int value) {
        return isValidMove(row, col, value, false);
    }

    private boolean isValidMove(int row, int col, int value, boolean ignoreCurrent) {
        // Checa linha e coluna
        for (int i = 0; i < 9; i++) {
            if (i != col && board[row][i].getValue() == value) return false;
            if (i != row && board[i][col].getValue() == value) return false;
        }

        // Checa bloco 3x3
        int boxRow = row - row % 3;
        int boxCol = col - col % 3;
        for (int i = boxRow; i < boxRow + 3; i++) {
            for (int j = boxCol; j < boxCol + 3; j++) {
                if ((i != row || j != col || ignoreCurrent) && board[i][j].getValue() == value) {
                    return false;
                }
            }
        }

        return true;
    }

}
