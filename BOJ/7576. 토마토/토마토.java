import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	private static int PERFECT = 1;
	private static int NOT_PERFECT = 0;
	private static int NO = -1;
	
	private static int N, M;
	private static int min_day = Integer.MAX_VALUE;
	private static int[][] box;
	private static Queue<int[]> queue;
	private static boolean[][] visited;
	private static int[][] directions = {
			{-1, 0}, {1, 0},
			{0, -1}, {0, 1}
	};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		box = new int[N][M];
		visited = new boolean[N][M];
		queue = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				box[i][j] = Integer.parseInt(st.nextToken());
				
				if (box[i][j] == 1) {
					visited[i][j] = true;
					queue.add(new int[] {i, j, 0});
				}
			}
		}
		
		int day = countPerfectTomatoDay();
		min_day = Math.min(min_day, day);
				
		if (!isAllPerfect()) {
			System.out.println(-1);
		} else {
			System.out.println(min_day);
		}
	}

	private static int countPerfectTomatoDay() {
		int day = 0;
		while (!queue.isEmpty()) {
			int[] current = queue.poll();
			for (int i = 0; i < 4; i++) {
				int nx = current[0] + directions[i][0];
				int ny = current[1] + directions[i][1];
				day = current[2];
				
				if (!isValid(nx, ny)) continue;
				if (visited[nx][ny]) continue;
				
				if (box[nx][ny] == 0) {
					visited[nx][ny] = true;
					box[nx][ny] = 1;
					queue.add(new int[] {nx, ny, day + 1});
				}
			}
		}
		return day;
	}
	
	private static boolean isValid(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < M;
	}
	
	
	private static boolean isAllPerfect() {
		boolean isPerfect = true;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (box[i][j] == 0) {
					isPerfect = false;
				}
			}
		}
		return isPerfect;
	}
}