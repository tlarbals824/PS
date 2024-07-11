import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));

        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int result = count(input[0], input[1], input[2]);
        System.out.println(result);
    }

    private static int count(int w, int h, int a) { // w 큰 값, h 작은 값
        int result = Integer.MAX_VALUE;
        for (int i = 1; i <= a; i++) {
            if (a % i != 0)
                continue;
            int anotherNum = a / i;
            if (i > h || anotherNum > w)
                continue;

            int countH = foldCount(i, h, 0);
            int countW = foldCount(anotherNum, w, 0);
            result = Math.min(countH + countW, result);
        }
        
        return result == Integer.MAX_VALUE ? -1 : result;
    }

    private static int foldCount(int target, int current, int count) {
        if (target == current)
            return count;
        else {
            if (target <= (current + 1) / 2) {
                return foldCount(target, (current % 2 + current) / 2, count + 1);
            } else {
                return foldCount(target, target, count + 1);
            }
        }
    }

    // 8,9
    // 127 -> 64 -> 32 -> 16 -> 8
    // 129 -> 65 -> 33 -> 17 -> 9
}
