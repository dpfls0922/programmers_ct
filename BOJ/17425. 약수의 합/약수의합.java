import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 약수의합 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		final int MAX = 1000000;
		long[] f = new long[MAX + 1];
		
		for (int i = 1; i <= MAX; i++) {
			for (int j = i; j <= MAX; j += i) {
				f[j]+= i;
			}
		}
		
		long[] g = new long[MAX + 1];
		for (int i = 1; i <= MAX; i++) {
			g[i] = g[i - 1] + f[i];
		}
		
		int tc = Integer.parseInt(br.readLine());
		for (int i = 1; i <= tc; i++) {
			int n = Integer.parseInt(br.readLine());
			sb.append(g[n]).append("\n");
		}
	    System.out.println(sb.toString());
	}
}