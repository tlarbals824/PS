import java.util.*;
import java.io.*;

class Main {

    private static Map<Integer, Set<Integer>> edge = new HashMap<>();
    private static Map<Integer, Integer> check = new HashMap<>();
    private static Set<Integer> finished = new HashSet<>();
    private static Stack<Integer> sccStack = new Stack<>();
    private static List<List<Integer>> sccs = new ArrayList<>();
    private static int idx = 0;
    private static int flag = 0;
    private static int[] result = new int[200010];
    private static int n;

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));

        int[] nm = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        n = nm[0];
        int m = nm[1];

        for (int i = 0; i < m; i++) {
            int[] xy = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int x = xy[0];
            int y = xy[1];

            var xVertex = edge.getOrDefault(-x, new HashSet<>());
            var yVertex = edge.getOrDefault(-y, new HashSet<>());
            xVertex.add(y);

            yVertex.add(x);
            edge.put(-x, xVertex);
            edge.put(-y, yVertex);
        }

        validate();

        if (flag == 0) {
            for (int i = sccs.size() - 1; i >= 0; i--) {
                for (int num : sccs.get(i)) {
                    if (result[n + num] == 0) {
                        result[n + num] = -1;
                        result[n - num] = 1;
                    }
                }
            }

            for (int i = n + 1; i <= 2 * n; i++) {
                System.out.print((result[i] == -1 ? 0 : 1) + " ");
            }
            System.out.println();
        }

    }

    private static void validate() {
        for (int i = -n; i <= n; i++) {
            if (i == 0)
                continue;
            if (check.containsKey(i))
                continue;
            findScc(i);
            if (flag == 1) {
                System.out.println(0);
                return;
            }
        }
        System.out.println(1);
    }

    private static Integer findScc(int vertexId) {
        check.put(vertexId, ++idx);
        int parent = check.get(vertexId);
        sccStack.add(vertexId);

        if (edge.containsKey(vertexId)) {
            for (int value : edge.get(vertexId)) {
                if (!check.containsKey(value)) {
                    parent = Math.min(parent, findScc(value));
                } else if (!finished.contains(value)) {
                    parent = Math.min(parent, check.get(value));
                }
            }
        }

        if (parent == check.get(vertexId)) {
            List<Integer> vertexList = new ArrayList<>();
            while (!sccStack.isEmpty()) {
                var value = sccStack.pop();
                if (vertexList.contains(Math.abs(value))) {
                    flag = 1;
                }
                vertexList.add(value);
                finished.add(value);
                if (value == vertexId)
                    break;
            }
            sccs.add(vertexList);
        }

        return parent;
    }

}
