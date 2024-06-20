import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] nmk = Arrays.stream(br.readLine().split(" "))
                .mapToInt(str -> Integer.parseInt(str))
                .toArray();

        int[] cardArr = Arrays.stream(br.readLine().split(" "))
                .mapToInt(str -> Integer.parseInt(str))
                .toArray();

        Arrays.sort(cardArr);
        int[] offerCardArr = Arrays.stream(br.readLine().split(" "))
                .mapToInt(str -> Integer.parseInt(str))
                .toArray();

        int[] check = new int[nmk[0]];

        for (int i = 0; i < nmk[2]; i++) {
            bw.write(cardArr[lower_bound(cardArr, offerCardArr[i], check)] + "\n");
        }
        bw.flush();
    }

    static int lower_bound(int[] arr, int target, int[] check) {
        int left = 0;
        int right = arr.length;

        int mid = 0;

        while (left < right) {
            mid = (left + right) / 2;

            if (arr[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        while (check[right] == 1) {
            right++;
            if (arr.length == right) {
                check[right - 1] = 1;
                return right - 1;
            }
        }
        check[right] = 1;
        return right;
    }

}
