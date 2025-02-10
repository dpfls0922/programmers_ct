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
        int[][] directions = {
            {1, 0}, {0, 1}, {-1, 0}, {0, -1} // 아래, 오른쪽, 위, 왼쪽
        };
        int direction = 0;

        board = new int[N][N];
        while (num >= 1) {
            if (num == M) {
                mRow = row + 1;
                mCol = col + 1;
            }
            board[row][col] = num--;

            int newRow = row + directions[direction][0];
            int newCol = col + directions[direction][1];

            if (newRow < 0 || newRow > N - 1 || newCol < 0 || newCol > N - 1 || board[newRow][newCol] != 0) {
                direction = (direction + 1) % 4; // 방향 전환
                newRow = row + directions[direction][0];
                newCol = col + directions[direction][1];
            }
            row = newRow;
            col = newCol;
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
