import java.util.Scanner;

public class ConnectFour {
    private static final int ROWS = 6;
    private static final int COLUMNS = 7;
    private static final char EMPTY = '.';
    private static final char PLAYER_ONE = 'X';
    private static final char PLAYER_TWO = 'O';
    
    private char[][] board = new char[ROWS][COLUMNS];
    private char currentPlayer = PLAYER_ONE;

    public ConnectFour() {
        initializeBoard();
    }

    private void initializeBoard() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                board[row][col] = EMPTY;
            }
        }
    }

    private void printBoard() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                System.out.print(board[row][col] + " ");
            }
            System.out.println();
        }
        System.out.println("1 2 3 4 5 6 7");
    }

    private boolean dropDisc(int column) {
        if (column < 0 || column >= COLUMNS || board[0][column] != EMPTY) {
            return false;
        }

        for (int row = ROWS - 1; row >= 0; row--) {
            if (board[row][column] == EMPTY) {
                board[row][column] = currentPlayer;
                return true;
            }
        }

        return false;
    }

    private boolean checkForWin() {
        
        return checkRows() || checkColumns() || checkDiagonals();
    }

    private boolean checkRows() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS - 3; col++) {
                if (board[row][col] == currentPlayer &&
                    board[row][col] == board[row][col + 1] &&
                    board[row][col] == board[row][col + 2] &&
                    board[row][col] == board[row][col + 3]) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkColumns() {
        for (int col = 0; col < COLUMNS; col++) {
            for (int row = 0; row < ROWS - 3; row++) {
                if (board[row][col] == currentPlayer &&
                    board[row][col] == board[row + 1][col] &&
                    board[row][col] == board[row + 2][col] &&
                    board[row][col] == board[row + 3][col]) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkDiagonals() {
       
        for (int row = 0; row < ROWS - 3; row++) {
            for (int col = 0; col < COLUMNS - 3; col++) {
                if (board[row][col] == currentPlayer &&
                    board[row][col] == board[row + 1][col + 1] &&
                    board[row][col] == board[row + 2][col + 2] &&
                    board[row][col] == board[row + 3][col + 3]) {
                    return true;
                }
            }
        }

        
        for (int row = 3; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS - 3; col++) {
                if (board[row][col] == currentPlayer &&
                    board[row][col] == board[row - 1][col + 1] &&
                    board[row][col] == board[row - 2][col + 2] &&
                    board[row][col] == board[row - 3][col + 3]) {
                    return true;
                }
            }
        }

        return false;
    }

    public void playGame() {
        Scanner scanner = new Scanner(System.in);
        boolean gameWon = false;

        while (!gameWon) {
            printBoard();
            System.out.println("Player " + (currentPlayer == PLAYER_ONE ? "One" : "Two") + ", choose a column (1-7): ");
            int column = scanner.nextInt() - 1;

            if (dropDisc(column)) {
                if (checkForWin()) {
                    printBoard();
                    System.out.println("Player " + (currentPlayer == PLAYER_ONE ? "One" : "Two") + " wins!");
                    gameWon = true;
                } else {
                    currentPlayer = (currentPlayer == PLAYER_ONE) ? PLAYER_TWO : PLAYER_ONE;
                }
            } else {
                System.out.println("Column full or invalid! Try again.");
            }
        }

        scanner.close();
    }

    public static void main(String[] args) {
        ConnectFour game = new ConnectFour();
        game.playGame();
    }
}
