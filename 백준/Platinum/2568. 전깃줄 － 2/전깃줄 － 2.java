import java.util.*;
import java.io.*;

class Main {

    /**
     * 4 2 6 7 1 3 10
     */
    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var lineSize = Integer.parseInt(br.readLine());
        List<Line> lines = new ArrayList<>();
        for (int i = 0; i < lineSize; i++) {
            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            lines.add(new Line(input[0], input[1]));
        }
        Collections.sort(lines);

        List<Integer> result = new ArrayList<>();
        int[] resultIndex = new int[lineSize];
        result.add(Integer.MAX_VALUE);
        for (int i = 0; i < lineSize; i++) {
            int target = lines.get(i).from;

            if (target > result.get(result.size() - 1)) { // 마지막보다 큰 경우 그냥 추가
                result.add(target);
                resultIndex[i] = result.size() - 1;
            } else {
                int targetIndex = lowerBound(result, target);
                result.set(targetIndex, target);
                resultIndex[i] = targetIndex;
            }
        }

        int maxSize = result.size() - 1;
        int index = lineSize - 1;
        int[] check = new int[lineSize];
        while (maxSize >= 0) {
            if (maxSize == resultIndex[index]) {
                maxSize--;
                check[index] = 1;
            }
            index--;
        }

        bw.write((lineSize - result.size()) + "\n");
        
        result.clear();
        for (int i = 0; i < lineSize; i++) {
            if (check[i] == 0) {
                result.add(lines.get(i).from);
            }
        }
        Collections.sort(result);
        for(int num: result){
            bw.write(num+"\n");
        }
        bw.flush();

    }

    private static int lowerBound(List<Integer> findList, int target) {
        int left = 0;
        int right = findList.size() - 1;

        while (left < right) {
            int mid = (left + right) / 2;
            if (findList.get(mid) < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    private static class Line implements Comparable<Line> {
        int from, to;

        public Line(int from, int to) {
            this.from = from;
            this.to = to;
        }

        public int compareTo(Line other) {
            return this.to - other.to;
        }

        public String toString() {
            return from + "->" + to;
        }

    }
}