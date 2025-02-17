import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class 나무높이 {
    private static int N;
    private static int odd = 0;
    private static int even = 0;
    private static int[] tree;
    private static int[] growthNeeded;
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
 
            tree = new int[N];
            growthNeeded = new int[N];
            int max = Integer.MIN_VALUE;
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                tree[i] = Integer.parseInt(st.nextToken());
                if (tree[i] > max) {
                    max = tree[i];
                }
            }
 
            for (int i = 0; i < N; i++) {
                growthNeeded[i] = max - tree[i];
                if (growthNeeded[i] != 0) {
                    odd += growthNeeded[i] % 2;
                    even += growthNeeded[i] / 2;
                }
            }
 
            int day = plantTree();
            System.out.println("#" + tc + " " + day);
        }
    }
 
    private static int plantTree() {
        if (even == 0 && odd == 0) return 0;
         
        int day = 1;
        while (true) {
            if (odd > 0 && day % 2 == 1) {
                odd--;
            } else if (even > 0 && day % 2 == 0) {
                even--;
            } else if (day % 2 == 1 && odd == 0 && even > 1) {
                odd += 1; // 짝수를 1 + 1로 나누고 지금 홀수를 하나 쓰고 다음에 홀수 또 쓰기
                even--;
            }
 
            if (odd == 0 && even == 0) break;
            day++;
        }
        return day;
    }
}