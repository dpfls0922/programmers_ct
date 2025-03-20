import java.io.*;
import java.util.*;

public class 랜선_자르기 {
    static int N, K;
    static long result = 0;
    static long[] line;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        line = new long[K];
        for (int i = 0; i < K; i++) {
            line[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(line);
        solve();
        System.out.println(result);
    }

    private static void solve() {
        long low = 0;
        long high = line[K - 1] + 1;

        while (low + 1 < high) {
            long mid = (low + high) / 2;

            if (canMakeNLine(mid)) {
                result = mid;
                low = mid;
            } else {
                high = mid;
            }
        }
    }

    // N개 이상의 랜선을 만들 수 있는 경우
    private static boolean canMakeNLine(long mid) {
        int count = 0;
        for (int i = 0; i < K; i++) {
            count += line[i] / mid;
        }
        return count >= N;
    }
}