import java.util.*;
import java.io.*;

class Main {

    private static List<Integer>[] edge;

    public static void main(String[] arsg) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));

        int computerCount = Integer.parseInt(br.readLine());
        int edgeCount = Integer.parseInt(br.readLine());
        edge = new List[computerCount + 1];
        for (int i = 1; i <= computerCount; i++) {
            edge[i] = new ArrayList<>();
        }

        for (int i = 0; i < edgeCount; i++) {
            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            edge[input[0]].add(input[1]);
            edge[input[1]].add(input[0]);
        }

        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        int count = 0;
        int[] check = new int[computerCount + 1];
        while (!q.isEmpty()) {
            int front = q.poll();
            if (check[front] == 1)
                continue;
            if (front > 1)
                count++;

            check[front] = 1;

            for (int v : edge[front]) {
                q.add(v);
            }
        }
        System.out.println(count);
    }

}
