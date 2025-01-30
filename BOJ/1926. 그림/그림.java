import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 그림 {
    private static int n;
    private static int m;
    private static int count = 0;
    private static int max_area = 0;
    private static int[][] paintings;
    private static boolean[][] visited;
    private static int[][] directions = {
        {-1, 0}, {1, 0},
        {0, -1}, {0, 1}
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        paintings = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                paintings[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j] && paintings[i][j] == 1) {
                    count += 1;
                    max_area = Math.max(max_area, checkPaintings(i, j));
                }
            }
        }
        System.out.println(count);
        System.out.println(max_area);
    }

    private static int checkPaintings(int currentX, int currentY) {
        Queue<int[]> queue = new LinkedList<>();
        visited[currentX][currentY] = true;
        queue.add(new int[] {currentX, currentY});
        int area = 1;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            for (int i = 0; i < 4; i++) {
                int x = current[0] + directions[i][0];
                int y = current[1] + directions[i][1];

                if (isValid(x, y) && !visited[x][y] && paintings[x][y] == 1) {
                    visited[x][y] = true;
                    queue.add(new int[] {x, y});
                    area += 1;
                }
            }
        }
        return area;
    }

    private static boolean isValid(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < m;
    }
}