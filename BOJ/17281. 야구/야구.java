import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static int maxScore = Integer.MIN_VALUE;
    private static int[][] scores;
    private static int[] order;
    private static boolean[] isUsed;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        scores = new int[N + 1][10];
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= 9; j++) {
                scores[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        order = new int[10];  // 선수 순서
        isUsed = new boolean[10];

        order[4] = 1;
        isUsed[1] = true;
        permutation(1);
        System.out.println(maxScore);
    }

    private static void permutation(int count) {
        if (count == 10) {
            maxScore = Math.max(maxScore, calculateScore());
            return;
        }

        if (count == 4) {
            permutation(count + 1);
            return;
        }

        for (int i = 2; i <= 9; i++) {
            if (!isUsed[i]) {
                isUsed[i] = true;
                order[count] = i;
                permutation(count + 1);
                isUsed[i] = false;
            }
        }
    }

    private static int calculateScore() {
        int score = 0;
        int player = 1;

        for (int times = 1; times <= N; times++) {
            boolean[] base = new boolean[4];
            int out = 0;

            while (out < 3) {
                int move = scores[times][order[player]]; // 현재 타석 결과
                player = (player % 9) + 1;

                if (move == 0) {
                    out++;
                    continue;
                }

                // 주자 이동 처리
                for (int i = 3; i >= 0; i--) {
                    if (base[i]) {
                        if (i + move > 3) { // 득점
                            score++;
                        } else { // 주자 이동
                            base[i + move] = true;
                        }
                        base[i] = false; // 원래 자리 비우기
                    }
                }

                // 타자를 1루에 배치
                if (move > 3) {
                    score++;
                } else {
                    base[move] = true;
                }
            }
        }
        return score;
    }
}