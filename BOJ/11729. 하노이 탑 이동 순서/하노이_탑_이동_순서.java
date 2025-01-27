import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 하노이_탑_이동_순서 {
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        System.out.println((1 << N) - 1); // 2^N - 1
        hanoi(1, 3, N);
        System.out.println(sb);
    }

    // 기둥 a에서 기둥 b로 n개의 원판 이동
    private static void hanoi(int a, int b, int n) {
        if (n == 1) {
            sb.append(a).append(" ").append(b).append("\n");
            return;
        }
        hanoi(a, 6 - a - b, n - 1);
        hanoi(a, b, 1);
        hanoi(6 - a - b, b, n - 1);
    }
}
