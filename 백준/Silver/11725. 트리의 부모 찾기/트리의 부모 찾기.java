import java.util.*;
import java.io.*;

class Main {

    private static Map<Integer, List<Integer>> tree = new HashMap<>();
    private static int[] parent = new int[100010];

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n - 1; i++) {
            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            var left = tree.getOrDefault(input[0], new ArrayList<>());
            var right = tree.getOrDefault(input[1], new ArrayList<>());
            left.add(input[1]);
            right.add(input[0]);
            tree.put(input[0], left);
            tree.put(input[1], right);
        }

        makeTree(1, 1);
        for (int i = 2; i <= n; i++) {
            System.out.println(parent[i]);
        }
    }

    private static void makeTree(int current, int parentIndex) {
        parent[current] = parentIndex;
        for (var num : tree.get(current)) {
            if (num == parentIndex)
                continue;
            makeTree(num, current);
        }
    }
}
