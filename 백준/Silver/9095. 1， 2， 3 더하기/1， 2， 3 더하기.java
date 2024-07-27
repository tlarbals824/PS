import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        int[] dp = new int[20];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4; // 1+1+2;
        for (int i = 4; i < 12; i++) {
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
        }
        for (int i = 0; i < t; i++) {
            System.out.println(dp[Integer.parseInt(br.readLine())]);
        }
    }

}
