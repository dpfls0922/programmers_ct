import java.io.*;
import java.util.*;

public class 통나무_옮기기기 {
	    static int N;
	    static int endX = 0, endY = 0, endDir = 0, startX = 0, startY = 0, startDir = 0;
	    static char[][] map;
	    static boolean[][][] visited; // [방향][행][열]
	    							  // 회전이 가능하면 방향이 바뀐 채로 같은 (x, y) 위치에 다시 방문할 수도 있음 -> visited를 방향별로 관리
	    static int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	    static int[][] rotateCheck = { {-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 0}, {0, 1}, {1, -1}, {1, 0}, {1, 1} };
	    static ArrayDeque<Log> queue = new ArrayDeque<>();

	    static class Log {
	        int x, y, dir, cnt;

	        Log(int x, int y, int dir, int cnt) {
	            this.x = x;
	            this.y = y;
	            this.dir = dir;
	            this.cnt = cnt;
	        }
	    }

	    public static void main(String[] args) throws IOException{
	        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	        N = Integer.parseInt(br.readLine()); // 4 ≤ N ≤ 50
	        map = new char[N][N];
	        visited = new boolean[2][N][N];  // 0: 가로, 1: 세로

	        List<int[]> bPositions = new ArrayList<>();
	        List<int[]> ePositions = new ArrayList<>();
	        
	        for (int i = 0; i < N; i++) {
	            String line = br.readLine();
	            for (int j = 0; j < N; j++) {
	                map[i][j] = line.charAt(j);
	                if (map[i][j] == 'B') bPositions.add(new int[]{i, j});
	                if (map[i][j] == 'E') ePositions.add(new int[]{i, j});
	            }
	        }

	        // BBB와 EEE의 중심 좌표 구하기
	        startX = bPositions.get(1)[0];
	        startY = bPositions.get(1)[1];
	        startDir = (bPositions.get(0)[0] == bPositions.get(1)[0]) ? 0 : 1;

	        // 목표 위치 및 방향 설정
	        endX = ePositions.get(1)[0];
	        endY = ePositions.get(1)[1];
	        endDir = (ePositions.get(0)[0] == ePositions.get(1)[0]) ? 0 : 1;

	        queue.add(new Log(startX, startY, startDir, 0));
	        visited[startDir][startX][startY] = true;

	        // 최소 동작 횟수 출력
	        solve();
	    }

	    private static void solve() {
	        while (!queue.isEmpty()) {
	            Log current = queue.poll();

	            if (current.x == endX && current.y == endY && current.dir == endDir) {
	                System.out.println(current.cnt);
	                return;
	            }

	            // 상하좌우 이동
	            for (int[] direction : directions) {
	                int nx = current.x + direction[0];
	                int ny = current.y + direction[1];

	                if (canMove(nx, ny, current.dir) && !visited[current.dir][nx][ny]) {
	                    visited[current.dir][nx][ny] = true;
	                    queue.add(new Log(nx, ny, current.dir, current.cnt + 1));
	                }
	            }

	            // 회전
	            int nextDir = 1 - current.dir; // 방향 전환
	            if (canRotate(current.x, current.y) && !visited[nextDir][current.x][current.y]) {
	                visited[nextDir][current.x][current.y] = true;
	                queue.add(new Log(current.x, current.y, nextDir, current.cnt + 1));
	            }
	        }

	        // 이동 불가능한 경우
	        System.out.println(0);
	    }

	    private static boolean canMove(int x, int y, int dir) {
	        int[][] delta = (dir == 0) ? new int[][]{{0, -1}, {0, 0}, {0, 1}} : new int[][]{{-1, 0}, {0, 0}, {1, 0}};
	        for (int[] d : delta) {
	            int nx = x + d[0];
	            int ny = y + d[1];
	            if (!isInRange(nx, ny) || map[nx][ny] == '1') return false;
	        }
	        return true;
	    }

	    private static boolean canRotate(int x, int y) {
	        for (int[] check : rotateCheck) {
	            int nx = x + check[0];
	            int ny = y + check[1];
	            if (!isInRange(nx, ny) || map[nx][ny] == '1') return false;
	        }
	        return true;
	    }

	    static boolean isInRange(int x, int y) {
	        return x >= 0 && x < N && y >= 0 && y < N;
	    }
	}