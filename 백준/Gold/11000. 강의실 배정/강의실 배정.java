import java.util.*;
import java.io.*;

class Main {
    static List<Time> timeArr;
    static int[] check;

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        timeArr = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int[] input = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            timeArr.add(new Time(input[0], input[1]));
        }
        Collections.sort(timeArr);

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(0);

        for (int i = 0; i < n; i++) {
            var top = pq.poll();
            if (top <= timeArr.get(i).start) {
                pq.add(timeArr.get(i).end);
            } else {
                pq.add(top);
                pq.add(timeArr.get(i).end);
            }
        }
        System.out.println(pq.size());
    }

    static int find(int currentTime, int prevIdx) {
        int start = prevIdx;
        int end = timeArr.size();
        while (start < end) {
            int mid = (start + end) / 2;
            if (timeArr.get(mid).start < currentTime) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return end;
    }

    static class Time implements Comparable<Time> {
        int start;
        int end;

        public Time(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int compareTo(Time target) {
            if (target.start == this.start) {
                return this.end - target.end;
            }
            return this.start - target.start;

        }

        public String toString() {
            return start + " to " + end;
        }

        @Override
        public boolean equals(Object other) {
            var target = (Time) other;
            return this.start == target.start && this.end == target.end;
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.start, this.end);
        }

    }

}
