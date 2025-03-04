import java.io.*;
import java.util.*;

public class 보호필름 {
    private static int D, W, K;
    private static int minMedicine;
    private static int[][] film;
    private static int[] chemicals;// 약품 투약 정보

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            D = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            film = new int[D][W];
            for (int i = 0; i < D; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++) {
                    film[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            minMedicine = Integer.MAX_VALUE;
            chemicals = new int[D];
            findMinMedicine(0, 0);

            sb.append("#" + tc).append(" ").append(minMedicine).append("\n");
        }
        System.out.println(sb.toString());
    }

    private static void findMinMedicine(int inputCnt, int row) {
        if (inputCnt > K) return;
        if (inputCnt >= minMedicine) return;

        if (row == D) {
            if (checkPass()) {
                minMedicine = Math.min(minMedicine, inputCnt);
            }
            return;
        }

        // 약품 투입(A, B, 미투입) 선택
        for (int i = -1; i < 3; i++) {
            chemicals[row] = i;

            if (i == -1) {
                findMinMedicine(inputCnt, row + 1);
            } else {
                findMinMedicine(inputCnt + 1, row + 1);
            }
        }
    }

    private static boolean checkPass() {
        for (int j = 0; j < W; j++) {
            int count = 1;
            for (int i = 0; i < D - 1; i++) {

                //투여 안 했으면 원본 배열 값, 투여 했으면 약품 투여 정보 배열에서 가져오기
                int current = chemicals[i] == -1 ? film[i][j]: chemicals[i];
                int next = chemicals[i + 1] == -1 ? film[i + 1][j]: chemicals[i + 1];

                if (current == next) {
                    count++;
                    if (count >= K) {
                        break;
                    }
                } else {
                    count = 1;
                }
            }
            if(count < K) {
                return false;
            }
        }
        return true;
    }
}