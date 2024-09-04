import java.util.*;
import java.io.*;

class Main {

    private static char[] ksa = { 'K', 'S', 'A' };

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));

        String x = br.readLine();

        int result = Integer.MAX_VALUE;
        for (int i = 0; i < 3; i++) {
            result = Math.min(result, cal(x, i));
        }
        System.out.println(result);

    }

    private static int cal(String x, int start) {
        int result = start;
        for (int i = 0; i < x.length(); i++) {
            if (x.charAt(i) == ksa[start % 3]) {
                start++;
            } else {
                result++; // 버린 횟수
            }
        }
        return result + Math.abs(x.length() - start);
    }

}
