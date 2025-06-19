import java.io.*;
import java.util.*;

public class Main {
    private static int N, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();
        sb.append("<");

        ArrayDeque<Integer> deque = new ArrayDeque<>();
        for (int i = 1; i <= N; i++) {
            deque.add(i);
        }

        while (!deque.isEmpty()) {
            for (int round = 0; round < K - 1; round++) {
                deque.add(deque.poll());
            }
            sb.append(deque.pollFirst());
            if (!deque.isEmpty()) sb.append(", ");
        }
        sb.append(">");
        System.out.println(sb.toString());
    }
}