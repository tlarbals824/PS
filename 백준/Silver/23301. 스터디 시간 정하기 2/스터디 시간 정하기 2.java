import java.util.*;
import java.io.*;

class Main {

    private static int[] timeLine = new int[2000];

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));

        int[] nt = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = nt[0], t = nt[1];
        int maxTime = 0;
        for (int i = 0; i < n; i++) {
            int k = Integer.parseInt(br.readLine());
            for (int j = 0; j < k; j++) {
                int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                int start = input[0], end = input[1];
                maxTime = Math.max(maxTime, end);
                for (int time = start; time < end; time++) {
                    timeLine[time]++;
                }
            }
        }

        int start = 0, end = 0;
        int expectedTime = 0;
        for (int i = 0; i <= maxTime - t; i++) {
            int sum = 0;
            for (int j = i; j < i + t; j++) {
                sum += timeLine[j];
            }
            if (expectedTime < sum) {
                expectedTime = sum;
                start = i;
                end = i + t;
            }
        }
        System.out.println(start + " " + end);
    }

}
