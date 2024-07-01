import java.util.*;
import java.io.*;

class Main {

    static Edge[] tree;
    static List<Edge>[] node;
    static int[] check;
    static int root = 1;

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        check = new int[n + 1];

        tree = new Edge[n + 1];
        node = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            node[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            int[] input = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int n1 = input[0], n2 = input[1], cost = input[2];
            node[n1].add(new Edge(n2, cost));
            node[n2].add(new Edge(n1, cost));
        }

        makeTree(root, -1, 0);

        int m = Integer.parseInt(br.readLine());

        for (int i = 0; i < m; i++) {
            int[] input = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            Arrays.fill(check, 0);

            int current = input[0];

            while (current != root) {
                var edge = tree[current];
                check[edge.to] += (check[current] + edge.cost);
                current = edge.to;
            }

            current = input[1];

            while (current != root && current != input[0]) {
                var edge = tree[current];
                if(check[current]!=0 && current == input[1]) break;
                check[edge.to] += (check[current] + edge.cost);
                if(edge.to == input[0]){
                    current = edge.to;
                    break;
                }
                if(check[edge.to] > check[current] + edge.cost){
                    current = edge.to;
                    break;
                }
                current = edge.to;
            }

            bw.write(check[current] + "\n");
        }
        bw.flush();
    }

    static class Edge {
        int to;
        int cost;

        public Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    static void makeTree(int currentNode, int parentNode, int cost) {
        for (Edge next : node[currentNode]) {
            if (next.to == parentNode)
                continue;
            makeTree(next.to, currentNode, next.cost);
        }
        tree[currentNode] = new Edge(parentNode, cost);
    }

}
