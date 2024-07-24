import java.util.*;
import java.io.*;

class Main {

    private static long[] size = new long[51];
    private static long[] count = new long[51];

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));

        long[] input = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();

        size[0] = count[0] = 1;

        for (int i = 1; i < 51; i++) {
            size[i] = size[i - 1] * 2 + 3;
            count[i] = count[i - 1] * 2 + 1;
        }

        long n = input[0];
        long x = input[1];

        long result = bs(1, size[(int) n], x, n);
        System.out.println(result);
    }

    private static long bs(long left, long right, long target, long n) {
        long start = left;
        long end = right;

        long result = 0;

        while (start <= end) {
            if (target == end) {
                result += count[(int) n];
                break;
            }
            if (target == start) {
                break;
            }
            long mid = (start + end) / 2;
            if (target == mid) {
                result += (1 + count[(int) n - 1]);
                break;
            } else if (target < mid) {
                n--;
                start++;
                end = mid - 1;
            } else { // target > mid
                start = mid + 1;
                end--;
                result += (1 + count[(int) n - 1]);
                n--;
            }
        }
        return result;
    }

}
