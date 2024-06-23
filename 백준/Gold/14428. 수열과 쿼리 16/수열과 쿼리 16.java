import java.util.*;
import java.io.*;

class Main {

    static Integer MAX_VALUE = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        String[] input = br.readLine().split(" ");
        int[] arr = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(input[i-1]);
        }
        arr[0] = MAX_VALUE;

        int[] tree = new int[4 * n];
        makeTree(tree, arr, 1, 1, n);

        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            int[] command = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            if (command[0] == 1) {
                arr[command[1]] = command[2];
                update(tree, arr, 1, 1, n, command[1]);
            } else {
                bw.write(findMin(tree, arr, 1, 1, n, command[1], command[2]) + "\n");
            }
        }
        bw.flush();
    }


    static int makeTree(int[] tree, int[] arr, int idx, int start, int end) {
        if (start == end) {
            return tree[idx] = start;
        } else {
            int v1 = makeTree(tree, arr, idx * 2, start, (start + end) / 2);
            int v2 = makeTree(tree, arr, idx * 2 + 1, (start + end) / 2 + 1, end);
            if (arr[v1] <= arr[v2]) {
                tree[idx] = v1;
            } else {
                tree[idx] = v2;
            }
            return tree[idx];
        }
    }

    static int update(int[] tree, int[] arr, int idx, int start, int end, int changeIdx) {
        if (start == end) {
            return tree[idx];
        } else {
            int mid = (start + end) / 2;
            int v1,v2;
            if (changeIdx <= mid) {
                v1 = update(tree, arr, idx * 2, start, mid, changeIdx);
                v2 = tree[idx * 2 + 1];
            } else {
                v1 = tree[idx * 2];
                v2 = update(tree, arr, idx * 2 + 1, mid + 1, end, changeIdx);
            }
            if (arr[v1] <= arr[v2]) {
                tree[idx] = v1;
            } else {
                tree[idx] = v2;
            }
            return tree[idx];
        }
    }

    static int findMin(int[] tree, int[] arr, int idx, int start, int end, int i, int j) {
        if (start > j || end < i)
            return 0;
        if (start >= i && j >= end)
            return tree[idx];
        else {
            int v1 = findMin(tree, arr, idx * 2, start, (start + end) / 2, i, j);
            int v2 = findMin(tree, arr, idx * 2 + 1, (start + end) / 2 + 1, end, i, j);
            if (arr[v1] <= arr[v2]) {
                return v1;
            } else {
                return v2;
            }
        }
    }

}
