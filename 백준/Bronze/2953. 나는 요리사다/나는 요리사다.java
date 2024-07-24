import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));

        int max = 0;
        int result = 0;
        for (int i = 0; i < 5; i++) {
            int target = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).reduce(0, (a, b) -> a + b);
            if (target > max) {
                max = target;
                result = i + 1;
            }
        }
        System.out.println(result + " " + max);
    }
}
