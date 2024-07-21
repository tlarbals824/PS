import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));

        long n = Long.parseLong(br.readLine());
        // int[] check = new int[65];
        if (n == 0) {
            System.out.println("NO");
            return;
        }
        while (n > 0) {
            if (n % 3 == 2) {
                System.out.println("NO");
                return;
            }
            n /= 3;
        }
        System.out.println("YES");
    }

}
