import java.util.*;
import java.io.*;

class Main {


    static int[] tree;
    static int[][] table;
    static List<Integer>[] node;
    static int root = 1;
    static int[] d;

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        tree = new int[n + 1];
        node = new List[n + 1];
        table = new int[19][n+1];
        d = new int[n+1];
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

        makeTree(root, -1, 0);
        makeTable();

        int m = Integer.parseInt(br.readLine());

        for (int i = 0; i < m; i++) {
            int[] input = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int n1 = input[0];
            int n2 = input[1];

            int d1 = d[n1]; // 앝은곳
            int d2 = d[n2]; // 깊은곳

            if(d1 > d2){
                int tmp = n1;
                n1 = n2;
                n2 = tmp;
            }

            n2 = findAscendant(n2, d[n2]-d[n1]);

            bw.write(findBothAscendant(n1, n2) + "\n");
        }
        bw.flush();
    }

    static void makeTree(int currentNode, int parentNode, int depth) {
        for (int next : node[currentNode]) {
            if (next == parentNode)
                continue;
            makeTree(next, currentNode, depth+1);
        }
        tree[currentNode] = parentNode;
        d[currentNode] = depth;
    }

    static void makeTable(){
        table[0]=tree;
        for(int i=1;i<=16;i++){
            for(int j=1;j<tree.length;j++){
                if(table[i-1][j]==-1) continue;
                table[i][j]=table[i-1][table[i-1][j]];
            }
        }
    }

    static int findAscendant(int idx, int depth){
        int moveCount = depth;

        int currentIdx=idx;
        int maxTwo = 16;
        for(;maxTwo >=0;maxTwo--){
            if((moveCount & 1 << maxTwo) > 0){
                currentIdx = table[maxTwo][currentIdx];
            }
        }
        return currentIdx;
    }



    static int findBothAscendant(int n1, int n2){
        if(n1 == n2){
            return n1;
        }
        for(int i=1;;i*=2){
            int tmpN1 = findAscendant(n1, i);
            int tmpN2 = findAscendant(n2, i);
            if(tmpN1 == 0 || tmpN1 == -1 || tmpN1 == tmpN2){
                if(i == 1){
                    return tmpN1;
                }
                return findBothAscendant(findAscendant(n1, i/2), findAscendant(n2, i/2));
            }
        }
    }
    
}
