import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));

        int[] nm = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int n = nm[0], m = nm[1];
        int[] num = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            num[i] = i;
        }
        for (int i = 0; i < m; i++) {
            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int[] left = Arrays.copyOfRange(num, input[0], input[2]);
            int[] right = Arrays.copyOfRange(num, input[2], input[1] + 1);
            System.arraycopy(left, 0, num, input[0] + right.length, left.length);
            System.arraycopy(right, 0, num, input[0], right.length);
        }

        var sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            sb.append(num[i] + " ");
        }
        System.out.println(sb.toString());

    }

}
