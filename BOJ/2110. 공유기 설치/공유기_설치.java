import java.io.*;
import java.util.*;

public class 공유기_설치 {
    private static int N, C;
    private static int result= 0;
    private static int[] houses;

    public static void main(String[] args) throws NumberFormatException, IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        houses = new int[N];
        for (int i = 0; i < N; i++) {
            houses[i] = Integer.parseInt(br.readLine());
        }

        // 집 좌표 정렬
        Arrays.sort(houses);

        // 가장 인접한 두 설치 지점 사이의 거리를 최대로 하는 프로그램
        solve();
        System.out.println(result);
    }

    private static void solve() {
        // 이분 탐색 범위 설정
        int low = 1; // 최소 거리
        int high = houses[N - 1] - houses[0]; // 최대 거리

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (canInstall(mid)) {
                result = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
    }

    private static boolean canInstall(int distance) {
        int count = 1; // 첫 번째 집에 공유기 설치
        int lastInstalled = houses[0]; // 마지막 설치 위치

        for (int i = 1; i < N; i++) {
            if (houses[i] - lastInstalled >= distance) {
                count++;
                lastInstalled = houses[i];
            }

            if (count >= C) {
                return true;
            }
        }
        return false;
    }
}
