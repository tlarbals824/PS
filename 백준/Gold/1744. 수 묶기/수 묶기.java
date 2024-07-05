import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        int minusEnd = n - 1;
        int plusStart = 0;

        for (int i = 1; i < n; i++) {
            if (arr[i] >= 0 && arr[i - 1] < 0) {
                minusEnd = i - 1;
            }
            if (arr[i] > 0 && arr[i - 1] <= 0) {
                plusStart = i;
            }
        }

        int result = 0;

        for (int i = 0; i <= minusEnd; i += 2) {
            if (arr[i] >= 0)
                continue;
            if (i == minusEnd) {
                if (i == n - 1) {
                    result += arr[i];
                }else if (i != n - 1 && arr[i + 1] != 0) {
                    result += arr[i];
                }
            } else {
                result += arr[i] * arr[i + 1];
            }
        }
        for (int i = n - 1; i >= plusStart; i -= 2) {
            if (arr[i] <= 0)
                continue;
            if (i == plusStart) {
                result += arr[i];
            } else {
                if (arr[i - 1] == 1 || arr[i] == 1) {
                    result += arr[i] + arr[i - 1];
                } else {
                    result += arr[i] * arr[i - 1];
                }

            }
        }

        System.out.println(result);

    }
}
