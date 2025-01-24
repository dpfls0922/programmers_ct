import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 부분수열의_합 {
    private static int N;
    private static int S;
    private static int count = 0;
    private static int[] sequence;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 입력받기
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        sequence = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            sequence[i] = Integer.parseInt(st.nextToken());
        }

        // 백트래킹
        findPossible(0, 0);
        if (S == 0) count--;
        System.out.println(count);
    }

    private static void findPossible(int length, int currentSum) {
        if (length == N) {
            if (currentSum == S) {
                count++;
            }
            return;
        }

        findPossible(length + 1, currentSum);
        findPossible(length + 1, currentSum + sequence[length]);
    }
}