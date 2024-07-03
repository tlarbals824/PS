import java.util.*;
import java.io.*;

class Main {

    static int[][] pascal;

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        pascal = new int[n][n];
        makeTriangle(n);

        var posSet = new HashSet<Pos>();

        for (int i = 0; i < m; i++) {
            int[] input = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            posSet.add(new Pos(input[0], input[1]));
        }

        List<Pos> posList = new ArrayList<>(posSet);
        Collections.sort(posList);

        Pos currentPos = new Pos(0, 0);
        int result = 1;
        for (Pos target : posList) {
            if (target.col - currentPos.col < 0 || target.col - currentPos.col > target.row - currentPos.row) {
                System.out.println(0);
                return;
            }
            result *= pascal[target.row - currentPos.row][target.col - currentPos.col];
            currentPos = target;
        }
        int tmp = 0;
        int row = n - currentPos.row - 1;
        for (int i = 0; i <= row; i++) {
            tmp += pascal[row][i];
        }
        result *= tmp;
        System.out.println(result);
    }

    static void makeTriangle(int n) {
        pascal[0][0] = 1;
        for (int i = 1; i < n; i++) {
            pascal[i][0] = pascal[i][i] = 1;
            for (int j = 1; j < i; j++) {
                pascal[i][j] = pascal[i - 1][j - 1] + pascal[i - 1][j];
            }
        }
    }

    static class Pos implements Comparable<Pos> {
        public int row;
        public int col;

        public Pos(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public int compareTo(Pos other) {
            return this.row - other.row;
        }

        @Override
        public boolean equals(Object other) {
            var target = (Pos) other;
            return target.row == this.row && target.col == this.col;
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.row, this.col);
        }
    }

}
