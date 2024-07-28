import java.util.*;
import java.io.*;

class Main {

    private static int[] dirY = { -1, 0, 1, 0 };
    private static int[] dirX = { 0, -1, 0, 1 };

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));

        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int n = input[0];
        int m = input[1];
        boolean[][] check = new boolean[n + 1][m + 1];
        char[][] maze = new char[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            char[] row = br.readLine().toCharArray();
            for (int j = 1; j <= m; j++) {
                maze[i][j] = row[j - 1];
            }
        }

        PriorityQueue<Pos> pq = new PriorityQueue<>();
        pq.add(new Pos(1, 1, 1));
        while (!pq.isEmpty()) {
            var top = pq.poll();
            if (top.x == m && top.y == n) {
                System.out.println(top.count);
                break;
            }

            if (check[top.y][top.x]) {
                continue;
            }
            check[top.y][top.x] = true;

            for (int i = 0; i < 4; i++) {
                int nextY = top.y + dirY[i];
                int nextX = top.x + dirX[i];
                if (nextY < 1 || nextY > n)
                    continue;
                if (nextX < 1 || nextX > m)
                    continue;
                if (maze[nextY][nextX] == '0')
                    continue;
                if (check[nextY][nextX])
                    continue;

                pq.add(new Pos(nextX, nextY, top.count + 1));
            }
        }
    }

    private static class Pos implements Comparable<Pos> {
        int x, y;
        int count;

        public Pos(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }

        public int compareTo(Pos other) {
            return this.count - other.count;
        }
    }

}
