import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();
        int length = input.length();

        int[][] dp = new int[length][length];
        for (int i = 0; i < length; i++) {
            dp[i][i] = 1;
        }

        for (int i = 0; i < length - 1; i++) {
            if (input.charAt(i) == input.charAt(i + 1)) {
                dp[i][i + 1] = 1;
            }
        }

        for (int i = 2; i < length; i++) {
            for (int j = 0; j < length - i; j++) {
                if (input.charAt(j) == input.charAt(j + i) && dp[j + 1][j + i - 1] == 1) {
                    dp[j][j + i] = 1;
                }
            }
        }

        int max = 0;
        for (int i = 0; i < length; i++) {
            if (dp[length - 1 - i][length - 1] == 1) {
                max = i + 1;
            }
        }

        System.out.println(length + (length - max));

    }

}
