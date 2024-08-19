import java.util.*;
import java.io.*;

class Main {

    private static List<Start> line = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            line.add(new Start(Math.min(input[0], input[1]), Math.max(input[0], input[1])));

        }

        Collections.sort(line);

        PriorityQueue<End> end = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            var target = line.get(i);
            end.add(new End(i, target.start, target.end));
        }

        int d = Integer.parseInt(br.readLine());

        int start = 0;
        int count = 0;
        int result = 0;
        while (!end.isEmpty()) {
            /**
             * top.start < line.get(start).start
             * 
             * 
             */

            var top = end.poll();
            if (top.end - top.start > d)
                continue;

            if (top.start < line.get(start).start)// 그러면 top이 먼저 나왔을거임 일단은? 그러면 등장한다면 범위가 긴거일 거임, end가 큰거
                continue;

            if (Math.max(line.get(start).end, top.end) - line.get(start).start > d) {
                int prevStart = start;
                count++;
                while (start <= top.idx && Math.max(line.get(start).end, top.end) - line.get(start).start > d) {
                    if (line.get(start).end - line.get(start).start > d) {
                        start++;
                        continue;
                    }
                    start++;
                    count--;
                }
            } else {
                count++;
                result = Math.max(result, count);
            }
        }

        System.out.println(result);

        /**
         * idx 하나씩 탐색?
         * 
         * start로 정렬
         * end로 정렬
         * 
         * start로 탐색 + end 이분탐색(start보다 크거나 같은 경우)
         */

    }

    private static class Start implements Comparable<Start> {
        int start, end;

        public Start(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int compareTo(Start other) {
            if (this.start == other.start) {
                return this.end - other.end;
            }
            return this.start - other.start;
        }

        public String toString() {
            return start + "->" + end;
        }
    }

    private static class End implements Comparable<End> {
        int idx;
        int start, end;

        public End(int idx, int start, int end) {
            this.idx = idx;
            this.start = start;
            this.end = end;
        }

        public int compareTo(End other) {
            if (this.end == other.end) {
                return this.start - other.start;
            }
            return this.end - other.end;
        }
    }

}
