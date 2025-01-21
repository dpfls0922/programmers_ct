import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.StringTokenizer;

class Solution {
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());

			int[] arr1 = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				arr1[i] = Integer.parseInt(st.nextToken());
			}

			int[] arr2 = new int[M];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				arr2[i] = Integer.parseInt(st.nextToken());
			}

			int min_index = Math.min(N, M);
			int max_index = Math.max(N, M);
			int max_sum = 0;
			for (int i = 0; i < max_index - min_index + 1; i++) {
				int sum = 0;
				for (int j = 0; j < min_index; j++) {
					if (N > M) {
						sum += arr1[i + j] * arr2[j];
					} else {
						sum += arr1[j] * arr2[i + j];
					}
				}
				max_sum = Math.max(max_sum, sum);
			}
			System.out.println("#" + test_case + " " + max_sum);
		}
	}
}
