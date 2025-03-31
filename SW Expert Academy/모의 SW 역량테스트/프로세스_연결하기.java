import java.io.*;
import java.util.*;

public class 프로세스_연결하기 {
    static int N, maxCore, minLength;
    static int[][] map;
    static List<int[]> cores;
    static int[] dx = {-1, 1, 0, 0}; // 상, 하, 좌, 우
    static int[] dy = {0, 0, -1, 1};
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            cores = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if (map[i][j] == 1 && i > 0 && j > 0 && i < N - 1 && j < N - 1) cores.add(new int[]{i, j});
                }
            }

            maxCore = 0;
            minLength = Integer.MAX_VALUE;
            solve(0, 0, 0);
            sb.append("#").append(tc).append(" ").append(minLength).append("\n");
        }
        System.out.println(sb.toString());
    }

    private static void solve(int index, int coreCount, int length) {
        if (index == cores.size()) {
            if (coreCount > maxCore) {
                maxCore = coreCount;
                minLength = length;
            } else if (coreCount == maxCore) {
                minLength = Math.min(minLength, length);
            }
            return;
        }

        int[] core = cores.get(index);
        int x = core[0], y = core[1];
        for (int i = 0; i < 4; i++) {
            int nx = x, ny = y, count = 0;

            while (true) {
                nx += dx[i];
                ny += dy[i];

                if (!isValid(nx, ny)) break;
                if (map[nx][ny] != 0) {
                    count = 0;
                    break;
                }
                count++;
            }

            if (count > 0) { // 전선 설치 가능할 때
                nx = x;
                ny = y;
                for (int j = 0; j < count; j++) {
                    nx += dx[i];
                    ny += dy[i];
                    map[nx][ny] = 2;
                }

                solve(index + 1, coreCount + 1, length + count);

                // 원래 상태로 되돌리기 (전선 제거)
                nx = x;
                ny = y;
                for (int j = 0; j < count; j++) {
                    nx += dx[i];
                    ny += dy[i];
                    map[nx][ny] = 0;
                }
            }
        }

        // 연결하지 않고 넘어가는 경우
        solve(index + 1, coreCount, length);
    }

    private static boolean isValid (int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}