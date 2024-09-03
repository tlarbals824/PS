import java.util.*;
import java.io.*;

class Main {

    private static Map<Integer, List<Pair>> tree = new HashMap<>();

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));

        int v = Integer.parseInt(br.readLine());

        for (int i = 1; i <= v; i++) {
            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            List<Pair> list = tree.getOrDefault(input[0], new ArrayList<>());
            for (int j = 1; j < input.length; j += 2) {
                int target = input[j];
                if (target == -1)
                    break;
                int distance = input[j + 1];
                list.add(new Pair(target, distance));
            }
            tree.put(input[0], list);
        }

        var top = calDistance(1);
        var result = calDistance(top.target);

        System.out.println(result.distance);
    }

    private static Pair calDistance(int current) {
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        Pair result = new Pair(current, 0);
        pq.add(result);
        Set<Integer> check = new HashSet<>();
        check.add(current);
        while (!pq.isEmpty()) {
            var top = pq.poll();

            if (top.distance > result.distance) {
                result.distance = top.distance;
                result.target = top.target;
            }

            for (var num : tree.get(top.target)) {
                if (check.contains(num.target))
                    continue;
                check.add(num.target);

                pq.add(new Pair(num.target, num.distance + top.distance));
            }

        }
        return result;
    }

    private static class Pair implements Comparable<Pair> {
        int target;
        int distance;

        public Pair(int target, int distance) {
            this.target = target;
            this.distance = distance;
        }

        public int compareTo(Pair other) {
            return other.distance - this.distance;
        }

        public String toString() {
            return target + ": " + distance;
        }
    }
}
