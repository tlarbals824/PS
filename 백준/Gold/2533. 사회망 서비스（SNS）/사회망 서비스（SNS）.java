import java.util.*;
import java.util.concurrent.*;
import java.io.*;

class Main {

    static List<Integer>[] edge;
    static int[] check;

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        edge = new List[n+1];
        for(int i=1;i<=n;i++){
            edge[i]=new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            int[] uv = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            var uToSet = edge[uv[0]];
            var vToSet = edge[uv[1]];

            uToSet.add(uv[1]);
            vToSet.add(uv[0]);
        }
        check = new int[n + 1];
        makeTree(1, -1);
        System.out.println(check[0]);
    }

    static void makeTree(int currentNode, int parentNode) {
        var currentSet = edge[currentNode];
        if(currentSet.size()==1){
            check[currentNode]=0;
        }
        for(int i=0;i<currentSet.size();i++){
            int child = currentSet.get(i);
            if (child == parentNode) {
                continue;
            }
            makeTree(child, currentNode);
        }

        boolean isAllAdaptor = true;
        for (int child : currentSet) {
            if (child == parentNode) {
                continue;
            }
            if (check[child] == 0) {
                isAllAdaptor = false;
                break;
            }
        }
        if (!isAllAdaptor) {
            check[currentNode] = 1;
            check[0]++;
        }
    }

}