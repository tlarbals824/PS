import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        System.out.println(n % 2 == 1 ? "SK" : "CY");
    }

    /**
     * 1 : SK
     * 2 : CY
     * 3 : SK
     * 4 : CY
     * 5 : SK
     * 6 :
     */

}
