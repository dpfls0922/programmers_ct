import java.io.*;
import java.util.*;

public class Main {
    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        ArrayDeque<int[]> deque = new ArrayDeque<>();
        for (int i = 1; i <= N; i++) {
            int move = Integer.parseInt(st.nextToken());
            deque.add(new int[]{i, move});
        }

        while (!deque.isEmpty()) {
            int[] current = deque.poll();
            sb.append(current[0]).append(" ");
            int move = current[1];

            if (deque.isEmpty()) break;
            if (move > 0) {
                for (int i = 0; i < move - 1; i++) {
                    deque.addLast(deque.pollFirst());
                }
            } else {
                for (int i = 0; i < Math.abs(move); i++) {
                    deque.addFirst(deque.pollLast());
                }
            }
        }
        System.out.println(sb.toString());
    }
}