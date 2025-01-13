import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.StringTokenizer;

public class 파리퇴치3 {
	public static void main(String args[]) throws Exception
	{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++)
		{     
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
            
			int [][]arr = new int[N][N];
            for (int i = 0; i < N; i++) {
        		st = new StringTokenizer(br.readLine());
            	for (int j = 0; j < N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

			int max_sum = Integer.MIN_VALUE;
			
			// + 모양
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					int sum = arr[i][j];
					
					for (int l = 1; l < M; l++) {
						if (i - l >= 0) sum += arr[i - l][j];
						if (i + l < N) sum += arr[i + l][j];
						if (j - l >= 0) sum += arr[i][j - l];
						if (j + l < N) sum += arr[i][j + l];
					}
					max_sum = Math.max(max_sum, sum);
				}
			}

			
		    // x 모양
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					int sum = arr[i][j];
					
					for (int l = 1; l < M; l++) {
						if (i - l >= 0 && j - l >= 0) sum += arr[i - l][j - l];
						if (i + l < N && j + l < N) sum += arr[i + l][j + l];
						if (i - l >= 0 && j + l < N) sum += arr[i - l][j + l];
						if (i + l < N && j - l >= 0) sum += arr[i + l][j - l];
					}
					max_sum = Math.max(max_sum, sum);
				}
			}
			System.out.println("#" + test_case + " " + max_sum);
		}
	}    
}
