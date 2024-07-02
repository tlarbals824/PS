import java.util.*;
import java.io.*;

class Main {


    static int[] tree;
    static List<Integer>[] node;
    static int[] check;
    static int root = 1;

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        check = new int[n + 1];

        tree = new int[n + 1];
        node = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            node[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            int[] input = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int n1 = input[0], n2 = input[1];
            node[n1].add(n2);
            node[n2].add(n1);
        }

        makeTree(root, -1);

        int m = Integer.parseInt(br.readLine());

        for (int i = 0; i < m; i++) {
            int[] input = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            Arrays.fill(check, 0);

            int current = input[0];

            while (current != root) {
                var edge = tree[current];
                check[edge]=1;
                current = edge;
                
            }

            current = input[1];

            while (current != root && current != input[0]) {
                var edge = tree[current];
                if(check[current]!=0 && current == input[1]) break;
                if(check[edge] != 0){
                    current = edge;
                    break;
                }
                check[edge]++;
                current = edge;
                if(edge == input[0]){
                    break;
                }
            }

            bw.write(current + "\n");
        }
        bw.flush();
    }

    static void makeTree(int currentNode, int parentNode) {
        for (int next : node[currentNode]) {
            if (next == parentNode)
                continue;
            makeTree(next, currentNode);
        }
        tree[currentNode] = parentNode;
    }
    
}
