import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));

        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int c = input[0], k = input[1], p = input[2];
        int result = (k * c * (c + 1)) / 2 + (p * c * (c + 1) * (2 * c + 1)) / 6;
        System.out.println(result);
    }
}
