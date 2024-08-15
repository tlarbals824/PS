import java.util.*;
import java.io.*;

class Main {

    private static int MODULAR = 1000000007;

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        long[] input = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();

        Arrays.sort(input);

        long[] modular = new long[200010];

        modular[0] = 1;
        for (int i = 1; i < 200010; i++) {
            modular[i] = (modular[i - 1] * 2) % MODULAR;
        }

        long result = 0;
        for (int i = n - 1; i >= 0; i--) {
            result = (result + input[i] * modular[i]) % MODULAR;
        }

        System.out.println(result);

        /**
         * 1 2 3
         * 
         * 
         * 4 5 3
         * 9 5 8
         * 9 15 17
         */
    }

}
