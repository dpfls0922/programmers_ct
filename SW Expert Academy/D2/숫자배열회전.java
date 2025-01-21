import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.StringTokenizer;

public class 숫자배열회전 {
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(br.readLine());
			int[][] arr = new int[N][N];

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			int[][] rotated90 = rotate90Array(arr);
			int[][] rotated180 = rotate90Array(rotated90);
			int[][] rotated270 = rotate90Array(rotated180);

			System.out.println("#" + test_case);
			printResult(N, rotated90, rotated180, rotated270);
		}
	}

	private static int[][] rotate90Array(int[][] arr) {
		int N = arr.length;
		int[][] rotated = new int[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = N - 1; j >= 0; j--) {
				rotated[i][N - 1 - j] = arr[j][i];
			}
		}
		return rotated;
	}

	private static void printResult(int N, int[][] rotated90, int[][] rotated180, int[][] rotated270) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(rotated90[i][j]);
			}
			System.out.print(" ");
			for (int j = 0; j < N; j++) {
				System.out.print(rotated180[i][j]);
			}
			System.out.print(" ");
			for (int j = 0; j < N; j++) {
				System.out.print(rotated270[i][j]);
			}
			System.out.println();
		}
	}
}
