import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));

        int[] tw = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int t = tw[0], w = tw[1];

        int[] input = new int[t + 1];
        for (int i = 1; i <= t; i++) {
            input[i] = Integer.parseInt(br.readLine());
        }

        int[][][] dp = new int[t + 1][w + 1][2];

        dp[1][0][0] = (input[1] == 1 ? 1 : 0);
        dp[1][1][1] = (input[1] == 2 ? 1 : 0);

        for (int i = 2; i <= t; i++) {
            for (int j = 0; j <= w; j++) {
                dp[i][j][0] = Math.max(dp[i - 1][j][0], (j == 0 ? 0 : dp[i - 1][j - 1][1])) + (input[i] == 1 ? 1 : 0);
                dp[i][j][1] = Math.max(dp[i - 1][j][1], (j == 0 ? 0 : dp[i - 1][j - 1][0])) + (input[i] == 2 ? 1 : 0);
            }
        }

        int result = 0;
        for (int i = 0; i <= w; i++) {
            result = Math.max(dp[t][i][0], result);
            result = Math.max(dp[t][i][1], result);
        }
        System.out.println(result);
    }

}
