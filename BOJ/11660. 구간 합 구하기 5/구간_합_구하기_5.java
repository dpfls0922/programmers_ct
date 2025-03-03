import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 구간_합_구하기_5 {
    private static int N, M;
    private static int[][] table;
    private static int[][] cumulativeSum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        table = new int[N + 1][N + 1];
        cumulativeSum = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                table[i][j] = Integer.parseInt(st.nextToken());
                cumulativeSum[i][j] = cumulativeSum[i][j - 1] + table[i][j];
            }
        }
        
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            sb.append(sum(x1, y1, x2, y2)).append("\n");
        }
        System.out.println(sb.toString());
    }

    private static int sum(int x1, int y1, int x2, int y2) {
        int sum = 0;

        for (int i = x1; i <= x2; i++) {
            sum += cumulativeSum[i][y2] - cumulativeSum[i][y1 - 1];
        }
        return sum;
    }
}
