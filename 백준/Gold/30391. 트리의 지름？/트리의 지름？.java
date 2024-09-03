import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));

        int[] nk = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int count = nk[0] - 1, k = nk[1];
        int lastIndex = 1;
        int currentIndex = 0;

        var sb = new StringBuilder();
        while (count > 0) {
            currentIndex++; // n
            
            var max = Math.min(count, currentIndex == 1 ? k : k - 1);
            for (int i = 0; i < max; i++) {
                count--;
                sb.append(currentIndex + " " + (++lastIndex) + "\n");
            }
        }
        System.out.print(sb.toString());

    }

}
