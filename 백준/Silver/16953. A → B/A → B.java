import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));

        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.add(new Pair(1, input[0]));
        while (!pq.isEmpty()) {
            var top = pq.poll();
            if (top.current == input[1]) {
                System.out.println(top.count);
                return;
            }

            if (top.current * 2 <= input[1]) {
                pq.add(new Pair(top.count + 1, top.current * 2));
            }
            if (top.current * 10 + 1 <= input[1]) {
                pq.add(new Pair(top.count + 1, top.current * 10 + 1));
            }
        }
        System.out.println(-1);
    }

    private static class Pair implements Comparable<Pair> {
        long count;
        long current;

        public Pair(long count, long current) {
            this.count = count;
            this.current = current;
        }

        public int compareTo(Pair other) {
            long tmp = this.count - other.count;
            if (tmp < 0) {
                return -1;
            }
            if (tmp > 0) {
                return 1;
            }
            return 0;
        }

    }

}