import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        List<Integer> input = new ArrayList<>();

        while (n-- > 0) {
            input.add(Integer.parseInt(br.readLine()));
        }

        /**
         * 가장 낮은 값부터 시작해서 하나씩 올려가며 하면 될듯?
         * 
         * 2 4 1 2 2 5 1
         * 
         * 2 4 * 2 2 5 1 - 2
         * 2 4 * 2 2 5 * - 3
         * 4 2 2 5 - 4
         * 4 5 - 9
         * - 10
         */

        long result = 0;
        PriorityQueue<Pos> pq = new PriorityQueue<>();
        int[] next = new int[input.size()];
        int[] prev = new int[input.size()];
        int[] check = new int[input.size()];
        for (int i = 0; i < input.size(); i++) {
            next[i] = i + 1;
            prev[i] = i - 1;
            pq.add(new Pos(input.get(i), i));
        }
        next[input.size() - 1] = -1;
        prev[0] = -1;

        while (!pq.isEmpty()) {
            var top = pq.poll();
            if (check[top.idx] == 1)
                continue;
            check[top.idx] = 1;

            long count = 3;
            int leftIdx = prev[top.idx]; // 왼쪽 값이 같으면 이동 왼쪽으로 계속, check[idx]=1 표시하기
            int rightIdx = next[top.idx];
            if (leftIdx != -1) {
                while (input.get(leftIdx) == top.value) { // 같으면 계속 이동
                    count++;
                    check[leftIdx] = 1;
                    leftIdx = prev[leftIdx];

                    if (leftIdx == -1) {
                        count--;
                        break;
                    }
                }
            } else {
                count--;
            }

            if (rightIdx != -1) {
                while (input.get(rightIdx) == top.value) {
                    count++;
                    check[rightIdx] = 1;
                    rightIdx = next[rightIdx];
                    if (rightIdx == -1) {
                        count--;
                        break;
                    }
                }
            } else {
                count--;
            }

            if (leftIdx != -1) {
                next[leftIdx] = rightIdx;
            }
            if (rightIdx != -1) {
                prev[rightIdx] = leftIdx;
            }

            result += (count * (count - 1)) / 2;

            if (leftIdx != -1 && rightIdx != -1) {
                result--;
            }
        }

        System.out.println(result);

    }

    private static class Pos implements Comparable<Pos> {
        int value;
        int idx;

        public Pos(int value, int idx) {
            this.value = value;
            this.idx = idx;
        }

        public int compareTo(Pos other) {
            if (other.value == this.value) {
                return this.idx - other.idx;
            }
            return this.value - other.value;
        }
    }

}
