import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class 줄기세포배양 {
    private static int N, M, K;
    private static final int INACTIVE = 0, ACTIVE = 1, DIED = 2;

    private static boolean[][] visited;
    // 관리할 세포들을 담을 큐 생성 (비활성화 + 활성화)
    private static Queue<Cell> map;
    // 활성화된 세포를 담을 우선순위 큐 생성
    // 동시에 번식하려고 하는 경우 생명력 수치가 높은 줄기 세포가 차지
    private static PriorityQueue<Cell> priorityQueue;
    private static int[][] directions = {
        {-1, 0}, {1, 0}, {0, -1}, {0, 1} // 상하좌우
    };

    static class Cell {
        int x;
        int y;
        int status; // 상태 정보 0: 비활성, 1:활성, 2:죽은 세포
        int life;   // 생명력
        int time;   // 살 수 있는 시간

        Cell(int x, int y, int life) {
            this.x = x;
            this.y = y;
            this.life = life;
            this.time = life;
        }

        // 시간마다 상태 정보 변경하는 메서드
        void updateStatusBasedOnTime() {
            if (status == INACTIVE) {
                if (--time == 0) {
                    status = ACTIVE;
                }
            } else if (status == ACTIVE) {
                if (++time == life) {
                    status = DIED;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            visited = new boolean[N + K + 1][M + K + 1];
            map = new LinkedList<>();;
            priorityQueue = new PriorityQueue<>((Cell a, Cell b) -> b.life - a.life);

            // 중앙 배치
            for (int i = K / 2 + 1; i < N + K / 2 + 1; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = K / 2 + 1; j < M + K / 2 + 1; j++) {
                    int life = Integer.parseInt(st.nextToken());

                    if (life > 0) { // 세포가 존재하는 경우만 추가
                        visited[i][j] = true;
                        map.add(new Cell(i, j, life)); // 비활성 상태로 큐에 추가
                    }
                }
            }
            simulateCellsLifeCycle();
            System.out.println("#" + tc + " " + map.size());
        }
    }

    private static void simulateCellsLifeCycle() {
        while (K-- > 0) {
            int size = map.size();
            for (int i = 0; i < size; i++) {
                Cell cell = map.poll();

                if (cell.status == ACTIVE) {
                    priorityQueue.add(cell);
                }
                cell.updateStatusBasedOnTime();
                if (cell.status == DIED) {
                    continue;
                }
                map.add(cell);
            }

            // 활성화된 세포들 번식
            while (!priorityQueue.isEmpty()) {
                Cell cell = priorityQueue.poll();

                // 4방향 번식
                for (int i = 0; i < 4; i++) {
                    int nx = cell.x + directions[i][0];
                    int ny = cell.y + directions[i][1];

                    if (visited[nx][ny]) continue;
                    visited[nx][ny]=true;
                    // 다음 시간에 세포 배양할 수 있게 큐에 넣어주기
                    map.add(new Cell (nx, ny, cell.life));
                }
            }
        }
    }
}

// 중앙 배치 목적
// 세포를 배양할 때, 세포들이 번식하면서 주변 영역으로 확장된다.
// 따라서 세포가 번식할 공간을 고려해, 초기 세포를 중앙에 배치하여 가장 효율적으로 시뮬레이션을 진행할 수 있다.
// 배양 시간 K를 고려해, N + K x M + K 크기의 큰 2차원 배열을 사용한다.
// 이 배열의 중심 영역에 초기 세포를 배치하기 위해, 배치 시작 위치를 K / 2 + 1로 계산한다.

// 우선순위 큐 목적
// 동시에 번식하려는 세포가 같은 위치를 차지하려고 하면, 생명력 수치가 더 높은 세포가 차지하도록 규칙을 적용한다.
// 생명력(life)이 높은 세포가 우선적으로 번식할 수 있도록 정렬한다.

// 활성화된 세포가 ACTIVE 상태가 되면, 번식할 수 있는 상태로 우선순위 큐에 추가된다.
// 번식 시에는 세포가 상하좌우로 한 칸씩 이동하며 새로운 세포를 생성한다.
// 번식하려는 위치가 아직 방문되지 않았다면, 새로운 Cell 객체를 생성해 map 큐에 추가한다.

// 비활성 세포 → 활성 세포
// 각 세포는 updateStatusBasedOnTime()을 통해 시간이 흐르며 상태가 변한다.
// 활성 상태가 된 세포는 priorityQueue에 추가되고, 생명력(life)이 높은 순서대로 번식한다.
// 번식이 끝난 세포는 자신의 생명력 수명이 다 되면 죽은 상태(DIED)가 되어 더 이상 번식하지 않는다.