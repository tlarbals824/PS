import java.util.*;
import java.io.*;

class Main {

    private static List<Pair> clause = new ArrayList<>();
    private static int[] variable;

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));

        int[] nm = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int n = nm[0];
        int m = nm[1];

        variable = new int[n + 1];

        for (int i = 0; i < m; i++) {
            int[] xy = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            clause.add(new Pair(xy[0], xy[1]));
        }
        boolean result = dfs(1, n);
        System.out.println(result ? 1 : 0);
        if (result) {
            for (int i = 1; i <= n; i++) {
                System.out.print((variable[i] == -1 ? 0 : 1) + " ");
            }
            System.out.println();
        }
    }

    private static Boolean dfs(int idx, int n) {
        if (idx == n + 1) {
            return check();
        } else {
            variable[idx] = 1;
            var trueCheck = dfs(idx + 1, n);
            if (trueCheck)
                return true;
            variable[idx] = -1;
            var falseCheck = dfs(idx + 1, n);
            if (falseCheck)
                return true;
            return false;
        }
    }

    private static Boolean check() {
        for (int i = 0; i < clause.size(); i++) {
            var pair = clause.get(i);
            int x = variable[(int) Math.abs(pair.x)] * (pair.x > 0 ? 1 : -1);
            int y = variable[(int) Math.abs(pair.y)] * (pair.y > 0 ? 1 : -1);

            if (x == -1 && y == -1)
                return false;
        }
        return true;
    }

    private static class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}
