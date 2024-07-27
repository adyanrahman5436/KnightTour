import java.util.Arrays;

public class KnightTour {
    private static final int[] knightMovesX = {2, 1, -1, -2, -2, -1, 1, 2};
    private static final int[] knightMovesY = {1, 2, 2, 1, -1, -2, -2, -1};
    private int boardSize;
    private int[][] board;
    
    public KnightTour(int size) {
        this.boardSize = size;
        this.board = new int[boardSize][boardSize];
        for (int[] row : board) {
            Arrays.fill(row, -1);
        }
    }

    public boolean solve() {
        board[0][0] = 0; // Start from the first cell
        if (solveUtil(0, 0, 1)) {
            printSolution();
            return true;
        } else {
            System.out.println("Solution does not exist");
            return false;
        }
    }

    private boolean solveUtil(int x, int y, int moveCount) {
        if (moveCount == boardSize * boardSize) {
            return true;
        }

        for (int i = 0; i < 8; i++) {
            int nextX = x + knightMovesX[i];
            int nextY = y + knightMovesY[i];

            if (isSafe(nextX, nextY)) {
                board[nextX][nextY] = moveCount;
                if (solveUtil(nextX, nextY, moveCount + 1)) {
                    return true;
                } else {
                    board[nextX][nextY] = -1; // Backtracking
                }
            }
        }
        return false;
    }

    private boolean isSafe(int x, int y) {
        return (x >= 0 && x < boardSize && y >= 0 && y < boardSize && board[x][y] == -1);
    }

    private void printSolution() {
        for (int[] row : board) {
            for (int cell : row) {
                System.out.printf("%2d ", cell);
            }
            System.out.println();
        }
    }
}
