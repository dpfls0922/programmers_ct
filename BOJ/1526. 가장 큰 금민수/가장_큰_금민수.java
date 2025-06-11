import java.io.*;

public class 가장_큰_금민수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        while (N > 0) {
            if (isValid(N)) {
                System.out.println(N);
                return;
            }
            N--;
        }
    }

    private static boolean isValid(int num) {
        while (num > 0) {
            int r = num % 10;
            if (r != 4 && r != 7) return false;
            num /= 10;
        }
        return true;
    }
}