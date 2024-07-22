import java.util.*;
import java.io.*;

class Main {

    private static long[] sum = new long[100000];

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        for (int i = 1; i < 100000; i++) {
            sum[i] = sum[i - 1] + i;
        }

        int count = 0;

        while (n > 0) {
            int idx = bs(n, 1, 100000);
            if (sum[idx] > n) {
                idx--;
            }
            n -= sum[idx];
            count += idx;
        }
        System.out.println(count);
    }

    private static int bs(int target, int start, int end) {
        int left = start;
        int right = end;

        while (left < right) {
            int mid = (left + right) / 2;
            if (sum[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

}
