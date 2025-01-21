import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Magnetic {
    static int size;
    static int N = 1;
    static int S = 2;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int test_case = 1; test_case <= 10; test_case++) {
            // 입력 받기
            size = Integer.parseInt(br.readLine());
            int[][] arr = new int[size][size];
            for (int i = 0; i < size; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < size; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // 교착상태 개수 구하기
            System.out.println("#" + test_case + " " + deadlock(arr));
        }
    }

    private static int deadlock(int[][] arr) {
        int count = 0;

        for (int i = 0; i < size; i++) {
            boolean meetN = false;
            boolean meetS = false;
            for (int j = 0; j < size; j++) {
                if (arr[j][i] == S) {
                    if (meetN) {
                        count += 1;
                        meetN = false;
                    } else {
                        meetS = true;
                    }
                } else if (arr[j][i] == N) {
                    if (meetS) {
                        meetS = false;
                    }
                    meetN = true;
                }
            }
        }
        return count;
    }
}
