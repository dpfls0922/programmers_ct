import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 일대일_가위바이보 {
    public static void main(String[] args) throws NumberFormatException, IOException {
		final int SCISSORS = 1;
		final int ROCK = 2;
		final int PAPER = 3;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());

        char winner = (A == SCISSORS && B == PAPER) || 
                      (A == ROCK && B == SCISSORS) || 
                      (A == PAPER && B == ROCK) ? 'A' : 'B';
		System.out.println(winner);
	}
}
