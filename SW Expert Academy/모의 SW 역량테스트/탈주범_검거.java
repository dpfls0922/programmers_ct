import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.LinkedList;
import java.util.Queue;

public class 탈주범_검거 {
    static int[][] map;
    static boolean[][] visited;
    static int N, M, R, C, L;

    // 상, 하, 좌, 우
    static int[][] type = {
            { 0, 0, 0, 0 }, // 0
            { 1, 1, 1, 1 }, // 1
            { 1, 1, 0, 0 }, // 2
            { 0, 0, 1, 1 }, // 3
            { 1, 0, 0, 1 }, // 4
            { 0, 1, 0, 1 }, // 5
            { 0, 1, 1, 0 }, // 6
            { 1, 0, 1, 0 } // 7
    };

    // 상, 하, 좌, 우
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {

            // 입력 받기
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); // 세로 크기
            M = Integer.parseInt(st.nextToken()); // 가로 크기
            R = Integer.parseInt(st.nextToken()); // 맨홀 세로 위치
            C = Integer.parseInt(st.nextToken()); // 맨홀 가로 위치
            L = Integer.parseInt(st.nextToken()); // 탈출 소요 시간

            map = new int[N][M];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // 장소의 개수 출력하기
            visited = new boolean[N][M];
            System.out.println("#" + test_case + " " + bfs(R, C));
        }
    }

    private static int bfs(int startX, int startY) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] { startX, startY, 1 });
        visited[startX][startY] = true;

        int count = 1; // 맨홀도 포함됨

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];
            int time = current[2];

            if (time >= L)
                continue;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (isValid(nx, ny) && !visited[nx][ny] && canMove(x, y, nx, ny)) {
                    visited[nx][ny] = true;
                    queue.offer(new int[] { nx, ny, time + 1 });
                    count++;
                }
            }
        }
        return count;
    }

    private static boolean isValid(int x, int y) {
        return (x >= 0 && x < N && y >= 0 && y < M);
    }

    private static boolean canMove(int x1, int y1, int x2, int y2) {
        int current = map[x1][y1];
        int next = map[x2][y2];

        if (x2 == x1 - 1 && type[current][0] == 1 && type[next][1] == 1)
            return true;
        if (x2 == x1 + 1 && type[current][1] == 1 && type[next][0] == 1)
            return true;
        if (y2 == y1 - 1 && type[current][2] == 1 && type[next][3] == 1)
            return true;
        if (y2 == y1 + 1 && type[current][3] == 1 && type[next][2] == 1)
            return true;

        return false;
    }
}
