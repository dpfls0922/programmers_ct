
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N과_M {
    private static int N, M;
	private static boolean[] isUsed;
	private static int[] possibleNums;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		isUsed = new boolean[N + 1];
		possibleNums = new int[M];
		backtracking(0);
	}

	private static void backtracking(int count) { // 현재 count까지 수를 택함
		if (count == M) {
			for (int num : possibleNums) {
				System.out.print(num + " ");
			}
			System.out.println();
			return;
		}
		
		for (int i = 1; i <= N; i++) {
			if (!isUsed[i]) {
				possibleNums[count] = i;
				
				isUsed[i] = true;
				backtracking(count + 1);
				isUsed[i] = false;
			}
		}
	}
}
