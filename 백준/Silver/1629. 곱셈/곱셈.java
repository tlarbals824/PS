import java.util.*;
import java.io.*;

class Main {

    private static int[] bit = new int[40];

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));

        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int a = input[0], b = input[1], c = input[2];
        Map<Integer, Integer> modular = new HashMap<>();
        modular.put(1, a % c);
        modular.put(0, 1 % c);

        bit[0] = 1;
        for (int i = 1; i < 32; i++) {
            bit[i] = bit[i - 1] * 2;
        }

        /**
         * a를 b번 곱한 수, 그리고 모듈러 c
         */
        for (long i = 2; i <= b; i *= 2) {
            modular.put((int) i, (int) (((long) modular.get((int) i / 2) * modular.get((int) i / 2)) % c));
        }
        long result = modular.get(0);
        while (b > 0) {
            int bit = maxBit(b);
            result = (result * modular.get(bit)) % c;
            b -= bit;
        }
        System.out.println(result);
    }

    private static int maxBit(int target) {
        int result = -1;
        for (int i = 30; i >= 0; i--) {
            if ((target & 1 << i) > 0) {
                result = i;
                break;
            }
        }

        if (result == -1) {
            return -1;
        }
        return bit[result];
    }

}
