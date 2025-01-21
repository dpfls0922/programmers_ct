import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 파핑파핑_지뢰찾기 {
    private static int N;
    private static char[][] map;
    private static boolean[][] visited;
    private static int[][] directions = {
            { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 },
            { -1, -1 }, { -1, 1 }, { 1, -1 }, { 1, 1 }
    };

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(br.readLine());
            map = new char[N][N];
            visited = new boolean[N][N];

            for (int i = 0; i < N; i++) {
                map[i] = br.readLine().toCharArray();
            }

            // 지뢰찾기
            int clickCount = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == '.' && !visited[i][j] && isSafeZone(i, j)) {
                        dfs(i, j);
                        clickCount++;
                    }
                }
            }
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == '.' && !visited[i][j]) {
                        clickCount++;
                    }
                }
            }
            System.out.println("#" + test_case + " " + clickCount);
        }
    }

    private static void dfs(int x, int y) {
        visited[x][y] = true;

        for (int[] direction : directions) {
            int nx = x + direction[0];
            int ny = y + direction[1];

            if (isValid(nx, ny) && map[nx][ny] == '.' && !visited[nx][ny] && isSafeZone(nx, ny)) {
                dfs(nx, ny);
            } else if (isValid(nx, ny) && !visited[nx][ny]) {
                visited[nx][ny] = true;
            }
        }
    }

    private static boolean isSafeZone(int x, int y) {
        for (int[] direction : directions) {
            int nx = x + direction[0];
            int ny = y + direction[1];

            if (isValid(nx, ny) && map[nx][ny] == '*') {
                return false;
            }
        }
        return true;
    }

    private static boolean isValid(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}
