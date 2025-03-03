import java.io.*;
import java.util.*;

public class 구간_합_구하기 {
    private static int N, M, K;
    private static long[] nums;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        nums = new long[N + 1];
        for (int i = 1; i <= N; i++) {
            nums[i] = Long.parseLong(br.readLine());
        }

        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());
            if (a == 1) {
                nums[b] = c;
            } else if (a == 2) {
                sb.append(cumulativeSum(b, c)).append("\n");
            }
        }
        System.out.println(sb.toString());
    }

    private static long cumulativeSum(int start, long end) {
        long sum = 0;

        for (int i = start; i <= end; i++) {
            sum += nums[i];
        }
        return sum;
    }
}