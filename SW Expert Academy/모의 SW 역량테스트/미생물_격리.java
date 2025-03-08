import java.io.*;
import java.util.*;

public class 미생물_격리 {
    static class Microbe {
        int x;
        int y;
        int count;
        int direction;

        public Microbe(int x, int y, int count, int direction) {
            this.x = x;
            this.y = y;
            this.count = count;
            this.direction = direction;
        }
    }

    static int N, M, K; // 셀의 개수, 격리 시간, 미생물 군집의 개수
    static List<Microbe> group; // 세로위치, 가로위치, 미생물 수, 이동방향
    static int[][] map;
    static int[][] directions = {{0, 0}, {-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // X, 상하좌우
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            group = new ArrayList<>();
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int count = Integer.parseInt(st.nextToken());
                int direction = Integer.parseInt(st.nextToken());

                group.add(new Microbe(x, y, count, direction));
            }

            solve();
            sb.append("#").append(tc).append(" ").append(countTotal()).append("\n");
        }
        System.out.println(sb.toString());
    }

    private static void solve() {
        while (M-- > 0) {
            List<Microbe> temp = new ArrayList<>();
            map = new int[N][N];
            for (int i = 0; i < N; i++) {
                Arrays.fill(map[i], -1);
            }

            group.sort(((o1, o2) -> o2.count - o1.count));
            for (int i = 0; i < group.size(); i++) {
                Microbe microbe = group.get(i);

                if (microbe.count == 0) continue;

                int direction = microbe.direction;
                int nx = microbe.x + directions[direction][0];
                int ny = microbe.y + directions[direction][1];
                microbe.x = nx;
                microbe.y = ny;

                if (isBoundary(nx, ny)) {
                    // 미생물 수 반절 죽고, 이동방향 반대로 바뀜
                    microbe.count /= 2;
                    microbe.direction = reverseDirection(direction);
                }

                if (map[nx][ny] == -1) {
                    temp.add(microbe);
                    map[nx][ny] = temp.size() - 1; // 미생물이 temp 리스트에서 몇 번째로 추가되었는지
                } else {
                    temp.get(map[nx][ny]).count += microbe.count;
                }
            }
            group = temp;
        }
    }

    private static boolean isBoundary(int x, int y) {
        return x == 0 || x == N - 1 || y == 0 || y == N - 1;
    }

    private static int reverseDirection(int direction) {
        if (direction == 1) return 2;
        else if (direction == 2) return 1;
        else if (direction == 3) return 4;
        else if (direction == 4) return 3;
        return -1;
    }

    private static int countTotal() {
        int total = 0;
        for (Microbe microbe : group) {
            total += microbe.count;
        }
        return total;
    }
}