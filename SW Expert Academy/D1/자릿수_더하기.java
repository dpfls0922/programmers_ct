import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 자릿수_더하기 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int sum = 0;
		while (N > 0) {
			sum += N % 10;
			N /= 10;
		}
		System.out.println(sum);
	}
}
