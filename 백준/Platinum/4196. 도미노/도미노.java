import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCaseCount = Integer.parseInt(br.readLine());
        for (int i = 0; i < testCaseCount; i++) {
            bw.write(scc(br) + "\n");
        }
        bw.flush();
    }

    private static int scc(BufferedReader br) throws Exception {
        int[] nm = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = nm[0];
        int m = nm[1];
        List<Integer>[] v = new List[n + 1];
        int[] count = new int[n + 1];
        int[] check = new int[n+1];
        for (int i = 1; i <= n; i++) {
            v[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            int[] xy = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            v[xy[0]].add(xy[1]);
            count[xy[1]]++;
        }

        PriorityQueue<Vertex> pq = new PriorityQueue<>();

        for (int i = 1; i <= n; i++) {
            pq.add(new Vertex(i, count[i], v[i].size()));
        }

        int result = 0;

        Stack<Integer> idxStack = new Stack<>();

        while(!pq.isEmpty()){

            var top = pq.poll();

            if(check[top.idx]==1) continue;


            result++;

            idxStack.add(top.idx);
            while(!idxStack.isEmpty()){
                int idx = idxStack.pop();
                check[idx]=1;
                
                for(int i=0;i<v[idx].size();i++){
                    int target = v[idx].get(i);
                    if(check[target]==1) continue;
                    idxStack.add(target);
                }
            }
        }

        return result;
    }

    private static class Vertex implements Comparable<Vertex> {
        int idx;
        int indegree;
        int outdegree;

        public Vertex(int idx, int indegree, int outdegree) {
            this.idx = idx;
            this.indegree = indegree;
            this.outdegree = outdegree;
        }

        public int compareTo(Vertex other) {
            if(this.indegree == other.indegree){
                return other.outdegree - this.outdegree;
            }
            return this.indegree - other.indegree;
        }
    }

}
