import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));

        long n = Long.parseLong(br.readLine());
        if (n <= 2) {
            System.out.println((n & 1) == 1 ? "SK" : "CY");
        } else {
            n = (n - 2) % 7;
            System.out.println((n == 5 || n == 0) ? "CY" : "SK");
        }
    }

    /**
     * 1: SK
     * 2: CY
     * 3: SK
     * 4: SK
     * 5: SK
     * 6: SK
     * 7: CY
     * 8: SK
     * 9: CY
     * 10: SK
     * 11: SK
     * 12: SK
     * 13: SK
     * 14: CY
     * 15: SK
     * 16: CY
     */

}
