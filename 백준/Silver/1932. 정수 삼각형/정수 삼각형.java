import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[][] map = new int[n][n];
        for (int i = 0; i < n; i++) {
            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j <= i; j++) {
                map[i][j] = input[j];
            }
        }

        long[][] dp = new long[n][n];
        dp[0][0] = map[0][0];
        long result = dp[0][0];
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                long max = 0;
                if (j == 0) {
                    max = dp[i - 1][j];
                } else if (j == i) {
                    max = dp[i - 1][j - 1];
                } else {
                    max = Math.max(dp[i - 1][j - 1], dp[i - 1][j]);
                }
                dp[i][j] = max + map[i][j];
                result = Math.max(result, dp[i][j]);
            }
        }
        System.out.println(result);

    }

}
