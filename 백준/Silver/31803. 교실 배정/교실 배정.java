import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        // 6 * 3 * 2* 3 / 3!
        long result = 1;
        for (int i = n; i > 0; i -= 2) {
            if (i != 1) {
                result *= (i % 2 == 1 ? i : i - 1);
            }
        }
        System.out.println(result);
    }

    /**
     * n이 짝수일 때.
     * nC2 반복한 뒤, (n/2)!로 나누면 됨
     * 
     * n이 홀수일 때.
     * 12345
     * 
     * 1 3
     */

}
