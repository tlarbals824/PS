import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] nmt = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = nmt[0], m = nmt[1], t = nmt[2];

        long[][] map = new long[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(map[i], Integer.MAX_VALUE);
            map[i][i] = 0;
        }
        while (m-- > 0) {
            int[] uvh = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            map[uvh[0]][uvh[1]] = Math.min(map[uvh[0]][uvh[1]], uvh[2]);
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    /**
                     * 기존 경로에서의 높이와, 연결했을 떄의 높이중 최솟값을 가져가야함
                     */
                    if (i == j || i == k || j == k)
                        continue;

                    if (map[i][k] == Integer.MAX_VALUE || map[k][j] == Integer.MAX_VALUE)
                        continue;

                    map[i][j] = Math.min(map[i][j], Math.max(map[i][k], map[k][j]));
                }
            }
        }

        while (t-- > 0) {
            int[] se = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            bw.write((map[se[0]][se[1]] != Integer.MAX_VALUE ? map[se[0]][se[1]] : -1) + "\n");
        }
        bw.flush();
    }

}
