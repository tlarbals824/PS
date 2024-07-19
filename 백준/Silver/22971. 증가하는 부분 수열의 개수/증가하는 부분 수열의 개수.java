import java.util.*;
import java.io.*;

class Main {

    private static int MODULAR = 998244353;

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] aArr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (aArr[j] < aArr[i]) {
                    dp[i] = (dp[i] + dp[j]) % MODULAR;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            System.out.print(dp[i] + " ");
        }
        System.out.println();

    }
}