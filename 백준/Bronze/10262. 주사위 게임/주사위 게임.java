import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));

        int[] g = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] e = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[] count = new int[210];
        for (int i = g[0]; i <= g[1]; i++) {
            for (int j = g[2]; j <= g[3]; j++) {
                count[i + j]++;
            }
        }

        int[] sum = new int[210];
        for (int i = g[1] + g[3]; i >= 0; i--) {
            sum[i] = count[i] + sum[i + 1];
        }

        int winCount = 0;
        int tieCount = 0;

        for (int i = e[0]; i <= e[1]; i++) {
            for (int j = e[2]; j <= e[3]; j++) {
                winCount += sum[i + j + 1];
                tieCount += count[i + j];
            }
        }

        int total = (g[1] - g[0] + 1) * (g[3] - g[2] + 1) * (e[1] - e[0] + 1) * (e[3] - e[2] + 1) - tieCount;

        if (winCount * 2 == total) {
            System.out.println("Tie");
        } else if (winCount * 2 > total) {
            System.out.println("Gunnar");
        } else {
            System.out.println("Emma");
        }
    }

}
