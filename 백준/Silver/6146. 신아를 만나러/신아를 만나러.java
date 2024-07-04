import java.util.*;
import java.io.*;

class Main {

    static int startY = 501;
    static int startX = 501;
    static int targetY, targetX;
    static int[] dirY = { -1, 0, 1, 0 };
    static int[] dirX = { 0, -1, 0, 1 };
    static int minX = 1100, minY = 1100, maxX = -1100, maxY = -1100;
    static int[][] check = new int[1100][1100];

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));

        int[] xyn = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        targetX = xyn[0] + startX;
        targetY = xyn[1] + startY;
        minX = maxX = startX;
        minY = maxY = startY;

        int[][] map = new int[1010][1010];

        for (int i = 0; i < xyn[2]; i++) {
            int[] input = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            map[startX + input[0]][startY + input[1]] = 1;
            minX = Math.min(minX, startX + input[0]);
            minY = Math.min(minY, startY + input[1]);
            maxX = Math.max(maxX, startX + input[0]);
            maxY = Math.max(maxY, startY + input[1]);
        }

        PriorityQueue<Pos> pq = new PriorityQueue<>();
        pq.add(new Pos(startY, startX, 0, 0, 0));
        while (!pq.isEmpty()) {
            var top = pq.poll();
            if(check[top.x][top.y] <= top.count &&  check[top.x][top.y]!=0) continue;
            check[top.x][top.y]=top.count;

            if (top.y == targetY && top.x == targetX) {
                System.out.println(top.count);
                return;
            }
            for (int i = 0; i < 4; i++) {
                int newY = dirY[i] + top.y;
                int newX = dirX[i] + top.x;

                if (newY < minY - 1 || newY > maxY + 1)
                    continue;
                if (newX < minX - 1 || newX > maxX + 1)
                    continue;
                if (newY == top.prevY && newX == top.prevX)
                    continue;
                if (map[newX][newY] == 1)
                    continue;

                pq.add(new Pos(newY, newX, top.count + 1, top.y, top.x));
            }
        }
    }

    static class Pos implements Comparable<Pos> {
        int y, x;
        int count;
        int prevY, prevX;

        public Pos(int y, int x, int count, int prevY, int prevX) {
            this.y = y;
            this.x = x;
            this.count = count;
            this.prevY = prevY;
            this.prevX = prevX;
        }

        public int compareTo(Pos target) {
            return this.count - target.count;
        }

    }
}