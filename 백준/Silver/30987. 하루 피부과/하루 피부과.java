import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));

        /**
         * ax^2 + (b-d)x + c-e
         * a(x2^3 - x1^2)/3 + (b-d)(x2^2 - x1^2) + (c-e)(x2-x1)
         */

        int[] x1x2 = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] abcde = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        System.out.println((int) (abcde[0] * (Math.pow(x1x2[1], 3) - Math.pow(x1x2[0], 3)) / 3
                + (abcde[1] - abcde[3]) * (Math.pow(x1x2[1], 2) - Math.pow(x1x2[0], 2)) / 2
                + (abcde[2] - abcde[4]) * (x1x2[1] - x1x2[0])));
    }

}