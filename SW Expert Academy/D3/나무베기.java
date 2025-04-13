import java.io.*;
import java.util.*;

public class 나무베기 {
    static int N, K;
    static final char START = 'X', END = 'Y';
    static Point start;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static char[][] map;
    static boolean[][][][] visited;
    static StringBuilder sb = new StringBuilder();

    static class Point {
        int x, y, direction, count, k;

        Point(int x, int y, int direction, int count, int k) {
            this.x = x;
            this.y = y;
            this.direction = direction;
            this.count = count;
            this.k = k;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken()); // 아빠가 벨 수 있는 최대 나무의 수

            map = new char[N][N];
            for (int i = 0; i < N; i++) {
                String line = br.readLine();
                for (int j = 0; j < N; j++) {
                    map[i][j] = line.charAt(j);
                    if (map[i][j] == START) {
                        start = new Point(i, j, 0, 0, K);
                    }
                }
            }
            sb.append("#").append(tc).append(" ").append(solve()).append("\n");
        }
        System.out.println(sb.toString());
    }

    // 차윤이가 RC카를 목적지까지 이동시키기 위한 최소 조작 횟수
    static int solve() {
        visited = new boolean[K + 1][N][N][4];
        ArrayDeque<Point> queue = new ArrayDeque<>();

        visited[K][start.x][start.y][start.direction] = true;
        queue.add(start);

        while (!queue.isEmpty()) {
            Point current = queue.poll();

            if (map[current.x][current.y] == END) {
                return current.count;
            }

            int x = current.x, y = current.y, dir = current.direction;
            int count = current.count, k = current.k;

            // 회전 (우)
            int right = turnRight(dir);
            if (!visited[k][x][y][right]) {
                visited[k][x][y][right] = true;
                queue.add(new Point(x, y, right, count + 1, k));
            }

            // 회전 (좌)
            int left = turnLeft(dir);
            if (!visited[k][x][y][left]) {
                visited[k][x][y][left] = true;
                queue.add(new Point(x, y, left, count + 1, k));
            }

            // 전진
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            if (!isValid(nx, ny)) continue;

            if (map[nx][ny] == 'T' && k > 0) {
                if (!visited[k - 1][nx][ny][dir]) {
                    visited[k - 1][nx][ny][dir] = true;
                    queue.add(new Point(nx, ny, dir, count + 1, k - 1));
                }
            } else if (map[nx][ny] != 'T') {
                if (!visited[k][nx][ny][dir]) {
                    visited[k][nx][ny][dir] = true;
                    queue.add(new Point(nx, ny, dir, count + 1, k));
                }
            }
        }
        return -1;
    }

    static int turnRight(int dir) {
        return (dir + 1) % 4;
    }

    static int turnLeft(int dir) {
        return (dir + 3) % 4;
    }

    static boolean isValid(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}