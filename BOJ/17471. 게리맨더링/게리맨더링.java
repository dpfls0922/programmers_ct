import java.io.*;
import java.util.*;

public class 게리맨더링 {
    private static int N;
    private static int minDiff = Integer.MAX_VALUE;
    private static int[] population;
    private static List<Integer>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        graph = new ArrayList[N + 1];
        population = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            population[i] = Integer.parseInt(st.nextToken());
            graph[i] = new ArrayList<>();
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int size = Integer.parseInt(st.nextToken());

            for (int j = 0; j < size; j++) {
                graph[i].add(Integer.parseInt(st.nextToken()));
            }
        }

        // 두 선거구 나누기 (선택 or 선택 x)
        // 연결성을 확인 후 유효하다면 인구 차이 구하기
        seperate();
        System.out.println(minDiff == Integer.MAX_VALUE ? -1 : minDiff);
    }

    private static void seperate() {
        for (int bitmask = 1; bitmask < (1 << N) - 1; bitmask++) { // 적어도 하나씩은 있어야 함
            List<Integer> groupA = new ArrayList<>();
            List<Integer> groupB = new ArrayList<>();

            for (int j = 0; j < N; j++) {
                if ((bitmask & (1 << j)) != 0) {
                    groupA.add(j + 1); // 1-based index
                } else {
                    groupB.add(j + 1);
                }
            }

            if (isConnected(groupA) && isConnected(groupB)) {
                findMinDiff(groupA, groupB);
            }
        }
    }

    private static boolean isConnected(List<Integer> group) {
        boolean[] visited = new boolean[N + 1];
        ArrayDeque<Integer> queue = new ArrayDeque<>();

        int startPoint = group.get(0);
        queue.add(startPoint);
        visited[startPoint] = true;

        int count = 1;
        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (Integer neighbor : graph[current]) {
                if (!visited[neighbor] && group.contains(neighbor)) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                    count++;
                }
            }
        }
        return group.size() == count;
    }

    private static void findMinDiff(List<Integer> groupA, List<Integer> groupB) {
        int sumA = 0, sumB = 0;

        for (int x : groupA) {
            sumA += population[x];
        }
        for (int x : groupB) {
            sumB += population[x];
        }
        minDiff = Math.min(minDiff, Math.abs(sumA - sumB));
    }
}