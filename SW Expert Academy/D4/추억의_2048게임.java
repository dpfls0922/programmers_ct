import java.io.*;
import java.util.*;

public class 추억의_2048게임 {
	private static int N;
	private static String S;
	private static int[][] nums;
	private static boolean[][] merged;
	private static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#" + tc).append("\n");

			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			S = st.nextToken();

			nums = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					nums[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			moveArr();
			printArr();
		}
		System.out.println(sb.toString());
	}

	private static void moveArr() {
		merged = new boolean[N][N];
		switch(S) {
			case "up": 
				moveUp();
				break;
			case "down": 
				moveDown();
				break;
			case "left": 
				moveLeft();
				break;
			default:
				moveRight();
				break;
		}
	}

	private static void moveUp() {
		for (int j = 0; j < N; j++) {
			for (int i = 1; i < N; i++) {
				if (nums[i][j] == 0) continue;

				int k = i;
				while (k > 0 && nums[k - 1][j] == 0) k--;

				if (k > 0 && nums[k - 1][j] == nums[i][j] && !merged[k - 1][j]) {
					nums[k - 1][j] *= 2;
					nums[i][j] = 0;
					merged[k - 1][j] = true;
				} else if (k != i) {
					nums[k][j] = nums[i][j];
					nums[i][j] = 0;
				}
			}
		}
	}

	private static void moveDown() {
		for (int j = 0; j < N; j++) {
			for (int i = N - 2; i >= 0; i--) {
				if (nums[i][j] == 0) continue;

				int k = i;
				while (k < N - 1 && nums[k + 1][j] == 0) k++;

				if (k < N - 1 && nums[k + 1][j] == nums[i][j] && !merged[k + 1][j]) {
					nums[k + 1][j] *= 2;
					nums[i][j] = 0;
					merged[k + 1][j] = true;
				} else if (k != i) {
					nums[k][j] = nums[i][j];
					nums[i][j] = 0;
				}
			}
		}
	}


	private static void moveLeft() {
		for (int i = 0; i < N; i++) {
			for (int j = 1; j < N; j++) {
				if (nums[i][j] == 0) continue;

				int k = j;
				while (k > 0 && nums[i][k - 1] == 0) k--;

				if (k > 0 && nums[i][k - 1] == nums[i][j] && !merged[i][k - 1]) {
					nums[i][k - 1] *= 2;
					nums[i][j] = 0;
					merged[i][k - 1] = true;
				} else if (k != j) {
					nums[i][k] = nums[i][j];
					nums[i][j] = 0;
				}
			}
		}
	}

	private static void moveRight() {
		for (int i = 0; i < N; i++) {
			for (int j = N - 2; j >= 0; j--) {
				if (nums[i][j] == 0) continue;

				int k = j;
				while (k < N - 1 && nums[i][k + 1] == 0) k++;

				if (k < N - 1 && nums[i][k + 1] == nums[i][j] && !merged[i][k + 1]) {
					nums[i][k + 1] *= 2;
					nums[i][j] = 0;
					merged[i][k + 1] = true;
				} else if (k != j) {
					nums[i][k] = nums[i][j];
					nums[i][j] = 0;
				}
			}
		}
	}
	
	private static void printArr() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sb.append(nums[i][j]).append(" ");
			}
			sb.append("\n");
		}
	}
}