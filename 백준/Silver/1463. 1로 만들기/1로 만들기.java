import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));

        int target = Integer.parseInt(br.readLine());

        PriorityQueue<Num> pq = new PriorityQueue<>();
        pq.add(new Num(target, 0));

        int[] check = new int[1000100];

        while (!pq.isEmpty()) {
            var top = pq.poll();
            if (check[top.number] == 1)
                continue;
            check[top.number] = 1;
            if (top.number == 1) {
                System.out.println(top.count);
                return;
            }
            if (top.number % 3 == 0) {
                pq.add(new Num(top.number / 3, top.count + 1));
            }
            if (top.number % 2 == 0) {
                pq.add(new Num(top.number / 2, top.count + 1));
            }
            pq.add(new Num(top.number - 1, top.count + 1));
        }
    }

    private static class Num implements Comparable<Num> {
        int number;
        int count;

        public Num(int number, int count) {
            this.number = number;
            this.count = count;
        }

        public int compareTo(Num other) {
            if (this.count == other.count) {
                return this.number - other.number;
            }
            return this.count - other.count;
        }
    }

}
