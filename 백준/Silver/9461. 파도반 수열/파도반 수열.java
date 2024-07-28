import java.util.*;
import java.io.*;

class Main {

    private static long[] dp = new long[110];

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());

        dfs(101);

        for (int i = 0; i < t; i++) {
            bw.write(dp[Integer.parseInt(br.readLine())] + "\n");
        }
        bw.flush();
    }

    private static long dfs(int current) {
        if (dp[current] != 0)
            return dp[current];
        if (current <= 3) {
            return dp[current] = 1;
        } else if (current <= 5) {
            return dp[current] = 2;
        } else {
            return dp[current] = dfs(current - 1) + dfs(current - 5);
        }
    }

}
