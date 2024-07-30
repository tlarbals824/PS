import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        n = (n - 1) % 7;
        System.out.println((n == 0 || n == 2) ? "CY" : "SK");
    }

    /**
     * 이기는 경우
     * 1: CY
     * 2: SK
     * 3: CY
     * 4: SK
     * 5: SK
     * 6: SK
     * 7: SK
     * 8: CY
     * 9: SK
     * 10: CY
     * 11: SK
     * 12: SK
     * 13: SK
     * 14: SK
     * 15: CY
     */

}
