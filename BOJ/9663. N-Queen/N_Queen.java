import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N_Queen {
    private static int N;
    private static int count = 0;
    private static boolean[] isUsed1;  // 열
    private static boolean[] isUsed2;  // 아래로(/) 내려가는 대각선
    private static boolean[] isUsed3;  // 위로(\) 올라가는 대각선

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        isUsed1 = new boolean[30];
        isUsed2 = new boolean[30];
        isUsed3 = new boolean[30];
        backtracking(0);
        System.out.println(count);
    }

    private static void backtracking(int current) { // 현재 열의 수를 택함
        if (current == N) {
            count++;
            return;
        }

        for (int i = 0; i < N; i++) {
            if (isUsed1[i] || isUsed2[i + current] || isUsed3[current - i + N - 1])
                continue;
            isUsed1[i] = true;
            isUsed2[i + current] = true;
            isUsed3[current - i + N - 1] = true;

            backtracking(current + 1);

            isUsed1[i] = false;
            isUsed2[i + current] = false;
            isUsed3[current - i + N - 1] = false;
        }
    }
}