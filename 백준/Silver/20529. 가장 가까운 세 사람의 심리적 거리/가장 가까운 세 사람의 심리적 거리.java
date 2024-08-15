import java.util.*;
import java.io.*;

class Main {

    private static Map<String, Integer> mbtiMap = new HashMap<>();

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            String[] input = br.readLine().split(" ");

            mbtiMap.clear();

            for (int i = 0; i < n; i++) {
                mbtiMap.put(input[i], mbtiMap.getOrDefault(input[i], 0) + 1);
            }

            List<String> mbtis = new ArrayList<>(mbtiMap.keySet());

            Collections.sort(mbtis);

            bw.write(bfs(0, mbtis, new ArrayList<>()) + "\n");
        }
        bw.flush();
    }

    private static int bfs(int count, List<String> mbtis, List<String> selected) {
        if (count == 3) {
            int result = 0;

            for (int i = 0; i < 3; i++) {
                result += cal(selected.get(i), selected.get((i + 1) % 3));
            }
            return result;
        } else {
            int result = Integer.MAX_VALUE;
            for (int i = 0; i < mbtis.size(); i++) {
                if (mbtiMap.get(mbtis.get(i)) <= 0)
                    continue;

                if (selected.size() == count) {
                    selected.add(mbtis.get(i));
                } else {
                    selected.set(count, mbtis.get(i));
                }

                mbtiMap.put(mbtis.get(i), mbtiMap.get(mbtis.get(i)) - 1);
                result = Math.min(result, bfs(count + 1, mbtis, selected));
                mbtiMap.put(mbtis.get(i), mbtiMap.get(mbtis.get(i)) + 1);
            }
            return result;
        }
    }

    private static int cal(String input1, String input2) {
        char[] charArr1 = input1.toCharArray();
        char[] charArr2 = input2.toCharArray();

        int count = 0;
        for (int i = 0; i < 4; i++) {
            if (charArr1[i] != charArr2[i]) {
                count++;
            }
        }
        return count;
    }

}
