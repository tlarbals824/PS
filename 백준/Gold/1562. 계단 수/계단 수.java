import java.util.*;
import java.io.*;

class Main {

    static int DIV = 1_000_000_000;

    public static void main(String[] main) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        long[][][] dp = new long[n + 1][10][1 << 10];

        for (int i = 1; i <= 9; i++) {
            dp[1][i][1 << i] = 1;
        }
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j <= 9; j++) {
                for (int k = 0; k < (1 << 10); k++) {
                    int marked = k | (1 << j);
                    dp[i][j][marked] += ((j != 0 ? dp[i - 1][j - 1][k] : 0) + (j != 9 ? dp[i - 1][j + 1][k] : 0)) % DIV;
                }
            }
        }
        long result = 0;
        for (int i = 0; i <= 9; i++) {
            result = ((result + dp[n][i][(1 << 10) - 1]) % DIV);
        }

        System.out.println(result);
    }

}
