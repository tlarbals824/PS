import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        /**
         * 단위는 long
         * 
         * 기준 값 1을 시작으로
         * 지연 평가?
         */

        long startValue = 1;
        long totalSum = 0;
        long multiNum = 1;

        int q = Integer.parseInt(br.readLine());
        while (q-- > 0) {
            long[] input = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
            if (input[0] == 0) {
                totalSum += input[1];
            }
            if (input[0] == 1) {
                totalSum *= input[1];
                multiNum *= input[1];
            }
            if (input[0] == 2) {
                startValue += input[1];
            }
            if (input[0] == 3) {
                bw.write((startValue * multiNum + totalSum) + "\n");
            }
        }
        bw.flush();
    }

}
