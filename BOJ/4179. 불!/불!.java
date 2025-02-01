import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static char WALL = '#';
    private static char FIRE = 'F';
    private static char START_POINT = 'J';

    private static int R;
    private static int C;
    private static char[][] map;
    private static int[][] fire_time;
    private static Queue<int[]> fire_queue = new LinkedList<>();
    private static int[][] person_time;
    private static Queue<int[]> person_queue = new LinkedList<>();
    private static final int[][] directions = {
        {-1, 0}, {1, 0},
        {0, -1}, {0, 1}
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        fire_time = new int[R][C];
        person_time = new int[R][C];
        initTime();

        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = line.charAt(j);

                if (map[i][j] == FIRE) {
                    fire_queue.add(new int[] {i, j});
                    fire_time[i][j] = 0;
                } else if (map[i][j] == START_POINT) {
                    person_queue.add(new int[] {i, j});
                    person_time[i][j] = 0;
                }
            }
        }
        simulateFireExpand();
        simulatePersonMove();
    }

    private static void initTime() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                fire_time[i][j] = -1;
                person_time[i][j] = -1;
            }
        }
    }

    private static void simulateFireExpand() {
        while (!fire_queue.isEmpty()) {
            int[] current = fire_queue.poll();
            int currentX = current[0];
            int currentY = current[1];

            for (int i = 0; i < 4; i++) {
                int nx = currentX + directions[i][0];
                int ny = currentY + directions[i][1];

                if (!isValid(nx, ny)) continue;
                if (fire_time[nx][ny] != -1 || map[nx][ny] == WALL) continue;

                fire_time[nx][ny] = fire_time[currentX][currentY] + 1;
                fire_queue.add(new int[] {nx, ny});
            }
        }
    }

    private static void simulatePersonMove() {
        while (!person_queue.isEmpty()) {
            int[] current = person_queue.poll();
            int currentX = current[0];
            int currentY = current[1];

            for (int i = 0; i < 4; i++) {
                int nx = currentX + directions[i][0];
                int ny = currentY + directions[i][1];

                if (!isValid(nx, ny)) {
                    System.out.println(person_time[currentX][currentY] + 1);
                    return;
                }
                if (person_time[nx][ny] != -1 || map[nx][ny] == WALL) continue;
                if (fire_time[nx][ny] != -1 && fire_time[nx][ny] <= person_time[currentX][currentY] + 1) continue;
                person_time[nx][ny] = person_time[currentX][currentY] + 1;
                person_queue.add(new int[] {nx, ny});
            }
        }
        System.out.println("IMPOSSIBLE");
    }

    private static boolean isValid(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }
}
