import java.util.*;
import java.io.*;

class Main {

    private static List<Integer> list;
    private static int[] check;
    private static int[] tmp;
    private static Set<String> result = new HashSet<>();

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] nm = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        list = new ArrayList<>();
        for (int i = 0; i < nm[0]; i++) {
            list.add(input[i]);
        }
        Collections.sort(list);
        check = new int[list.size()];
        tmp = new int[nm[1]];

        dfs(0, nm[1]);
    }

    private static void dfs(int count, int m) {
        if (count == m) {
            var sb = new StringBuilder();
            for (int i = 0; i < m; i++) {
                sb.append(list.get(tmp[i]));
                if (i != m - 1) {
                    sb.append(" ");
                }
            }
            String target = sb.toString();
            if (!result.contains(target)) {
                System.out.println(target);
                result.add(target);
            }

        } else {
            for (int i = 0; i < list.size(); i++) {
                if (check[i] != 0)
                    continue;
                tmp[count] = i;
                check[i] = 1;
                dfs(count + 1, m);
                check[i] = 0;
            }
        }
    }

}
