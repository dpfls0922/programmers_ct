import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static int N, M, mRow, mCol;
    private static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        makeBoard();
        printBoard();
        System.out.print(mRow + " " + mCol);
    }

    private static void makeBoard() {
        int num = N * N;
        int row = 0;
        int col = 0;
        int direction = 0; // 이동 방향 (0: 아래, 1: 오른쪽, 2: 위, 3: 왼쪽)

        board = new int[N][N];
        while (num >= 1) {
            if (num == M) {
                mRow = row + 1;
                mCol = col + 1;
            }
            board[row][col] = num--;

            if (direction == 0) { // 아래
                if (row == N - 1 || board[row + 1][col] != 0) {
                    direction = 1;
                    col++;
                } else {
                    row++;
                }
            } else if (direction == 1) { // 오른쪽
                if (col == N - 1 || board[row][col + 1] != 0) {
                    direction = 2;
                    row--;
                } else {
                    col++;
                }
            } else if (direction == 2) { // 위
                if (row == 0 || board[row - 1][col] != 0) {
                    direction = 3;
                    col--;
                } else {
                    row--;
                }
            } else if (direction == 3) { // 왼쪽
                if (col == 0 || board[row][col - 1] != 0) {
                    direction = 0;
                    row++;
                } else {
                    col--;
                }
            }
        }
    }

    private static void printBoard() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
