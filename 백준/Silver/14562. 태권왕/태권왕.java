import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int c = Integer.parseInt(br.readLine());
        while (c-- > 0) {
            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            /**
             * 1. 내 점수 2배, 상대 점수 +3
             * bfs
             */

            PriorityQueue<Pair> pq = new PriorityQueue<>();
            pq.add(new Pair(input[1], input[0], 0));
            while (!pq.isEmpty()) {
                var top = pq.poll();

                if (top.t == top.s) {
                    bw.write(top.count + "\n");
                    break;
                }

                if (top.t + 3 >= top.s * 2) {
                    pq.add(new Pair(top.t + 3, top.s * 2, top.count + 1));
                }
                pq.add(new Pair(top.t, top.s + 1, top.count + 1));
            }
        }
        bw.flush();

    }

    private static class Pair implements Comparable<Pair> {
        int t;
        int s;
        int count;

        public Pair(int t, int s, int count) {
            this.t = t;
            this.s = s;
            this.count = count;
        }

        public int compareTo(Pair other) {
            return this.count - other.count;
        }

    }

}
