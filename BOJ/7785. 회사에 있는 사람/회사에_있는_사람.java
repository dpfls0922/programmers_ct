import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class 회사에_있는_사람 {
    private static int N;
    private static HashMap<String, Boolean> map = new HashMap<>();

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            String[] info = br.readLine().split(" ");
            if (info[1].equals("enter")) {
                map.put(info[0], true);
            } else if (info[1].equals("leave")) {
                map.remove(info[0]);
            }
        }

        List<String> names = new ArrayList<>(map.keySet());
        names.sort(Collections.reverseOrder());
        for (String name : names) {
            System.out.println(name);
        }
    }
}