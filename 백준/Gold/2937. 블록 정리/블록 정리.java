import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));

        int[] nm = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int n = nm[0], m = nm[1];
        int[][] board = new int[n + 1][n + 1];

        for (int i = 0; i < m; i++) {
            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            board[input[0]][input[1]] = 1;
        }

        int[][] sum = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                sum[i][j] = board[i][j] + sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1];
            }
        }

        /**
         * 직사각형 판별 방법은?
         * 범위 합 구하는 문제네
         * 
         * (x1, y1) 부터 (x2, y2)까지 몇개가 있니? 비어있는거가 움직여야해
         * 
         * 
         * i, j가 구해지면 -> m 순회하면서 (x1, y1) , (x1+i, y1+j) 범위 합을 구함 m과 차이가 얼마나 나는지 구함
         */

        int result = m;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                for (int k = 1; k <= m; k++) {
                    if (m % k != 0)
                        continue;
                    int x = i, y = j;
                    int range = 0;

                    if (y + k - 1 <= n && x + m/k - 1 <= n) {
                        range = sum[y + k - 1][x + m/k - 1] - sum[y + k - 1][x - 1] - sum[y - 1][x + m/k - 1]
                                + sum[y - 1][x - 1];
                    }

                    result = Math.min(result, m - range);
                }
            }
        }

        System.out.println(result);
    }

}
