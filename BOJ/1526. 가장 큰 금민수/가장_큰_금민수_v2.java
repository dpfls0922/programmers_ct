import java.io.*;
import java.util.*;

// v1은 구현은 간단하지만 N이 클 때 느릴 수 있음
// dfs/bfs로 숫자를 직접 만들며 탐색하므로 더 효율적
public class 가장_큰_금민수_v2 {
    public static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        System.out.println(bfs());
    }

    private static int bfs() {
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.add(4);
        queue.add(7);

        int max = 0;
        while (!queue.isEmpty()) {
            int num = queue.poll();
            if (num > N) continue;

            max = Math.max(max, num);

            queue.add(10 * num + 4);
            queue.add(10 * num + 7);
        }
        return max;
    }
}