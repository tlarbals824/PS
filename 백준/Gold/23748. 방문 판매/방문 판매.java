import java.util.*;
import java.io.*;

class Main {
    private static Pair[] customers;

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));

        int[] nxy = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = nxy[0], x = nxy[1], y = nxy[2];
        customers = new Pair[n + 1];
        for (int i = 1; i <= n; i++) {
            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            customers[i] = new Pair(input[0], input[1]);
        }

        int[][] dp = new int[x + 1][y + 1];
        int[][] count = new int[x + 1][y + 1];
        int[][] last = new int[x + 1][y + 1];
        for (int i = 0; i <= x; i++) {
            for (int j = 0; j <= y; j++) {
                count[i][j] = Integer.MAX_VALUE;
            }
        }
        dp[0][0] = 1;
        count[0][0] = 0;

        for (int i = 1; i <= n; i++) {
            var target = customers[i];
            for (int a = x; a >= 0; a--) {
                for (int b = y; b >= 0; b--) {
                    if (dp[a][b] == 1) {
                        int targetX = Math.min(x, a + target.x);
                        int targetY = Math.min(y, b + target.y);
                        dp[targetX][targetY] = 1;
                        if (count[targetX][targetY] > count[a][b] + 1) {
                            count[targetX][targetY] = count[a][b] + 1;
                            last[targetX][targetY] = i;
                        }
                    }
                }
            }
        }
        if (count[x][y] == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(count[x][y]);
            System.out.println(last[x][y]);
        }

    }

    private static class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}
