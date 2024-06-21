import java.util.*;
import java.io.*;

class Main {

    static long divideValue = 1000000007;
    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] nmk = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        long[] input = new long[nmk[0] + 1];

        for (int i = 1; i <= nmk[0]; i++) {
            input[i] = Long.parseLong(br.readLine());
        }

        long[] tree = new long[4 * nmk[0]];
        init(tree, input, 1, 1, nmk[0]);

        for (int i = 0; i < nmk[1] + nmk[2]; i++) {
            long[] command = Arrays.stream(br.readLine().split(" "))
                    .mapToLong(Long::parseLong)
                    .toArray();

            if (command[0] == 1) {
                update(tree, 1, 1, nmk[0], command[1], command[2]);
            } else {
                bw.write(mul(tree, 1, 1, nmk[0], command[1], command[2]) + "\n");
            }
        }
        bw.flush();
    }

    static long init(long[] tree, long[] arr, int idx, int start, int end) {
        if (start == end) {
            return tree[idx] = arr[start];
        } else {
            return tree[idx] = (init(tree, arr, idx * 2, start, (start + end) / 2)
                    * init(tree, arr, idx * 2 + 1, (start + end) / 2 + 1, end)) % divideValue;
        }
    }

    static long update(long[] tree, int idx, int start, int end, long changeIdx, long changeValue) {
        if (start == end) {
            if (start == changeIdx)
                return tree[idx] = changeValue;
            else
                return tree[idx];
        } else {
            if ((start + end) / 2 >= changeIdx) {
                return tree[idx] = (tree[idx * 2 + 1]
                        * update(tree, idx * 2, start, (start + end) / 2, changeIdx, changeValue)) % divideValue;
            } else {
                return tree[idx] = (tree[idx * 2]
                        * update(tree, idx * 2 + 1, (start + end) / 2 + 1, end, changeIdx, changeValue)) % divideValue;
            }
        }
    }

    static long mul(long[] tree, int idx, int start, int end, long b, long c) {
        if (end < b || start > c)
            return 1;
        else if (start >= b && c >= end) {
            return tree[idx];
        } else {
            return (mul(tree, idx * 2, start, (start + end) / 2, b, c)
                    * mul(tree, idx * 2 + 1, (start + end) / 2 + 1, end, b, c)) % divideValue;
        }
    }

}
