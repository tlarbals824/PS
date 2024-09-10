import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            int[][] map = new int[2][n];

            for (int i = 0; i < 2; i++) {
                map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }

            int[][][] dp = new int[2][2][n + 1];

            /**
             * dp[0][i][j] = max(dp[0][i][j-1], dp[1][i][j-1])
             * dp[1][i][j] = max(dp[0][i][j-1], dp[0][abs(i-1)][j])+map[i][j]
             */

            int result = 0;
            for (int i = 1; i <= n; i++) {
                dp[0][0][i] = Math.max(dp[0][0][i - 1], dp[1][0][i - 1]);
                dp[0][1][i] = Math.max(dp[0][1][i - 1], dp[1][1][i - 1]);

                dp[1][0][i] = Math.max(dp[0][0][i - 1], dp[0][1][i]) + map[0][i - 1];
                dp[1][1][i] = Math.max(dp[0][0][i], dp[0][1][i - 1]) + map[1][i - 1];

                result = Math.max(dp[1][0][i], result);
                result = Math.max(dp[1][1][i], result);
                result = Math.max(dp[0][0][i], result);
                result = Math.max(dp[0][1][i], result);
            }
            System.out.println(result);
        }
    }

}
