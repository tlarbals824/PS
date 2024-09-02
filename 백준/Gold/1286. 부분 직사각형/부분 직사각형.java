import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));

        long[] result = new long[40];

        int[] nm = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int n = nm[0], m = nm[1];
        long[][] check = new long[n * 2][m * 2];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                long add = (2L * n - i) * (i + 1) * (2L * m - j) * (j + 1L);
                check[i][j] += add;
                check[n - i - 1][j] += add;
                check[i][m - j - 1] += add;
                check[n - i - 1][m - j - 1] += add;
            }
        }

        for (int i = 0; i < n; i++) {
            String input = br.readLine();
            for (int j = 0; j < m; j++) {
                int target = input.charAt(j) - 'A';
                result[target] += check[i][j];
            }
        }

        for (int i = 0; i < 26; i++) {
            System.out.println(result[i]);
        }

    }
}
