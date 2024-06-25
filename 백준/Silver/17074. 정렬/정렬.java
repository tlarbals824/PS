import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] ar = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int currentIdx = -1;
        int failCount = 0;
        int failIndex = 0;
        for (int i = 1; i < n; i++) {
            if (ar[i] < ar[i - 1]) {
                failCount++;
                if (failCount == 2) {
                    System.out.println(0);
                    return;
                }
                failIndex = i - 1;
            }
        }

        if (failCount == 1) {
            int upper = 0;
            int startIdx = failIndex - 1;
            if (startIdx == -1) {
                if (n == 2) {
                    System.out.println(2);
                } else {
                    int result = 1 + (ar[0] <= ar[2] ? 1 : 0);
                    System.out.println(result);
                }
            } else {
                if (failIndex + 2 >= n) {
                    int result = 1 + (ar[failIndex - 1] <= ar[failIndex + 1] ? 1 : 0);
                    System.out.println(result);
                } else {
                    for (int i = 0; i < 3; i++) {
                        int value = ar[failIndex + i];

                        if (value >= ar[startIdx]) {
                            upper++;
                        }
                    }
                    if (upper == 1) {
                        System.out.println(0);
                    } else {
                        int result = (upper == 3 ? 1 : 0)
                                + (ar[failIndex] <= ar[failIndex + 2] ? 1 : 0);
                        System.out.println(result);
                    }
                }
            }
        } else {
            System.out.println(n);
        }
    }

}
