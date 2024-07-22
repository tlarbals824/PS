import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));

        int[] nm = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int m = nm[1];
        long[] sum = new long[nm[0] + 10];

        long[] count = new long[nm[1] + 10];

        long result = 0;
        for (int i = 0; i < nm[0]; i++) { // 10^6
            sum[i] = ((i == 0 ? 0 : sum[i - 1]) + input[i]) % m;
            if (sum[i] == 0)
                result++;
            result += count[(int) sum[i]]++;
        }

        System.out.println(result);
    }
}