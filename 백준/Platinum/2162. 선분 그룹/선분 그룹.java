import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] tree = new int[n + 1];
        int[] treeSize = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            tree[i] = i;
        }
        Arrays.fill(treeSize, 1);
        int treeCount = n;

        Line[] lineArr = new Line[n + 1];

        for (int i = 1; i <= n; i++) {
            int[] input = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(str -> Integer.parseInt(str))
                    .toArray();

            lineArr[i] = new Line(input[0], input[1], input[2], input[3]);
        }

        for (int i = 1; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
                if (i == j)
                    continue;
                int idx1 = findIdx(tree, i);
                int idx2 = findIdx(tree, j);
                if (idx1 == idx2)
                    continue;

                if (check(lineArr[i], lineArr[j])) {
                    tree[idx1] = idx2;
                    treeSize[idx2] += treeSize[idx1];
                    treeSize[idx1] = 0;
                    treeCount--;
                }
            }
        }

        int maxResult = 0;

        for (int i = 1; i <= n; i++) {
            maxResult = Math.max(maxResult, treeSize[i]);
        }
        System.out.println(treeCount);
        System.out.println(maxResult);
    }

    static int findIdx(int[] tree, int idx) {
        if (tree[idx] == idx)
            return idx;
        return tree[idx] = findIdx(tree, tree[idx]);
    }

    static boolean check(Line l1, Line l2) {
        boolean check1 = ccw(l1.x1, l1.y1, l1.x2, l1.y2, l2.x1, l2.y1)
                * ccw(l1.x1, l1.y1, l1.x2, l1.y2, l2.x2, l2.y2) <= 0;
        boolean check2 = ccw(l2.x1, l2.y1, l2.x2, l2.y2, l1.x1, l1.y1)
                * ccw(l2.x1, l2.y1, l2.x2, l2.y2, l1.x2, l1.y2) <= 0;
        boolean check3 = Math.min(l1.x1, l1.x2) <= Math.max(l2.x1, l2.x2) &&
                Math.min(l2.x1, l2.x2) <= Math.max(l1.x1, l1.x2) &&
                Math.min(l1.y1, l1.y2) <= Math.max(l2.y1, l2.y2) &&
                Math.min(l2.y1, l2.y2) <= Math.max(l1.y1, l1.y2);
        return check1 && check2 && check3;
    }

    static long ccw(int x1, int y1, int x2, int y2, int x3, int y3) {
        long tmp = ((long) x1 * y2 + x2 * y3 + x3 * y1) - ((long) x1 * y3 + x2 * y1 + x3 * y2);
        if (tmp == 0)
            return 0;
        if (tmp > 0)
            return 1;
        return -1;
    }

    static class Line {
        int x1;
        int y1;
        int x2;
        int y2;

        Line(int x1, int y1, int x2, int y2) {

            this.y1 = y1;
            this.x1 = x1;
            this.y2 = y2;
            this.x2 = x2;
        }
    }

}
