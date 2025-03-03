import java.io.*;
import java.util.*;

public class 다리_만들기 {
    private static int N;
    private static int minLength = Integer.MAX_VALUE;
    private static List<int[]> edges = new ArrayList<>();
    private static int[][] map;
    private static int landNum = 1;
    private static int[][] isLandMap;
    private static int[][] directions = {
        {-1, 0}, {1, 0}, {0, -1}, {0, 1}
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        isLandMap = new int[N][N];
        boolean[][] visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 1 && !visited[i][j]) {
                    markIslands(i, j, landNum++, visited);
                }
            }
        }

        for (int[] start : edges) {
            bfs(start);
        }
        System.out.println(minLength);
    }

    private static void markIslands(int startX, int startY, int landNum, boolean[][] visited) {
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        visited[startX][startY] = true;

        queue.add(new int[] {startX, startY});
        isLandMap[startX][startY] = landNum;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + directions[i][0];
                int ny = y + directions[i][1];

                if (!isValid(nx, ny)) continue;
                if (visited[nx][ny]) continue;

                if (map[nx][ny] == 1) {
                    visited[nx][ny] = true;
                    isLandMap[nx][ny] = landNum;
                    queue.add(new int[] {nx, ny});
                } else if (map[nx][ny] == 0) {
                    edges.add(new int[] {x, y, landNum});  // 바다에 인접한 육지
                }
            }
        }
    }

    private static void bfs(int[] start) {
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][N];

        queue.add(new int[] {start[0], start[1], start[2], 0});
        visited[start[0]][start[1]] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];
            int landNum = current[2];
            int length = current[3];

            for (int i = 0; i < 4; i++) {
                int nx = x + directions[i][0];
                int ny = y + directions[i][1];

                if (!isValid(nx, ny) || visited[nx][ny]) continue;

                if (map[nx][ny] == 1 && isLandMap[nx][ny] != landNum) {
                    minLength = Math.min(minLength, length);
                    return;
                } else if (map[nx][ny] == 0) {
                    visited[nx][ny] = true;
                    queue.add(new int[] {nx, ny, landNum, length + 1});
                }
            }
        }
    }

    private static boolean isValid(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}