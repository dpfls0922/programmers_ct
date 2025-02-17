import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 동전_0 {
    private static int N, K;
    private static int minCoinCount = Integer.MAX_VALUE;
    private static int[] coins;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        coins = new int[N];
        for (int i = 0; i < N; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }
        findMinimumCoin();
        System.out.println(minCoinCount);
    }

    private static void findMinimumCoin() {
        int count = 0;

        for (int i = N - 1; i >= 0; i--) {
            if (coins[i] > K) continue;

            int quotient = K / coins[i];
            K -= coins[i] * quotient;
            count += quotient;

            if (K == 0) break;
        }
        minCoinCount = Math.min(minCoinCount, count);
    }
}