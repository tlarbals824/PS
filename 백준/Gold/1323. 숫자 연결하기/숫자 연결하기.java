import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        int k = Integer.parseInt(input[1]);
        int count = 1;
        int nLength = input[0].length();
        long nSizeModular = (long) Math.pow(10, nLength) % k;
        long nModular = Long.parseLong(input[0]) % k;
        long current = 0;
        int[] check = new int[k + 1];
        long[] nSizeModularArr = new long[k + 1];
        nSizeModularArr[1] = nSizeModular;
        nSizeModularArr[0] = 1;
        for (int i = 2; i <= k; i++) {
            nSizeModularArr[i] = (nSizeModularArr[i - 1] * nSizeModular) % k;
        }
        while (count <= k) {

            long modular = (nModular * nSizeModularArr[count - 1]) % k;

            modular = (current + modular) % k;

            if (check[(int) modular] > 0) {
                break;
            }
            if (modular == 0) {
                System.out.println(count);
                return;
            } else {
                check[(int) modular]++;
                current = modular;
                count++;
            }
        }
        System.out.println(-1);
    }

}
