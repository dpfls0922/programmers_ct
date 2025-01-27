import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 곱셈 {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());
        long C = Long.parseLong(st.nextToken());

        System.out.println(power(A, B, C));
    }

    private static long power(long A, long B, long C) {
        if (B == 0) {
            return 1;
        }

        // 짝수일 때, a^2n = a^n x a^n
        long half = power(A, B / 2, C);
        if (B % 2 == 0) {
            return (half * half) % C;
        }
        // 홀수일 때, a^(2n + 1) = a x a^2n = a x a^n x a^n
        return (A * ((half * half) % C)) % C;
    }
}
