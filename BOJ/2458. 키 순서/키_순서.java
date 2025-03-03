import java.io.*;
import java.util.*;

public class 키_순서 {
    private static int N, M;
    private static int isTaller, isSmaller;
    private static boolean[] visited;
    private static List<Integer>[] taller;
    private static List<Integer>[] smaller;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        taller  = new ArrayList[N + 1]; // 1번부터 N번까지 학생 번호
        smaller = new ArrayList[N + 1]; // 1번부터 N번까지 학생 번호
        
        for (int i = 1; i <= N; i++) {
            taller[i] = new ArrayList<>();
            smaller[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            taller[a].add(b);
            smaller[b].add(a);
        }


        int count = 0;
        for (int num = 1; num <= N; num++) {
            isTaller = 0;
            visited = new boolean[N + 1];
            countTaller(num);
            
            isSmaller = 0;
            visited = new boolean[N + 1];
            countSmaller(num);

            if (isTaller + isSmaller == N - 1) {
                count++;
            }
        }
        System.out.println(count);
    }

    private static void countTaller(int num) {
        visited[num] = true;

        for (int tallerNum : taller[num]) {
            if (!visited[tallerNum]) {
                isTaller++;
                countTaller(tallerNum);
            }
        }
    }

    private static void countSmaller(int num) {
        visited[num] = true;

        for (int smallerNum : smaller[num]) {
            if (!visited[smallerNum]) {
                isSmaller++;
                countSmaller(smallerNum);
            }
        }
    }
}