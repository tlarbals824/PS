import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));

        List<Integer> nums = new ArrayList<>();

        int[] nm = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int n = nm[0], m = nm[1];
        for (int i = 0; i < n; i++) {
            nums.add(Integer.parseInt(br.readLine()));
        }

        int[] sum = new int[n + 1];
        sum[1] = nums.get(0);

        for (int i = 2; i <= n; i++) {
            sum[i] = sum[i - 1] + nums.get(i - 1);
        }

        long[] dp = new long[n + 1];
        Arrays.fill(dp, Long.MAX_VALUE);
        dp[0] = 0;
        int[] depth = new int[n + 1];
        depth[0] = -1;
        long result = Long.MAX_VALUE;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (sum[j] - sum[i - 1] + (j - i) > m)
                    continue;

                long distance = dp[i - 1] + pow(m - (sum[j] - sum[i - 1] + (j - i)));
                if (dp[j] > distance) {
                    depth[j] = depth[i - 1] + 1;
                    dp[j] = distance;
                }
                if (j == n) {
                    result = Math.min(result, dp[i - 1]);
                }
            }
        }
        System.out.println(result);
    }

    private static long pow(int n) {
        return n * n;
    }

}
