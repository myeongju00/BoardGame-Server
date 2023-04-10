package Model;

import java.util.Arrays;

public class Board {
    private final String[][] board;
    private final int boardSize;
    private static final int[] dx = {0, 1, 0, -1};
    private static final int[] dy = {1, 0, -1, 0};

    public Board(int boardSize) {
        this.boardSize = boardSize;
        board = new String[boardSize][boardSize];
        makeBoard();
    }

    private void makeBoard() {
        int num = 1;
        for (int i = 0; i < (boardSize + 1) / 2; i++) {
            int x = i, y = i;
            int idx = 0;
            board[i][i] = String.valueOf(num++);
            while (idx < 4) {
                int nx = x + dx[idx];
                int ny = y + dy[idx];
                if (nx > boardSize - 1 || ny > boardSize - 1 || nx < 0 || ny < 0 || board[nx][ny] != null) {
                    idx++;
                } else {
                    board[nx][ny] = String.valueOf(num++);
                    x = nx;
                    y = ny;
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < boardSize; i++) {
            sb.append("|");
            for (int j = 0; j < boardSize; j++) {
                sb.append(" ").append(board[i][j]).append(" |");
            }
            if (i == boardSize - 1) break;
            sb.append("\n");
        }
        return sb.toString();
    }
}
