import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));

        long n = Long.parseLong(br.readLine());
        if (n <= 3) {
            System.out.println((n & 1) == 1 ? "SK" : "CY");
        } else {
            n = ((n - 4) % 5) % 2;
            System.out.println((n & 1) == 1 ? "CY" : "SK");

        }
    }

    /**
     * 마지막에 가져가는 사람이 이김
     * 1: SK
     * 2: CY
     * 3: SK
     * 4: SK - a
     * 5: CY
     * 6: SK
     * 7: CY
     * 8: SK - a
     * 9: SK - b
     * 10: CY
     * 11: SK
     * 12: CY
     * 13: SK - b
     * 14: SK - c
     * 15: CY
     * 16: SK -
     * 17: CY
     * 18: SK - c
     * 19: SK - d
     * 20: CY
     * 21: SK
     * 22: CY
     * 23: SK - d
     * 24: SK
     * 25: CY
     */

}
