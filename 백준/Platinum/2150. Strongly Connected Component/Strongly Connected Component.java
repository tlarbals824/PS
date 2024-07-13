import java.util.*;
import java.io.*;

class Main {

    private static List<Integer>[] graph;
    private static Stack<Integer> vertexStack = new Stack<>();
    private static int[] check = new int[10010];
    private static int[] finished = new int[10010];
    private static int id = 1;
    private static List<List<Integer>> sccs = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] ve = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int v = ve[0];
        int e = ve[1];

        graph = new List[v + 1];
        for (int i = 1; i <= v; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < e; i++) {
            int[] ab = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            graph[ab[0]].add(ab[1]);
        }

        for (int i = 1; i <= v; i++) {
            if (check[i] == 0)
                dfs(i);
        }

        Collections.sort(sccs, (l1, l2) -> l1.get(0).compareTo(l2.get(0)));

        bw.write(sccs.size()+"\n");
        for(int i=0;i<sccs.size();i++){
            for(int j=0;j<sccs.get(i).size();j++){
                bw.write(sccs.get(i).get(j)+" ");
            }
            bw.write("\n");
        }
        bw.flush();

    }

    private static int dfs(int vertex) {
        int parent = check[vertex] = id++;
        vertexStack.add(vertex);

        for (int i = 0; i < graph[vertex].size(); i++) {
            int target = graph[vertex].get(i);
            if (check[target] == 0)
                parent = Math.min(parent, dfs(target));
            else if (finished[target] == 0)
                parent = Math.min(parent, check[target]);
        }

        if (parent == check[vertex]) {
            List<Integer> scc = new ArrayList<>();
            while (true) {
                int top = vertexStack.pop();
                scc.add(top);
                finished[top] = 1;
                if (top == vertex)
                    break;
            }
            Collections.sort(scc);
            scc.add(-1);
            sccs.add(scc);
        }

        return parent;
    }

}
