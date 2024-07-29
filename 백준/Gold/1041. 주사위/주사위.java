import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] num = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        if (n == 1) {
            int sum = 0;
            int max = 0;
            for (int i = 0; i < num.length; i++) {
                sum += num[i];
                max = Math.max(max, num[i]);
            }
            sum -= max;
            System.out.println(sum);
            return;
        }

        long result = 0;
        // 1면
        long oneDimension = n - 2;
        long min = Long.MAX_VALUE;
        for (int i = 0; i < num.length; i++) {
            min = Math.min(min, num[i]);
        }
        result += oneDimension * oneDimension * min * 5 + min * oneDimension * 4; // 0
        // 2면 2면 합이 최소인거 고르면 됨
        long twoDimension = n - 2;
        min = Long.MAX_VALUE;
        for (int i = 0; i < num.length; i++) {
            for (int j = 0; j < num.length; j++) {
                if (i == j)
                    continue;
                if ((i + j) == 5)
                    continue;
                min = Math.min(num[i] + num[j], min);
            }
        }
        result += twoDimension * min * 8 + 4 * min; // 8
        // 3면 4개만 확인하면 됨
        // 0-1-2, 0-1-3, 0-2-4, 0-3-4
        min = Math.min(Math.min(num[1] + num[2], num[1] + num[3]), Math.min(num[2] + num[4], num[3] + num[4]));
        min = Math.min(num[0], num[5]) + min;
        result += 4 * min; //
        System.out.println(result);
    }

}
