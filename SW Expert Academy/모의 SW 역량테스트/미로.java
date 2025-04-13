import java.io.*;
import java.util.ArrayDeque;

public class 미로 {
    static final int START = 2, END = 3;
    static Point start;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[][] map;
    static boolean[][] visited;
    static StringBuilder sb = new StringBuilder();

    static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int tc = 1; tc <= 10; tc++) {
            String test_case = br.readLine();
            map = new int[16][16];

            for (int i = 0; i < 16; i++) {
                String line = br.readLine();
                for (int j = 0; j < 16; j++) {
                    map[i][j] = line.charAt(j) - '0';
                    if (map[i][j] == START) {
                        start = new Point(i, j);
                    }
                }
            }

            sb.append("#").append(tc).append(" ").append(solve()).append("\n");
        }
        System.out.println(sb.toString());
    }

    static int solve() {
        visited = new boolean[16][16];
        ArrayDeque<Point> queue = new ArrayDeque<>();

        visited[start.x][start.y] = true;
        queue.add(start);

        while (!queue.isEmpty()) {
            Point current = queue.poll();

            if (map[current.x][current.y] == END) {
                return 1;
            }

            for (int d = 0; d < 4; d++) {
                int nx = current.x + dx[d];
                int ny = current.y + dy[d];

                if (!isValid(nx, ny) || visited[nx][ny] || map[nx][ny] == 1) continue;
                visited[nx][ny] = true;
                queue.add(new Point(nx, ny));
            }
        }
        return -1;
    }

    static boolean isValid(int x, int y) {
        return x >= 0 && x < 16 && y >= 0 && y < 16;
    }
}
