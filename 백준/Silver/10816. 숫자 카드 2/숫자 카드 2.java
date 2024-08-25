import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] cards = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int m = Integer.parseInt(br.readLine());
        int[] check = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Map<Integer, Integer> count = new HashMap<>();
        for (int i = 0; i < n; i++) {
            count.put(cards[i], count.getOrDefault(cards[i], 0) + 1);
        }
        for (int i = 0; i < m; i++) {
            bw.write(count.getOrDefault(check[i], 0) + " ");
        }
        bw.write("\n");
        bw.flush();

    }

}
