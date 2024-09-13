import java.util.*;
import java.io.*;

class Main {

    private static double[] bAcc = new double[11];
    private static double[] aAcc = new double[11];

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));

        int k = Integer.parseInt(br.readLine());
        int[] cArr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] abn = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        double a = abn[0], b = abn[1], n = abn[2];

        aAcc[0] = bAcc[0] = 1;
        aAcc[1] = a;
        bAcc[1] = b;
        for (int i = 2; i <= 10; i++) {
            aAcc[i] = aAcc[i - 1] * a;
            bAcc[i] = bAcc[i - 1] * b;
        }

        double left = 0;

        for (int i = k; i >= 0; i--) {
            left += cArr[k - i] * (bAcc[i + 1] - aAcc[i + 1]) / (i + 1);
        }

        left = correct(left);

        double start = 0, end = 20;
        while (start < end) {
            double mid = ((start + end) / 2);
            double right = 0;
            for (int i = 0; i < n; i++) {
                right += fx(cArr, (a + i * (b - a) / n + mid)) * (b - a) / n;
            }

            right = correct(right);

            if (right == left) {
                System.out.println(mid);
                return;
            } else if (right > left) {
                end = mid;
            } else {
                start = mid;
            }
        }

        
        System.out.println(-1);

    }

    private static double correct(double target) {
        return Math.floor(target * 10000) / 10000;
    }

    private static double fx(int[] cArr, double x) {
        int k = cArr.length - 1;
        double result = 0;
        for (int i = k; i >= 0; i--) {
            result += cArr[k - i] * Math.pow(x, i);
        }
        return result;
    }

}
