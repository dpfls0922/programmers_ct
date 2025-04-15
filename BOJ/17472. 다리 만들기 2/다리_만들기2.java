import java.io.*;
import java.util.*;

/*
1. 섬 번호 매기기
2. 각 섬에서 가능한 모든 다리 후보 찾기 (길이 ≥ 2, 직선으로만 가능)
3. 최소 스패닝 트리(MST) 알고리즘으로 섬들 연결하기
*/

public class 다리_만들기2 {
    static class Bridge {
        int from, to, length;

        public Bridge(int from, int to, int length) {
            this.from = from;
            this.to = to;
            this.length = length;
        }
    }

    static int N, M;
    static int[] parents;
    static int[][] map;
    static boolean[][] visited;
    private static int landNum = 1;
    private static int[][] isLandMap;
    private static int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private static List<Bridge> bridges = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        isLandMap = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1 && !visited[i][j]) {
                    isLandMap[i][j] = landNum;
                    markIslands(i, j, landNum++);
                }
            }
        }

        findBridges();
        Collections.sort(bridges, (a, b) -> a.length - b.length);

        make();
        int totalLength = 0, count = 0;
        for (Bridge bridge : bridges) {
            if (union(bridge.from, bridge.to)) {
                totalLength += bridge.length;
                count++;
            }
        }

        // 모든 섬이 연결되었는지 확인
        if (count == landNum - 2) {
            System.out.println(totalLength);
        } else {
            System.out.println(-1);
        }
    }

    private static void markIslands(int startX, int startY, int landNum) {
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        queue.add(new int[] {startX, startY, landNum});
        visited[startX][startY] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0], y = current[1];

            for (int[] dir : directions) {
                int nx = x + dir[0];
                int ny = y + dir[1];

                if (!isValid(nx, ny) || visited[nx][ny]) continue;
                if (map[nx][ny] == 1) {
                    visited[nx][ny] = true;
                    isLandMap[nx][ny] = landNum;
                    queue.add(new int[] {nx, ny});
                }
            }
        }
    }

    private static boolean isValid(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    private static void findBridges() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (isLandMap[i][j] > 0) {
                    int fromLand = isLandMap[i][j];

                    for (int[] dir : directions) {
                        int nx = i + dir[0];
                        int ny = j + dir[1];
                        int length = 0;

                        while (isValid(nx, ny)) {
                            if (isLandMap[nx][ny] == fromLand) break;
                            if (isLandMap[nx][ny] != 0) {
                                if (length >= 2) {
                                    int toLand = isLandMap[nx][ny];
                                    bridges.add(new Bridge(fromLand, toLand, length));
                                }
                                break;
                            }
                            nx += dir[0];
                            ny += dir[1];
                            length++;
                        }
                    }
                }
            }
        }
    }

    private static void make() {
        parents = new int[landNum];
        for (int i = 1; i < landNum; i++) {
            parents[i] = i;
        }
    }

    private static int find(int x){
        if (x == parents[x]) return x;
        return parents[x] = find(parents[x]);
    }

    private static boolean union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA == rootB) return false;
        if (rootA > rootB) parents[rootA] = rootB;
        else parents[rootB] = rootA;
        return true;
    }
}