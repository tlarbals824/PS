import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();
        int length = input.length();
        int n = Integer.parseInt(input);

        long[] count = new long[10];
        for (int i = 0; i < length; i++) {
            int pow = (int) Math.pow(10, i);
            int upperPow = 10 * pow;
            int share = n / upperPow;
            int remainder = n % upperPow;

            int upperRemainder = remainder / pow;
            int lowerRemainder = remainder % pow;
            if (share > 0) {
                for (int j = 0; j < 10; j++) {
                    count[j] += (j == 0 ? share - 1 : share) * pow;
                }
            }
            int start = (share > 0 ? 0 : 1);
            for (int j = start; j < upperRemainder; j++) {
                count[j] += pow;
            }
            count[upperRemainder] += (lowerRemainder + 1);
        }

        var sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(count[i] + " ");
        }
        System.out.println(sb.toString());
    }

}
