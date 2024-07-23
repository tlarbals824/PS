import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());
        Set<Integer> idxSet = new HashSet<>();

        Arrays.stream(br.readLine().split(" "))
                .forEach(it -> {
                    int num = Integer.parseInt(it);
                    idxSet.add(num);
                });

        List<Integer> idxs = new ArrayList<>(idxSet);
        Collections.sort(idxs);

        int sum = idxs.get(idxs.size() - 1) - idxs.get(0);
        PriorityQueue<Distance> pq = new PriorityQueue<>();
        for (int i = 1; i < idxs.size(); i++) {
            pq.add(new Distance(i - 1, i, idxs.get(i) - idxs.get(i - 1)));
        }

        int[] check = new int[n + 1];
        while (k - 1 > 0 && !pq.isEmpty()) {
            var top = pq.poll();
            if (check[top.idx1] == 1 || check[top.idx2] == 1)
                continue;
            sum -= top.distance;
            k--;
        }
        System.out.println(sum);
    }

    private static class Distance implements Comparable<Distance> {
        int idx1, idx2;
        int distance;

        public Distance(int idx1, int idx2, int distance) {
            this.idx1 = idx1;
            this.idx2 = idx2;
            this.distance = distance;
        }

        public int compareTo(Distance other) {
            if (other.distance == this.distance) {
                return this.idx1 - other.idx1;
            }
            return other.distance - this.distance;
        }
    }

}
