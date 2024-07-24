import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] check = new int[1010];
        int[] input;
        int tc = Integer.parseInt(br.readLine());

        for (int i = 0; i < tc; i++) {
            int n = Integer.parseInt(br.readLine());
            Arrays.fill(check, 0);
            input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int result = 0;
            for (int j = 0; j < n; j++) {
                if (check[j] == 0) {
                    result++;
                    int current = j + 1;
                    while (true) {
                        if (check[current - 1] == 1)
                            break;
                        check[current - 1] = 1;
                        current = input[current - 1];
                    }
                }
            }
            bw.write(result + "\n");
        }
        bw.flush();

    }
}
