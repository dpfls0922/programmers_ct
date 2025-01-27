import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Z {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        System.out.println(Z(N, r, c));
    }

    private static int Z(int N, int r, int c) {
        if (N == 0) {
            return 0;
        }

        int half = 1 << (N - 1);
        if (r < half && c < half) return Z(N - 1, r, c);
        if (r < half && c >= half) return half * half + Z(N - 1, r, c - half);
        if (r >= half && c < half) return 2 * half * half + Z(N - 1, r - half, c);
        return 3 * half * half + Z(N - 1, r - half, c - half);
    }
}