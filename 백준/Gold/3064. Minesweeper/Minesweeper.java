import java.util.*;
import java.io.*;

class Main {

    private static int[] dirY = { -1, -1, -1, 0, 0, 1, 1, 1 };
    private static int[] dirX = { -1, 0, 1, -1, 1, -1, 0, 1 };

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            bw.write(findMine(br, bw) + "\n");
        }
        bw.flush();
    }

    private static int findMine(BufferedReader br, BufferedWriter bw) throws Exception {
        int n = Integer.parseInt(br.readLine());
        char[][] map = new char[n][n];
        int[][] check = new int[n][n];
        for (int i = 0; i < n; i++) {
            map[i] = br.readLine().toCharArray();
        }
        /**
         * 1,1 시작해서 n-2,n-2까지 가면서 최솟값을 구해, 그리고 #칸에 점유하고 있는 지뢰수도 검색해, 만약에 같다? 그러면 넘어감.
         * 작다? 지금 위치 표시하기.
         * 
         * 중간에는 다 심어두 됨
         * 
         */
        int result = dfs(0, 0, 0, check, map, n);
        if (n > 4) {
            result += (n - 4) * (n - 4);
        }
        // for (int i = 0; i < n; i++) {
        //     bw.write(Arrays.toString(check[i]) + "\n");
        // }
        return result;
    }

    private static int dfs(int y, int x, int count, int[][] check, char[][] map, int n) {
        if (y == n - 1 && x == n - 1) {
            return count;
        } else {
            int mineCount = map[y][x] - '0';

            int addMineCount = 0;

            for (int i = 0; i < 8; i++) {
                int targetY = y + dirY[i];
                int targetX = x + dirX[i];
                if (targetY <= 0 || targetY >= n - 1)
                    continue;
                if (targetX <= 0 || targetX >= n - 1)
                    continue;
                if (map[targetY][targetX] == '#') {
                    if (check[targetY][targetX] == 0) {
                        if (mineCount > 0) {
                            mineCount--;
                            check[targetY][targetX] = 1;
                            addMineCount++;
                        } else {
                            check[targetY][targetX] = -1;
                        }
                    } else if (check[targetY][targetX] == 1) {
                        mineCount--;
                    }
                }
            }
            check[y][x] = addMineCount;

            int nextCount = count + addMineCount;
            int nextY, nextX;
            if (y == 0 || y == n - 1) {
                nextY = (x == n - 1 ? y + 1 : y);
                nextX = (x == n - 1 ? 0 : x + 1);
            } else {
                nextY = (x == 0 ? y : y + 1);
                nextX = (x == 0 ? n - 1 : 0);
            }
            return dfs(nextY, nextX, nextCount, check, map, n);
        }
    }

}
