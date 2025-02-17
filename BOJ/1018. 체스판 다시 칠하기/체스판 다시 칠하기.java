import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N, M;
    private static int minCount = Integer.MAX_VALUE;
    private static char[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new char[N][M];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = line.charAt(j);
            }
        }

        for (int i = 0; i <= N - 8; i++) {
            for (int j = 0; j <= M - 8; j++) {
                makeChessBoard(i, j);
            }
        }
        System.out.println(minCount);
    }

    private static void makeChessBoard(int startX, int startY) {
        char[] case1 = new char[] {'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W'};
        char[] case2 = new char[] {'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B'};

        countChessRepaints(startX, startY, case1, case2);
        countChessRepaints(startX, startY, case2, case1);
    }

    private static void countChessRepaints(int startX, int startY, char[] case1, char[] case2) {
        int count = 0;

        for (int i = startX; i < startX + 8; i++) {
            for (int j = startY; j < startY + 8; j++) {
                if (i % 2 == 0) {
                    if (board[i][j] != case1[j - startY]) count += 1;
                } else {
                    if (board[i][j] != case2[j - startY]) count+= 1;
                }
            }
        }
        minCount = Math.min(count, minCount);
    }
}