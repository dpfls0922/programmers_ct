import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Solution {
	static int OBSTACLE = 1;
	static int STORM = 2;
	static int N, current_x, current_y, destination_x, destination_y;

	static int [][]map;
	static boolean [][]visited;
	static int []dx = {-1, 1, 0, 0};
	static int []dy = {0, 0, -1, 1};

	public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		
		for(int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			visited = new boolean[N][N];
			
			// 입력 받기
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			st = new StringTokenizer(br.readLine());
			current_x = Integer.parseInt(st.nextToken());
			current_y = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());
			destination_x = Integer.parseInt(st.nextToken());
			destination_y = Integer.parseInt(st.nextToken());

			// 가장 빠른 길 찾기
			System.out.println("#" + test_case + " " + bfs(current_x, current_y));
		}
	}
	
	private static int bfs(int current_x, int current_y) {
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] {current_x, current_y, 0});
		visited[current_x][current_y] = true;

		while (!queue.isEmpty()) {
			int[] current = queue.poll();
			int x = current[0];
			int y = current[1];
			int time = current[2];
            int stormState = (time % 3);  // 소용돌이 상태 (0, 1, 2초마다 반복)

			if (x == destination_x && y == destination_y) {
				return time;
			}

			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];

				if (isValid(nx, ny) && !visited[nx][ny]) {
					if (map[nx][ny] == OBSTACLE) continue;
					if (map[nx][ny] == STORM) {
						if (stormState != 2) {
							queue.offer(new int[] {x, y, time + 1});
							continue;
						}
					}
					
					visited[nx][ny] = true;
					queue.offer(new int[] {nx, ny, time + 1});
				}
			}
		}
        return -1;
	}
	
	private static boolean isValid(int x, int y) {
		return (x >= 0 && x < N && y >= 0 && y < N);
	}
}