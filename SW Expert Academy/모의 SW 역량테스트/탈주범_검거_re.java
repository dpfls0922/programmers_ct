import java.io.*;
import java.util.*;

public class 탈주범_검거_re {
    static int N, M, R, C, L;
    static int[] dx = {-1, 1, 0, 0}; // 상 하 좌 우
    static int[] dy = {0, 0, -1, 1};
    static int[][] structures = {{}, {0, 1, 2, 3}, {0, 1}, {2, 3}, {0, 3}, {1, 3}, {1, 2}, {0, 2}};
    static int[][] map;
    static StringBuilder sb = new StringBuilder();

    static class Point {
        int x, y, time;

        Point(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());

            map = new int[N][M];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            sb.append("#").append(tc).append(" ").append(solve()).append("\n");
        }
        System.out.println(sb.toString());
    }

    static int solve() {
        boolean[][] visited = new boolean[N][M];
        ArrayDeque<Point> queue = new ArrayDeque<>();

        visited[R][C] = true;
        queue.add(new Point(R, C, 1));

        int count = 1;
        while (!queue.isEmpty()) {
            Point current = queue.poll();

            int x = current.x, y = current.y, time = current.time;
            if (time >= L) continue;

            int[] directions = structures[map[x][y]];
            for (int dir : directions) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];

                if (!isValid(nx, ny) || visited[nx][ny]) continue;
                if (canConnect(dir, map[nx][ny])) {
                    visited[nx][ny] = true;
                    queue.add(new Point(nx, ny, time + 1));
                    count++;
                }
            }
        }
        return count;
    }

    static boolean canConnect(int prevDir, int nextType) {
        int opposite = prevDir ^= 1; // [0->1, 1->0, 2->3, 3->2]

        for (int dir : structures[nextType]) {
            if (dir == opposite) return true;
        }
        return false;
    }

    static boolean isValid(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }
}
