import java.util.*;
import java.io.*;

class Main {

    private static List<Integer>[] edge;
    private static int[] check;
    private static int[] candies;

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));

        int[] nmk = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int n = nmk[0], m = nmk[1], k = nmk[2];
        int[] tmp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        candies = new int[n + 1];
        for (int i = 0; i < n; i++) {
            candies[i + 1] = tmp[i];
        }

        edge = new List[n + 1];
        check = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            edge[i] = new ArrayList<>();
            edge[i].add(i);
        }
        for (int i = 0; i < m; i++) {
            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            edge[input[0]].add(input[1]);
            edge[input[1]].add(input[0]);
        }

        List<Node> nodes = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            if (check[i] == 1)
                continue;
            nodes.add(getNode(i));
        }

        int[][] dp = new int[nodes.size() + 1][k];
        int result = 0;
        for (int i = 1; i <= nodes.size(); i++) {
            var node = nodes.get(i - 1);
            for (int j = 0; j < k; j++) {
                if (j < node.childSize) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - node.childSize] + node.candyCount);
                }
                result = Math.max(dp[i][j], result);
            }
        }

        System.out.println(result);
    }

    private static Node getNode(int current) {
        check[current] = 1;
        int candyCount = candies[current];
        int childSize = 1;
        for (int num : edge[current]) {
            if (check[num] == 1)
                continue;
            var result = getNode(num);
            candyCount += result.candyCount;
            childSize += result.childSize;
        }
        return new Node(childSize, candyCount);
    }

    private static class Node {
        int childSize;
        int candyCount;

        public Node(int childSize, int candyCount) {
            this.childSize = childSize;
            this.candyCount = candyCount;
        }
    }
}