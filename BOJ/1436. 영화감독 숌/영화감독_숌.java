import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 영화감독_숌 {
	private static String END_NUMBER = "666";
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		
		int count = 0;
		for (int i = 1; i <= 10000000; i++) {
		    if (String.valueOf(i).contains(END_NUMBER)) {
		        count += 1;
		    }
		    if (count == N) {
		        System.out.println(i);
		        break;
		    }
		}
	}
}
