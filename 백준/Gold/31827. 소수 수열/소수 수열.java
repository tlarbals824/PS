import java.util.*;
import java.io.*;

class Main {


    private static int[] check = new int[1000001];
    private static Map<Integer, List<Integer>> primeMap = new HashMap<>();

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));

        int[] nk = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = nk[0], k = nk[1];
        for (int i = 2; i <= 1000000; i++) {
            if (check[i] == 1)
                continue;

            var list = primeMap.getOrDefault(i % k, new ArrayList<>());
            list.add(i);
            primeMap.put(i % k, list);

            for (int j = i; j <= 1000000; j += i) {
                check[j] = 1;
            }
        }

        /**
         * 0으로만 가득 채우기 근데 불가능일거임
         * 
         * 0~k-1 연속으로 더했을 때 0이 되는 경우는?
         * 
         * 3
         * 
         * 0 1 2
         * 
         * 2
         * 1 1 1 1
         * 
         * 3
         * 1 1 1 1 1 1 ...
         * 2 2 2 2 2 2 ...
         * 0 1 2 0 1 2 ...
         * 
         * 
         * 4
         * 1 1 1 1 1
         * 2 2 2 2 2
         * 3 3 3 3 3
         * 
         */

        for (var entry : primeMap.entrySet()) {
            if (entry.getValue().size() >= n) {
                var list = entry.getValue();
                for (int i = 0; i < n; i++) {
                    System.out.print(list.get(i) + " ");
                }
                break;
            }
        }

        System.out.println();
    }

}
