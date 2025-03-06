import java.io.*;
import java.util.*;

public class 캐슬_디펜스 {
    static int N, M, D;
    static int[][] map;
    static int max = Integer.MIN_VALUE;
    static int[] archerPositions = new int[3];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 궁수 배치 경우의 수
        placeArcher(0, 0);
        System.out.println(max);
    }

    private static void placeArcher(int count, int start) {
        if (count == 3) {
            max = Math.max(game(), max);
            return;
        }

        for (int pos = start; pos < M; pos++) {
            archerPositions[count] = pos;
            placeArcher(count + 1, pos + 1);
        }
    }

    private static int game() {
        int totalAttackCount = 0;
        int[][] status = new int[N][M];  // 공격한 적 표시

        for (int aPosX = N; aPosX > 0; aPosX--) { // 궁수 위로 이동
        	for (int aPosY : archerPositions) {
            	for (int d = 1; d <= D; d++) {
            		int count = attack(status, aPosX, aPosY, d);
            		if (count < 0) continue;
            		totalAttackCount += count;
            		break;
            	}	
        	}
        }
        return totalAttackCount;
    }
    
    private static int attack(int[][] status, int aPosX, int aPosY, int d) {
    	for (int y = aPosY - (d - 1); y <= aPosY + (d - 1); y++) {
    		// 거리가 d가 되도록 x를 계산
    		// d = aPosX - x + Math.abs(y - aPosY);
    		int x = aPosX - d + Math.abs(y - aPosY);
    		
    		if (!isValid(x, y, aPosX)) continue;
    		if (map[x][y] == 0) continue;

            // 해당 위치에 적이 있고, 아직 처리하지 않았다면 공격
            if (status[x][y] == 0){
               status[x][y] = aPosX;
               return 1;
            } else if (status[x][y] == aPosX) { // 같은 적이 여러 궁수에게 공격당할 경우
            	return 0;
            }
    	}
		return -1;
    }

    private static boolean isValid(int x, int y, int aPosX) {
    	return x >= 0 && x < aPosX && y >= 0 && y < M; // aPosX까지 확인해야 함
    }
}