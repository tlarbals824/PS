import java.util.*;
import java.io.*;

class Main {

    private static int[][] map;
    private static int LIMIT = 1000000;

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));

        int[] nk = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = nk[0], k = nk[1];
        map = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(map[i], LIMIT);
            map[i][i] = 0;
        }

        for (int i = 0; i < k; i++) {
            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int left = input[0], right = input[1];
            map[left][right] = 1;
        }

        for (int x = 1; x <= n; x++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (map[i][x] == 1 && map[x][j] == 1) {
                        map[i][j] = 1;
                    }
                }
            }
        }

        int s = Integer.parseInt(br.readLine());

        while (s-- > 0) {
            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            if (map[input[0]][input[1]] != LIMIT) {
                System.out.println(-1);
            } else if (map[input[1]][input[0]] != LIMIT) {
                System.out.println(1);
            } else {
                System.out.println(0);
            }
        }
    }

}
