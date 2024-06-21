import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] nm = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[] input = new int[nm[0] + 1];
        int[][] tree = new int[2][4 * nm[0]];

        for (int i = 1; i <= nm[0]; i++) {
            input[i] = Integer.parseInt(br.readLine());
        }

        maxInit(input, tree[0], 1, 1, nm[0]);
        minInit(input, tree[1], 1, 1, nm[0]);

        for (int i = 0; i < nm[1]; i++) {
            int[] ab = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int max = findMax(input, tree[0], 1, 1, nm[0], ab[0], ab[1]);
            int min = findMin(input, tree[1], 1, 1, nm[0], ab[0], ab[1]);
            bw.write(min + " " + max + "\n");
        }
        bw.flush();

    }

    static int maxInit(int[] arr, int[] tree, int idx, int start, int end) {
        if (start == end) {
            return tree[idx] = arr[start];
        } else {
            return tree[idx] = Math.max(maxInit(arr, tree, idx * 2, start, (start + end) / 2),
                    maxInit(arr, tree, idx * 2 + 1, (start + end) / 2 + 1, end));
        }
    }

    static int findMax(int[] arr, int[] tree, int idx, int start, int end, int left, int right) {
        // System.out.println(start + " " + end);
        if (start > right || end < left)
            return -1;
        if (start >= left && end <= right) {
            return tree[idx];
        } else {
            if (start == end) {
                return tree[idx];
            } else {
                int mid = (start + end) / 2;
                return Math.max(findMax(arr, tree, idx * 2, start, mid, left, right),
                        findMax(arr, tree, idx * 2 + 1, mid + 1, end, left, right));

            }
        }
    }

    static int minInit(int[] arr, int[] tree, int idx, int start, int end) {
        if (start == end) {
            return tree[idx] = arr[start];
        } else {
            return tree[idx] = Math.min(minInit(arr, tree, idx * 2, start, (start + end) / 2),
                    minInit(arr, tree, idx * 2 + 1, (start + end) / 2 + 1, end));
        }
    }

    static int findMin(int[] arr, int[] tree, int idx, int start, int end, int left, int right) {
        if (start > right || end < left)
            return 1000000100;
        if (start >= left && end <= right) {
            return tree[idx];
        } else {
            if (start == end) {
                return tree[idx];
            } else {
                int mid = (start + end) / 2;
                return Math.min(findMin(arr, tree, idx * 2, start, mid, left, right),
                        findMin(arr, tree, idx * 2 + 1, mid + 1, end, left, right));

            }
        }
    }

}
