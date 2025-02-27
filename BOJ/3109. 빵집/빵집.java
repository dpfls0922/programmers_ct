import java.util.*;
import java.io.*;

public class Main {
	private static int R, C;
	private static int maxPipe;
	private static int[][] map;
	private static boolean[][] visited;
	private static int[][] directions = {
		{-1, 1}, {0, 1}, {1, 1}  // 오른쪽 위 대각선, 오른쪽, 오른쪽 아래 대각선
	 };
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new int[R][C];
		visited = new boolean[R][C];
		for (int i = 0; i < R; i++) {
			String line = br.readLine();
			for (int j = 0; j < C; j++) {
				if (line.charAt(j) == '.') {
					map[i][j] = 0;
				} else {
					map[i][j] = 1;
				}
			}
		}

		maxPipe = 0;
		for (int i = 0; i < R; i++) {
			if (map[i][0] != 1) {
				placeMaxPipes(i, 0, 0);
			}
		}
		System.out.println(maxPipe);
	}

	private static boolean placeMaxPipes(int x, int y, int pipe) {
		if (y == C - 1) {
			maxPipe++;
			return true;
		}

		for (int i = 0; i < 3; i++) {
			int nx = x + directions[i][0];
			int ny = y + directions[i][1];

			if (!(nx >= 0 && nx < R && ny >= 0 && ny < C)) continue;
			if (visited[nx][ny] || map[nx][ny] == 1) continue;
			
			visited[nx][ny] = true;
			if (placeMaxPipes(nx, ny, pipe + 1)) {
				return true;
			}
		}
		return false;
	}
}