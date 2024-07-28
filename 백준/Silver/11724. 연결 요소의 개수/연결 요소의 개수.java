import java.util.*;
import java.io.*;

class Main {

    private static List<Integer>[] edge;
    private static int[] check;

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));

        int[] nm = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = nm[0], m = nm[1];
        edge = new List[n + 1];
        check = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            edge[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            edge[input[0]].add(input[1]);
            edge[input[1]].add(input[0]);
        }

        int count = 0;
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if (check[i] == 1)
                continue;
            count++;
            q.add(i);
            while (!q.isEmpty()) {
                int top = q.poll();
                if (check[top] == 1)
                    continue;
                check[top] = 1;

                for (int j = 0; j < edge[top].size(); j++) {
                    int target = edge[top].get(j);
                    if (check[target] == 1)
                        continue;
                    q.add(target);
                }

            }
        }
        System.out.println(count);

    }

}
