import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));

        long n = Long.parseLong(br.readLine());

        /**
         * 서로소를 구하면 됨
         * 
         * 1 <= n <= 10^12.
         * 
         * O(n)보다 작아야함
         * 
         * 1. n에 대한 약수들을 모두 구함. logn * n
         * 2. 에어쩌구 채, logn * logn*
         * 
         * 
         * 배열로 관리할 수 없음, 어떻게 관리하면 좋을까
         */

        long result = n;
        for (long i = 2; i <= (long) Math.sqrt(n); i++) {
            if (n % i == 0) {
                result = result - result / i;
                while (n % i == 0)
                    n /= i;
            }
        }

        // 본인이 소수일때
        if (n > 1) {
            result = result - result / n;
        }

        System.out.println(result);
    }

}
