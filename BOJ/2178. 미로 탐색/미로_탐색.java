import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 미로_탐색 {
    private static int N;
    private static int M;
    private static int[][] map;
    private static boolean[][] visited;
    private static int[][] directions = {
        {-1, 0}, {1, 0},
        {0, -1}, {0, 1}
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }
        System.out.println(findMinimumMoves());
    }

    private static int findMinimumMoves() {
        Queue<int[]> queue = new LinkedList<>();
        visited[0][0] = true;
        queue.add(new int[] {0, 0, 1});

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int currentX = current[0];
            int currentY = current[1];
            int move = current[2];

            if (currentX == N - 1 && currentY == M - 1) {
                return move;
            }
            for (int i = 0; i < 4; i++) {
                int x = currentX + directions[i][0];
                int y = currentY + directions[i][1];

                if (isValid(x, y) && !visited[x][y] && map[x][y] == 1) {
                    visited[x][y] = true;
                    queue.add(new int[] {x, y, move + 1});
                }
            }
        }
        return 0;
    }

    private static boolean isValid(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }
}
