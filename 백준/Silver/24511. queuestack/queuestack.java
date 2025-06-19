import java.io.*;
import java.util.*;

public class Main {
    private static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] status = new int[N];
        for (int i = 0; i < N; i++) {
            status[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int[] nums = new int[N];
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int[] target = new int[M];
        for (int i = 0; i < M; i++) {
            target[i] = Integer.parseInt(st.nextToken());
        }

        ArrayDeque<Integer> queue = new ArrayDeque<>();
        for (int j = 0; j < N; j++) {
            if (status[j] == 0) {
                queue.addFirst(nums[j]);
            }
        }
        for (int i = 0; i < M; i++) {
            queue.add(target[i]);
            sb.append(queue.poll()).append(" ");
        }
        System.out.println(sb.toString());
    }
}