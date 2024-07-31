import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            int[] num = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int[][][] dp = new int[2][n][n];
            /**
             * 선공일 때, 왼쪽 뽑고 dp[1][i+1][j]
             * 선공일 때, 오른쪽 뽑고 dp[1][i][j-1]
             */
            for (int i = 0; i < n; i++) {
                dp[0][i][i] = num[i]; // 선공이면 다 가져감
            }

            for (int i = 1; i < n; i++) {
                for (int j = 0; j < n - i; j++) {
                    dp[0][j][j + i] = Math.max(num[j] + dp[1][j + 1][j + i], num[j + i] + dp[1][j][j + i - 1]);
                    dp[1][j][j + i] = Math.min(dp[0][j + 1][j + i], dp[0][j][j + i - 1]);
                }
            }

            bw.write(dp[0][0][n - 1] + "\n");
        }
        bw.flush();
    }

}
