import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            long[] nm = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
            long n = nm[0], m = nm[1];

            long result = maxBit(n) + 1 + m;
            bw.write(result + "\n");
        }
        bw.flush();

    }

    private static long maxBit(long target) {
        int result = -1;
        for (int i = 0; i < 63; i++) {
            if ((target & (long) 1 << i) > 0) {
                result = i;
            }
        }
        return result;
    }

}
