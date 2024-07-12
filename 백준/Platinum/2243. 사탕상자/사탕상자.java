import java.util.*;
import java.io.*;

class Main {

    private static int MAX_VALUE = 1000000;
    private static int MIN_VALUE = 1;
    private static int[] tree = new int[4 * MAX_VALUE + 10];

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] candy = new int[1000100];

        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            int[] command = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            if (command[0] == 1) {
                int rank = command[1];
                int tasteLevel = selectCandy(MIN_VALUE, MAX_VALUE, 1, rank, 0);
                bw.write(tasteLevel + "\n");
                candy[tasteLevel]--;
                updateTree(MIN_VALUE, MAX_VALUE, 1, tasteLevel, candy[tasteLevel]);
            } else {
                int tasteLevel = command[1];
                int candyCount = command[2];
                candy[tasteLevel] += candyCount;
                updateTree(MIN_VALUE, MAX_VALUE, 1, tasteLevel, candy[tasteLevel]);
            }
        }
        bw.flush();
    }

    private static int updateTree(int left, int right, int idx, int targetIdx, int count) {
        if (left == right) {
            if (left == targetIdx) {
                return tree[idx] = count;
            }
            return tree[idx];
        } else {
            int mid = (left + right) / 2;
            if (mid < targetIdx) {
                return tree[idx] = tree[idx * 2] + updateTree(mid + 1, right, idx * 2 + 1, targetIdx, count);
            } else {
                return tree[idx] = updateTree(left, mid, idx * 2, targetIdx, count) + tree[idx * 2 + 1];
            }
        }
    }

    private static int selectCandy(int left, int right, int idx, int level, int sum) {
        if (left == right) {
            return left;
        } else {
            int mid = (left + right) / 2;
            int midBeforeSum = tree[idx * 2];
            if (sum + midBeforeSum < level) {
                return selectCandy(mid + 1, right, idx * 2 + 1, level, sum + midBeforeSum);
            } else {
                return selectCandy(left, mid, idx * 2, level, sum);
            }
        }
    }
}