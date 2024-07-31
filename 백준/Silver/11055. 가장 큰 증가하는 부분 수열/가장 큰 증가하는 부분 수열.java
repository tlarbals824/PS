import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[] dp = new int[n];
        int result = 0;
        for (int i = 0; i < n; i++) {
            dp[i] = input[i];
            result = Math.max(result, dp[i]);
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (input[i] < input[j]) {
                    dp[j] = Math.max(dp[i] + input[j], dp[j]);
                    result = Math.max(result, dp[j]);
                }
            }
        }
        System.out.println(result);
    }

}
