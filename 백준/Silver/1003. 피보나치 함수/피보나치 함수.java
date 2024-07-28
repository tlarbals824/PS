import java.util.*;
import java.io.*;

class Main {

    private static int[] fibonacciWithZeroCount = new int[50];
    private static int[] fibonacciWithOneCount = new int[50];

    static {
        fibonacciWithZeroCount[0] = 1;
        fibonacciWithOneCount[1] = 1;
    }

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());
        fibonacci(fibonacciWithZeroCount, 41);
        fibonacci(fibonacciWithOneCount, 41);

        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            System.out.println(fibonacciWithZeroCount[n] + " " + fibonacciWithOneCount[n]);
        }
    }

    private static int fibonacci(int[] fibonacciArr, int index) {
        if (fibonacciArr[index] != 0)
            return fibonacciArr[index];
        if (index <= 1) {
            return fibonacciArr[index];
        } else {
            return fibonacciArr[index] = fibonacci(fibonacciArr, index - 1) + fibonacci(fibonacciArr, index - 2);
        }
    }

}
