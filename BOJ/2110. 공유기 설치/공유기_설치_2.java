import java.util.*;
import java.io.*;

public class 공유기_설치_2 {
    private static int N, C;
    private static int[] positions;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        positions = new int[N];
        for (int i = 0; i < N; i++) {
            positions[i] = Integer.parseInt(br.readLine());
        }
        solve();
    }

    // 목표는 공유기 사이의 "최소 거리"를 최대화하는 것
    private static void solve() {
        Arrays.sort(positions);

        int start = 0;  // 최소 거리
        int end = positions[N - 1] - positions[0] + 1; // 최대 거리 + 1

        while (start + 1 < end) {
            int mid = (start + end) / 2;

            if (install(mid) >= C) {
                start = mid; // mid 거리로도 C개 이상 설치 가능 → 더 늘려볼 수 있음
            } else {
                end = mid;
            }
        }
        System.out.println(start);
    }

    // mid 거리 이상으로 공유기를 설치할 수 있는 개수
    private static int install(int mid) {
        int count = 1; // 첫 번째 공유기는 무조건 설치
        int lastInstalled = positions[0];

        for (int position : positions) {
            int diff = position - lastInstalled;
            if (diff >= mid) {
                count++;
                lastInstalled = position;
            }
        }
        return count;
    }
}