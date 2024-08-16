import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));

        char[] input = br.readLine().toCharArray();
        int inputNum = Integer.parseInt(String.valueOf(input));
        nextPermutation(input);
        int resultNum = Integer.parseInt(String.valueOf(input));

        if (inputNum == resultNum) {
            System.out.println(0);
        } else {
            System.out.println(resultNum);
        }
    }

    private static void nextPermutation(char[] input) {
        /**
         * 1234
         * 1243
         * 1324
         * 1342
         * 1423
         * 
         * 
         * 감소하는 지점 다음 값과 그 값보다 큰값중 작은 값으로 바꾸고, 정렬하기
         */
        for (int i = input.length - 1; i > 0; i--) {
            if (input[i] > input[i - 1]) {
                int maxIdx = i;
                for (int j = i; j < input.length; j++) {
                    if (input[i - 1] < input[j] && input[j] < input[maxIdx]) {
                        maxIdx = j;
                    }
                }

                char tmp = input[maxIdx];
                input[maxIdx] = input[i - 1];
                input[i - 1] = tmp;

                Arrays.sort(input, i, input.length);

                break;
            }
        }
    }

}
