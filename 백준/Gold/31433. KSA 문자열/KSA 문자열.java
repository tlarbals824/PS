import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));

        char[] x = br.readLine().toCharArray();

        char[] ksa = { 'K', 'S', 'A' };
        int add = 0;
        if (x[0] != 'K') {
            add = 2;
        }
        int start = 1;

        /**
         * 첫번쨰에서는 K로 바꿈에 의미를 둬야 할듯
         */
        for (int i = 1; i < x.length; i++) {
            if (x[i] == ksa[start]) {
                start = (start + 1) % 3;
            } else {
                add += 2;
            }
        }
        System.out.println(add);

    }

}
