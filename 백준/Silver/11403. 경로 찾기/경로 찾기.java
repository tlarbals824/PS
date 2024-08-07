import java.util.*;
import java.io.*;

class Main {

    private static Integer MAX = 1000000;

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[][] floyd = new int[n][n];
        for (int i = 0; i < n; i++) {
            floyd[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (floyd[i][j] == 0) {
                    floyd[i][j] = MAX;
                }
            }
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    floyd[i][j] = Math.min(floyd[i][j], floyd[i][k] + floyd[k][j]);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print((floyd[i][j] != MAX ? 1 : 0) + " ");
            }
            System.out.println();
        }
    }

}
