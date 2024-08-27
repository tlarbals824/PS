import java.util.*;
import java.io.*;

class Main {

    private static List<Integer> list;
    private static int[] result;

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));

        int[] nm = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        var set = new TreeSet<Integer>();
        for (int i = 0; i < nm[0]; i++) {
            set.add(input[i]);
        }
        list = new ArrayList<>(set);
        result = new int[nm[1]];
        dfs(0, nm[1], 0, list.size());
    }

    private static void dfs(int count, int m, int idx, int n) {
        if (count == m) {
            for (int i = 0; i < m; i++) {
                System.out.print(list.get(result[i]) + " ");
            }
            System.out.println();
        } else {
            for (int i = idx; i < n; i++) {
                result[count] = i;
                dfs(count + 1, m, i, n);
            }
        }
    }

}
