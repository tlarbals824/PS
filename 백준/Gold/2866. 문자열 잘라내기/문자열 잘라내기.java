import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));

        int[] rc = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int r = rc[0], c = rc[1];
        String[] input = new String[r];
        for (int i = 0; i < r; i++) {
            input[i] = br.readLine();
        }

        /**
         * 아래서부터 시작해서 중복되는 인덱스를 구함
         */

        Queue<Integer> q = new LinkedList<>();
        Map<Integer, String> coupledString = new HashMap<>();
        for (int i = 0; i < c; i++) {
            q.add(i);
            coupledString.put(i, "");
        }
        Map<String, Set<Integer>> subString = new HashMap<>();
        int count = r;
        /**
         * r-1, r-2 하나씩
         * 
         * 첫 줄에서 중복이 발생한지 어떻게할지?
         * 
         */

        for (int i = r - 1; i >= 0; i--) {
            if (q.size() > 0) {
                count--;
            }
            while (!q.isEmpty()) {
                int top = q.poll();
                String target = coupledString.get(top);
                String key = target + input[i].charAt(top);
                var list = subString.getOrDefault(key, new HashSet<>());
                list.add(top);
                subString.put(key, list);
            }

            for (var entry : subString.entrySet()) {
                if (entry.getKey().length() != r - i) {
                    continue;
                }
                if (entry.getValue().size() > 1) {
                    var list = entry.getValue();
                    for (int num : list) {
                        q.add(num);
                        coupledString.put(num, entry.getKey());
                    }
                }
            }
        }

        System.out.println(count);
    }

}
