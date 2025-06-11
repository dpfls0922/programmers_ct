import java.io.*;
import java.util.*;

public class Main_2007년 {
    public static int x, y;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        System.out.println(solve());
    }

    private static String solve() {
        String[] week = {"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"};
        int[] days = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        // 1월 1일부터 x월 y일까지 며칠이 지났는지를 계산
        int d = y;
        while (x > 0) {
            d += days[x - 1];
            x -= 1;
        }
        return week[d % 7];
    }
}