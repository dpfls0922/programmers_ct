import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

class Main {
	private static int N;
	private static Deque<Integer> current = new ArrayDeque<>(); // 큐
	private static Deque<Integer> temp = new ArrayDeque<>();    // 스택
	
	public static void main(String args[]) throws Exception
	{
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 
		 N = Integer.parseInt(br.readLine());
		 StringTokenizer st = new StringTokenizer(br.readLine());
		 for (int i = 0; i < N; i++) {
			 int num = Integer.parseInt(st.nextToken());
			 current.offer(num);
		 }

		 // 번호표는 1, 2, ..., N까지
         // 큐(현재 줄 서 있는 곳)와 스택(임시 공간)을 사용해서 오름차순으로 번호표를 뽑기기
		 int min = 1;
		 while (!current.isEmpty()) {
			 if (current.peek() == min) {
				 current.poll();
				 min++;
			 } else if (!temp.isEmpty() && temp.peek() == min) {
				 temp.pop();
				 min++;
			 } else if (current.peek() > min) {
				 temp.push(current.poll());
			 }
		 }

		 boolean isValid = true;
		 int prev = 1;
		 while (!temp.isEmpty()) {
			 int num = temp.pop();
			 if (prev > num) {
				 isValid = false;
				 break;
			 }
			 prev = num;
		 }
         // 스택(임시 공간)에서 오름차순으로 pop되면서, 스택과 큐가 비어있다면 Nice
		 if (isValid && current.isEmpty() && temp.isEmpty()) {
			 System.out.println("Nice");
		 } else {
			 System.out.println("Sad");
		 }
	}
}