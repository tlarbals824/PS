import java.util.*;
import java.io.*;

class Main {

    private static long MODULAR = 1000000009;

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));

        long[][] dp = new long[100010][4];
        dp[0][0] = 1;
        dp[1][1] = 1;
        dp[2][0] = 1;
        dp[2][2] = 1;

        for (int i = 3; i < 100001; i++) {
            dp[i][0] = (dp[i - 1][1] + dp[i - 2][2] + dp[i - 3][3]) % MODULAR;
            dp[i][1] = dp[i - 1][0];
            dp[i][2] = dp[i - 2][0];
            dp[i][3] = dp[i - 3][0];
        }

        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            long result = 0;
            for (int i = 0; i < 4; i++) {
                result = (result + dp[n][i]) % MODULAR;
            }
            System.out.println(result);
        }
    }

}
