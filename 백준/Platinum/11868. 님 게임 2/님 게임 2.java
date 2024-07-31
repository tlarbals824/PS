import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] num = Arrays.stream(br.readLine().split(" "))
                .mapToInt(it -> {
                    int converted = Integer.parseInt(it);
                    return converted;
                })
                .toArray();

        int result = 0;
        for (int i = 0; i < n; i++) {
            result = result ^ num[i];
        }

        System.out.println((result == 0) ? "cubelover" : "koosaga");
    }
}
