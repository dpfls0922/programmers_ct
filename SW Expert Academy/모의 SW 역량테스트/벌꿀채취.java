import java.util.*;
import java.io.*;

public class 벌꿀채취 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, M, C;
    static int[][] map;

    public static void main(String[] args) throws Exception {
        int TC = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= TC; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            map = new int[N][N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            System.out.println("#" + tc + " " + solve());
        }
    }

    private static int solve() {
        int maxProfit = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j <= N - M; j++) {
                int max1 = getMaxHoney(Arrays.copyOfRange(map[i], j, j + M));

                // 같은 행에서 선택
                int max2 = 0;
                for (int j2 = j + M; j2 <= N - M; j2++) {
                    max2 = Math.max(max2, getMaxHoney(Arrays.copyOfRange(map[i], j2, j2 + M)));
                }

                // 다른 행에서 선택
                for (int i2 = i + 1; i2 < N; i2++) {
                    for (int j2 = 0; j2 <= N - M; j2++) {
                        max2 = Math.max(max2, getMaxHoney(Arrays.copyOfRange(map[i2], j2, j2 + M)));
                    }
                }

                maxProfit = Math.max(maxProfit, max1 + max2);
            }
        }
        return maxProfit;
    }

    private static int getMaxHoney(int[] honey) {
        int maxBenefit = 0;
        int size = M;

        for (int subset = 0; subset < (1 << size); subset++) {
            int sum = 0, profit = 0;

            for (int i = 0; i < size; i++) {
                if ((subset & (1 << i)) != 0) {
                    sum += honey[i];
                    profit += honey[i] * honey[i];
                }
            }

            // C 이하일 때만 갱신
            if (sum <= C) {
                maxBenefit = Math.max(maxBenefit, profit);
            }
        }
        return maxBenefit;
    }
}