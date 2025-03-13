import java.io.*;
import java.util.*;

public class 바이러스 {
	private static int N, M;
	private static ArrayList<Integer>[] network;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		network = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			network[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			network[a].add(b);
			network[b].add(a);
		}
		System.out.println(bfs());
	}
	
	private static int bfs() {
		int count = 0;
		ArrayDeque<Integer> queue = new ArrayDeque<>();
		boolean[] visited = new boolean[N + 1];
		
		queue.add(1); // 1번 컴퓨터가 감염됨
		visited[1] = true;
		
		while (!queue.isEmpty()) {
			int current = queue.poll();
			
			for (Integer neighbor : network[current]) {
				if (!visited[neighbor]) {
					visited[neighbor] = true;
					queue.add(neighbor);
					count++;
				}
			}
		}
		return count;
	}
}