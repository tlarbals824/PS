import java.util.*;
import java.io.*;

class Main {

    private static int MODULAR = 10007;
    private static Map<Integer, List<Long>> combination = new HashMap<>();

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        for (int i = 52; i >= 0; i--) {
            List<Long> list = new ArrayList<>();
            list.add(1L);
            for (int j = 1; j <= i; j++) {
                list.add((list.get(j - 1) * (i - j + 1) / j));
            }
            combination.put(i, list);
        }

        long[] dp = new long[53];
        for (int i = 0; i <= n; i++) {
            dp[i] = (combination.get(52).get(i) - getCount(i, 0)) % MODULAR;
        }

        System.out.println(dp[n]);
    }
    
    private static long getCount(int i, int j) {
        long result = combination.get(52 - j).get(i - j);
        for (int k = 4; k <= i - j; k += 4) {
            result = result - combination.get(13 - j / 4).get(k / 4) * getCount(i, j + k);
        }
        return result;
    }

}
