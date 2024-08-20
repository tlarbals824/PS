import java.util.*;
import java.io.*;

class Main {

    private static final int MODULAR = 1000000007;
    private static long[] twoModular = new long[3000100];

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        twoModular[0] = 1;
        for (int i = 1; i <= n; i++) {
            twoModular[i] = (twoModular[i - 1] * 2) % MODULAR;
        }
        for (int i = 1; i <= n; i++) {
            twoModular[i] = (twoModular[i - 1] + twoModular[i]) % MODULAR;
        }
        /**
         * 정렬한 다음에
         * 
         * n=6
         * 0 1 2 3 4
         * 1 1 1 1 1
         * 2 4 4 4 2
         * 4 8 12 8 4
         * 8 16 16 16 8
         * 16 16 16 16 16
         * 
         * 
         * 0 1 2 3 4 5
         * 1 1 1 1 1 1
         * 1 2 2 2 2 1
         * 1 2 3 3 2 1
         * 1 2 3 3 2 1
         * 1 2 2 2 2 1
         * 1 1 1 1 1 1
         * 
         * mul[i]=mul[n-2-i]=(mul[i-1]+twoModular[n-2-i]-twoModular[i-1])%MODULAR;
         * 
         * 두 수를 뽑고 그 사이에 값들은 있든 없든이라 2^n 하면 됨
         * 
         * 최대 nlogn 로 풀어야함
         */

        Arrays.sort(input);

        long[] mul = new long[n + 10];
        if (n > 1) {
            mul[0] = mul[n - 2] = twoModular[n - 2];
        }

        for (int i = 1; i <= (n - 1) / 2; i++) {
            long add = twoModular[n - 2 - i] - twoModular[i - 1];
            if (add < 0) {
                add += MODULAR;
            }
            mul[i] = mul[n - 2 - i] = (mul[i - 1] + add) % MODULAR;
        }

        long result = 0;
        for (int i = 0; i < n - 1; i++) {
            result = (result + (mul[i] * (input[i + 1] - input[i]) % MODULAR) % MODULAR) % MODULAR;
        }
        System.out.println(result);

    }
}