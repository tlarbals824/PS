import java.util.*;
import java.io.*;

class Main {

    private static List<Pos> list = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            list.add(new Pos(input[0], input[1]));
        }

        /**
         * dp 네...
         */

        int[][] dp = new int[2][n];
        dp[0][0] = list.get(0).a;
        dp[1][0] = list.get(0).b;

        for (int i = 1; i < n; i++) {
            dp[0][i] = Math.max(dp[0][i - 1] + Math.abs(list.get(i - 1).b - list.get(i).b),
                    dp[1][i - 1] + Math.abs(list.get(i - 1).a - list.get(i).b)) + list.get(i).a;
            dp[1][i] = Math.max(dp[0][i - 1] + Math.abs(list.get(i - 1).b - list.get(i).a),
                    dp[1][i - 1] + Math.abs(list.get(i - 1).a - list.get(i).a)) + list.get(i).b;
        }

        System.out.println(Math.max(dp[0][n - 1], dp[1][n - 1]));

    }

    private static class Pos {
        int a, b;

        public Pos(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }

}
