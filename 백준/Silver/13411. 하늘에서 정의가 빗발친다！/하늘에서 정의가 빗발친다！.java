import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        PriorityQueue<Pos> pq = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            pq.add(new Pos(input[0], input[1], input[2], i + 1));
        }
        for (int i = 0; i < n; i++) {
            var target = pq.poll();
            bw.write(target.idx + "\n");
        }
        bw.flush();
    }

    private static class Pos implements Comparable<Pos> {
        Double dis;
        int idx;

        public Pos(double x, double y, double v, int idx) {
            this.dis = Math.sqrt(x * x + y * y) / v;
            this.idx = idx;
        }

        public int compareTo(Pos other) {
            if (this.dis.equals(other.dis)) {
                return this.idx - other.idx;
            }
            return this.dis.compareTo(other.dis);
        }
    }

}
