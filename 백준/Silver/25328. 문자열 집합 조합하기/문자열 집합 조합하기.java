import java.util.*;
import java.io.*;

class Main {

    private static Map<String, Integer> subString = new TreeMap<>();

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String x = br.readLine();
        String y = br.readLine();
        String z = br.readLine();
        int k = Integer.parseInt(br.readLine());

        dfs(0, 0, x, 0, k);
        dfs(0, 0, y, 0, k);
        dfs(0, 0, z, 0, k);

        int count = 0;
        for (var entry : subString.entrySet()) {
            if (entry.getValue() > 1) {
                count++;
                bw.write(entry.getKey() + "\n");
            }
        }
        if (count == 0) {
            bw.write("-1\n");
        }
        bw.flush();
    }

    private static void dfs(int count, int bit, String target, int current, int k) {
        if (current >= target.length() && count < k) {
            return;
        }
        if (count == k) {
            var sb = new StringBuilder();
            for (int i = 0; i < target.length(); i++) {
                if ((bit & 1 << i) > 0) {
                    sb.append(target.charAt(i));
                }
            }
            String result = sb.toString();
            subString.put(result, subString.getOrDefault(result, 0) + 1);
        } else {
            for (int i = current; i < target.length(); i++) {
                dfs(count + 1, mark(bit, i), target, i + 1, k);
            }
        }
    }

    private static int mark(int bit, int idx) {
        return bit | 1 << idx;
    }

}
