import java.io.*;
import java.util.*;

public class Main {
    private static int N;
    private static HashMap<String, Integer> wordString = new HashMap<>();
    private static HashMap<Integer, String> wordIndex = new HashMap<>();
    private static String[] sorted;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        sorted = new String[N];
        for (int i = 0; i < N; i++) {
            String word = br.readLine();
            sorted[i] = word;

            if (wordString.getOrDefault(word, -1) != -1) continue;
            wordString.put(word, i);
            wordIndex.put(i, word);
        }

        Arrays.sort(sorted);

        // 인접한 두 문자열 비교
        int max = 0;
        Map<String, List<Integer>> prefixMap = new HashMap<>();
        for (int i = 0; i < N - 1; i++) {
            int length = compareString(sorted[i], sorted[i + 1]);

            if (length > max) {
                max = length;

                prefixMap = new HashMap<>();
                String prefix = sorted[i].substring(0, max);

                List<Integer> idxList = new ArrayList<>();
                idxList.add(wordString.get(sorted[i]));
                idxList.add(wordString.get(sorted[i + 1]));
                
                prefixMap.put(prefix, idxList);
            } else if (length == max) {
                String prefix = sorted[i].substring(0, max);
                List<Integer> idxList = prefixMap.get(prefix);

                if (idxList != null) {
                    idxList.add(wordString.get(sorted[i + 1]));
                } else { // 새로운 접두사일때
                    List<Integer> newIdxList = new ArrayList<>();
                    newIdxList.add(wordString.get(sorted[i]));
                    newIdxList.add(wordString.get(sorted[i + 1]));

                    prefixMap.put(prefix, newIdxList);
                }
            }
        }
        
        int minIdx1 = Integer.MAX_VALUE;
        int minIdx2 = Integer.MAX_VALUE;
        for (String prefix : prefixMap.keySet()) {
            List<Integer> idxList = prefixMap.get(prefix);

            Collections.sort(idxList);
            int idx1 = idxList.get(0);
            int idx2 = idxList.get(1);

            if (minIdx1 > idx1) {
                minIdx1 = idx1;
                minIdx2 = idx2;
            }
        }
        sb.append(wordIndex.get(minIdx1)).append("\n").append(wordIndex.get(minIdx2));
        System.out.println(sb.toString());
    }

    private static int compareString(String s1, String s2) {
        int length = 0;

        for (int i = 0; i < Math.min(s1.length(), s2.length()); i++) {
            if (s1.charAt(i) == s2.charAt(i)) {
                length++;
            } else {
                break;
            }
        }
        return length;
    }
}