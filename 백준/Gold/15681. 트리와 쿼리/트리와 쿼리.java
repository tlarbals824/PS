import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] nrq = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        Map<Integer, Set<Integer>> v = new HashMap<>();

        for (int i = 0; i < nrq[0]-1; i++) {
            int[] uv = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            var uToSet = v.getOrDefault(uv[0], new HashSet<>());
            var vToSet = v.getOrDefault(uv[1], new HashSet<>());

            uToSet.add(uv[1]);
            vToSet.add(uv[0]);

            v.put(uv[0], uToSet);
            v.put(uv[1], vToSet);
        }

        Map<Integer, Set<Integer>> tree = new HashMap<>();
        int[] sizeArr = new int[nrq[0]+1];

        makeTree(nrq[1], -1, tree, v);

        calSize(nrq[1], tree, sizeArr);

        for(int i=0;i<nrq[2];i++){
            bw.write(sizeArr[Integer.parseInt(br.readLine())]+"\n");
        }
        bw.flush();
    }

    static void makeTree(int currentNode, int parentNode, Map<Integer, Set<Integer>> tree,
            Map<Integer, Set<Integer>> vMap) {
        var current = tree.getOrDefault(currentNode, new HashSet<>());
        for(int child: vMap.get(currentNode)){
            if(child==parentNode) continue;
            current.add(child);
            makeTree(child, currentNode, tree, vMap);
        }
        tree.put(currentNode, current);
    }

    static void calSize(int currentNode, Map<Integer, Set<Integer>> tree, int[] sizeArr){
        sizeArr[currentNode]=1;
        for(int child : tree.get(currentNode)){
            calSize(child, tree, sizeArr);
            sizeArr[currentNode]+=sizeArr[child];
        }
    }

}
