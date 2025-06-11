import java.io.*;

public class 더하기_사이클 {
    public static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        System.out.println(solve(0, N));
    }

    private static int solve(int count, int current) {
        if (count != 0 && N == current) return count;

        int sum = (current % 10) + (current / 10);
        int next = (current % 10) * 10 + (sum % 10);

        return solve(count + 1, next);
    }
}

/*
재귀 없이 반복문을 쓰면 return이 없어도 되지만,
재귀는 "서로한테 메모를 전해주는 구조"라서,
결과를 계속 위로 전해줘야만(return을 통해) 마지막에 원하는 값을 얻을 수 있다.
*/