import java.io.*;
import java.util.*;

public class 차윤이의RC카 {
    static int N, Q;
    static final char START = 'X', END = 'Y';
    static Point start;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static char[][] map;
    static ArrayDeque<Point> queue;
    static StringBuilder sb = new StringBuilder();

    static class Point {
        int x, y, direction;

        Point(int x, int y, int direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            sb.append("#").append(tc).append(" ");
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());

            map = new char[N][N];
            for (int i = 0; i < N; i++) {
                String line = br.readLine();
                for (int j = 0; j < N; j++) {
                    map[i][j] = line.charAt(j);
                    if (map[i][j] == START) {
                        start = new Point(i, j, 0);
                    }
                }
            }

            Q = Integer.parseInt(br.readLine());
            for (int i = 0; i < Q; i++) {
                st = new StringTokenizer(br.readLine());
                int size = Integer.parseInt(st.nextToken());
                solve(size, st.nextToken());
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    static void solve(int size, String commands) {
        queue = new ArrayDeque<>();
        queue.add(start);

        for (int i = 0; i < size; i++) {
            int cmd = commands.charAt(i);

            int qsize = queue.size();
            while (qsize-- > 0) {
                Point current = queue.poll();

                int x = current.x, y = current.y, dir = current.direction;
                switch (cmd) {
                    case 'R':
                        int right = turnRight(dir);
                        queue.add(new Point(x, y, right));
                        break;
                    case 'L':
                        int left = turnLeft(dir);
                        queue.add(new Point(x, y, left));
                        break;
                    case 'A':
                        go(x, y, dir);
                        break;
                }
            }
        }

        for (Point p : queue) {
            if (map[p.x][p.y] == END) {
                sb.append(1).append(" ");
                return;
            }
        }
        sb.append(0).append(" ");
    }

    static int turnRight(int dir) {
        return (dir + 1) % 4;
    }

    static int turnLeft(int dir) {
        return (dir + 3) % 4;
    }

    private static void go(int x, int y, int dir) {
        int nx = x + dx[dir];
        int ny = y + dy[dir];

        if (!isValid(nx, ny) || map[nx][ny] == 'T') {
            queue.add(new Point(x, y, dir));
        } else {
            queue.add(new Point(nx, ny, dir));
        }
    }

    static boolean isValid(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}
\