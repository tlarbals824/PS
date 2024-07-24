import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int s = Integer.parseInt(br.readLine());
        char[] m = br.readLine().toCharArray();

        int result = 0;
        int[] check = new int[s + 1];
        for (int i = 1; i < s; i++) {
            check[i] = (m[i - 1] != m[i] ? (check[i - 1] == 0 && m[i - 1] == 'O' ? 0 : check[i - 1] + 1) : 0);
        }
        for (int i = 0; i < s; i++) {
            if (check[i] >= 2 * n && check[i] % 2 == 0)
                result++;
        }
        System.out.println(result);

    }

}
