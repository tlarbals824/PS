import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] aArr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] bArr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        // a ^ x = b
        // x ^ a = b
        // x = b ^ a

        // a ^ x1 ^ x2 ^ x3 ..... = b

        int[] xorTmp = new int[n];
        for (int i = 0; i < n; i++) {
            xorTmp[i] = aArr[i] ^ bArr[i];
        }
        int count = 0;
        for (int i = 0; i < n - 1; i++) {
            if (xorTmp[i] == 0)
                continue;
            count++;
            xorTmp[i + 1] = xorTmp[i + 1] ^ xorTmp[i];
        }

        if (xorTmp[n - 1] != 0) {
            System.out.println(-1);
        } else {
            System.out.println(count);
        }
    }
}
