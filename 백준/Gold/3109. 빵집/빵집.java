import java.util.*;
import java.io.*;

class Main {

    private static int result = 0;
    private static char[][] map;
    private static int r, c;

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));

        int[] rc = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        r = rc[0];
        c = rc[1];

        map = new char[r][c];

        for (int i = 0; i < r; i++) {
            map[i] = br.readLine().toCharArray();
        }

        for (int i = 0; i < r; i++) {
            dfs(i, 0);
        }

        for (int i = 0; i < r; i++) {
            if (map[i][c - 1] == 'O')
                result++;
        }
        // for (int i = 0; i < r; i++) {
        // System.out.println(Arrays.toString(map[i]));
        // }
        // System.out.println();

        System.out.println(result);
    }

    private static char dfs(int y, int x) {
        if (x == c - 1) {
            return map[y][x] = 'O';
        } else {
            char result = '.';
            for (int i = -1; i <= 1; i++) {
                if (y + i < 0 || y + i >= r)
                    continue;
                if (map[y + i][x + 1] != '.')
                    continue;

                result = dfs(y + i, x + 1);
                if (result == 'O')
                    break;
                else {
                    map[y + i][x + 1] = 'X';
                }
            }
            return map[y][x] = result;
        }
    }

}
